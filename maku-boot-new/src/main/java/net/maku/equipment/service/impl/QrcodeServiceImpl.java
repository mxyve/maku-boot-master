package net.maku.equipment.service.impl;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.AllArgsConstructor;
import net.maku.equipment.dao.QrcodeDao;
import net.maku.equipment.dao.TDeviceDao;
import net.maku.equipment.entity.QrcodeEntity;
import net.maku.equipment.entity.TDeviceEntity;
import net.maku.equipment.service.QrcodeService;
import net.maku.equipment.vo.QrcodeVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
@AllArgsConstructor
public class QrcodeServiceImpl extends ServiceImpl<QrcodeDao, QrcodeEntity> implements QrcodeService {
    private final QrcodeDao qrcodeDao;
    private final TDeviceDao tDeviceDao;

    @Override
    public QrcodeEntity getByQrcodeId(String qrcodeId) {
        return qrcodeDao.getByDeviceId(qrcodeId);
    }

    @Override
    public QrcodeVO getQrcodeByDeviceId(String qrcodeId) {
        QrcodeEntity entity = qrcodeDao.getByDeviceId(qrcodeId);
        if (entity == null) {
            return null;
        }

        // 将Entity转换为VO
        QrcodeVO vo = new QrcodeVO();
        vo.setId(entity.getId());
        vo.setDeviceId(entity.getDeviceId());
        vo.setPicture(entity.getPicture());
//        vo.setCreateTime(entity.getCreateTime());

        return vo;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void generateQrcode(String deviceId) {
        if (StringUtils.isBlank(deviceId)) {
            throw new IllegalArgumentException("设备ID不能为空");
        }

        QrConfig config = new QrConfig(300, 300);

        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            QrCodeUtil.generate(deviceId, config, "png", os);
            String base64Image = "data:image/png;base64," + Base64.getEncoder().encodeToString(os.toByteArray());

            // 1. 检查二维码表中是否已存在该设备的记录
            QrcodeEntity qrcode = qrcodeDao.getByDeviceId(deviceId); // 假设根据deviceId查询二维码记录
            if (qrcode == null) {
                // 2. 不存在则创建新记录
                qrcode = new QrcodeEntity();
                qrcode.setDeviceId(deviceId);
                qrcode.setPicture(base64Image);
                qrcode.setCreateTime(LocalDateTime.now());
                save(qrcode); // 保存到二维码表，自动生成id（需依赖数据库自增主键）
            } else {
                // 3. 存在则更新图片和时间
                qrcode.setPicture(base64Image);
                qrcode.setCreateTime(LocalDateTime.now());
                updateById(qrcode); // 更新二维码记录
            }

            // 4. 根据deviceId找到对应的设备记录，更新qrcode_id为二维码表的id
            LambdaUpdateWrapper<TDeviceEntity> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(TDeviceEntity::getDeviceId, deviceId) // 通过deviceId关联设备表
                    .set(TDeviceEntity::getQrcodeId, qrcode.getId()); // 设置qrcode_id为二维码表的id
            tDeviceDao.update(null, updateWrapper); // 执行更新
        } catch (Exception e) {
            throw new RuntimeException("生成二维码失败", e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteQrcode(Long qrcodeId) {
        // 1. 先查询二维码记录获取关联的deviceId
        QrcodeEntity qrcode = getById(qrcodeId);
        if (qrcode == null) {
            return;
        }

        // 2. 删除二维码记录
        removeById(qrcodeId);

        // 3. 查找所有关联此二维码的设备，并清除qrcode_id
        LambdaUpdateWrapper<TDeviceEntity> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(TDeviceEntity::getQrcodeId, qrcodeId)
                .set(TDeviceEntity::getQrcodeId, null);
        tDeviceDao.update(null, updateWrapper);
    }
}