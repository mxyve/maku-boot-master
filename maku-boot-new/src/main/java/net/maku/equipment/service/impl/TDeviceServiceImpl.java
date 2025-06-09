package net.maku.equipment.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import net.maku.equipment.entity.QrcodeEntity;
import net.maku.equipment.entity.TDeviceTenantEntity;
import net.maku.equipment.service.QrcodeService;
import net.maku.equipment.service.TDeviceTenantService;
import net.maku.framework.common.utils.PageResult;
import net.maku.framework.mybatis.service.impl.BaseServiceImpl;
import net.maku.equipment.convert.TDeviceConvert;
import net.maku.equipment.entity.TDeviceEntity;
import net.maku.equipment.query.TDeviceQuery;
import net.maku.equipment.vo.TDeviceVO;
import net.maku.equipment.dao.TDeviceDao;
import net.maku.equipment.service.TDeviceService;
import com.fhs.trans.service.impl.TransService;
import net.maku.framework.common.utils.ExcelUtils;
import net.maku.equipment.vo.TDeviceExcelVO;
import net.maku.framework.common.excel.ExcelFinishCallBack;
import net.maku.framework.security.user.SecurityUser;
import org.springframework.web.multipart.MultipartFile;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class TDeviceServiceImpl extends BaseServiceImpl<TDeviceDao, TDeviceEntity> implements TDeviceService {
    private final TransService transService;
    private final QrcodeService qrcodeService;
    private final TDeviceTenantService tDeviceTenantService;

    @Override
    public PageResult<TDeviceVO> page(TDeviceQuery query) {
        // 获取当前用户租户ID
        Long tenantId = SecurityUser.getUser().getTenantId();

        // 构建查询条件
        LambdaQueryWrapper<TDeviceEntity> wrapper = getWrapper(query);

        // 添加租户过滤条件
        wrapper.inSql(TDeviceEntity::getId,
                "SELECT device_id FROM t_device_tenant WHERE deleted = 0 AND tenant_id = " + tenantId);

        IPage<TDeviceEntity> page = baseMapper.selectPage(getPage(query), wrapper);

        List<TDeviceEntity> entityList = page.getRecords();
        List<TDeviceVO> voList = TDeviceConvert.INSTANCE.convertList(entityList);

        // 遍历设备列表，补充二维码图片
        for (TDeviceVO vo : voList) {
            if (vo.getQrcodeId() != null) {
                QrcodeEntity qrcode = qrcodeService.getById(vo.getQrcodeId());
                if (qrcode != null) {
                    vo.setPicture(qrcode.getPicture());
                }
            }
        }
        return new PageResult<>(voList, page.getTotal());
    }

    private LambdaQueryWrapper<TDeviceEntity> getWrapper(TDeviceQuery query) {
        LambdaQueryWrapper<TDeviceEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.like(ObjectUtil.isNotEmpty(query.getName()), TDeviceEntity::getName, query.getName());
        wrapper.like(ObjectUtil.isNotEmpty(query.getStatus()), TDeviceEntity::getStatus, query.getStatus());
        return wrapper;
    }

    @Override
    public TDeviceVO get(Long id) {
        // 验证设备是否属于当前租户
        Long tenantId = SecurityUser.getUser().getTenantId();
        Long count = baseMapper.selectCount(Wrappers.<TDeviceEntity>lambdaQuery()
                .eq(TDeviceEntity::getId, id)
                .inSql(TDeviceEntity::getId,
                        "SELECT device_id FROM t_device_tenant WHERE deleted = 0 AND tenant_id = " + tenantId));

        if(count == 0) {
            throw new RuntimeException("设备不存在或无权访问");
        }

        TDeviceEntity entity = baseMapper.selectById(id);
        TDeviceVO vo = TDeviceConvert.INSTANCE.convert(entity);

        if (entity.getQrcodeId() != null) {
            QrcodeEntity qrcode = qrcodeService.getById(entity.getQrcodeId());
            if (qrcode != null) {
                vo.setPicture(qrcode.getPicture());
            }
        }
        return vo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(TDeviceVO vo) {
        // 保存设备信息
        TDeviceEntity entity = TDeviceConvert.INSTANCE.convert(vo);
        baseMapper.insert(entity);

        // 获取当前租户ID
        Long tenantId = SecurityUser.getUser().getTenantId();

        // 在t_device_tenant表中添加关联记录
        TDeviceTenantEntity deviceTenant = new TDeviceTenantEntity();
        deviceTenant.setDeviceId(entity.getId());
        deviceTenant.setTenantId(tenantId);
        deviceTenant.setDeleted(0);
        tDeviceTenantService.save(deviceTenant);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(TDeviceVO vo) {
        TDeviceEntity entity = TDeviceConvert.INSTANCE.convert(vo);
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        // 获取当前租户ID
        Long tenantId = SecurityUser.getUser().getTenantId();

        // 逻辑删除设备租户关联表中的记录（将deleted设置为1）
        for (Long deviceId : idList) {
            tDeviceTenantService.update(
                    Wrappers.<TDeviceTenantEntity>lambdaUpdate()
                            .eq(TDeviceTenantEntity::getDeviceId, deviceId)
                            .eq(TDeviceTenantEntity::getTenantId, tenantId)
                            .set(TDeviceTenantEntity::getDeleted, 1)
            );
        }

        // 物理删除设备表中的记录
        removeByIds(idList);
    }

    @Override
    public void export() {
        List<TDeviceExcelVO> excelList = TDeviceConvert.INSTANCE.convertExcelList(list());
        transService.transBatch(excelList);
        ExcelUtils.excelExport(TDeviceExcelVO.class, "设备表", null, excelList);
    }

    @Override
    public QrcodeEntity getQrcodeByDeviceId(String qrcodeId) {
        return qrcodeService.getByQrcodeId(qrcodeId);
    }
}