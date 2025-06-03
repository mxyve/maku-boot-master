package net.maku.test.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import net.maku.framework.common.utils.PageResult;
import net.maku.framework.mybatis.service.impl.BaseServiceImpl;
import net.maku.test.convert.TNewsConvert;
import net.maku.test.entity.TNewsEntity;
import net.maku.test.query.TNewsQuery;
import net.maku.test.vo.TNewsVO;
import net.maku.test.dao.TNewsDao;
import net.maku.test.service.TNewsService;
import com.fhs.trans.service.impl.TransService;
import net.maku.framework.common.utils.ExcelUtils;
import net.maku.test.vo.TNewsExcelVO;
import net.maku.framework.common.excel.ExcelFinishCallBack;
import org.springframework.web.multipart.MultipartFile;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 资讯表
 *
 * @author MAKU babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Service
@AllArgsConstructor
public class TNewsServiceImpl extends BaseServiceImpl<TNewsDao, TNewsEntity> implements TNewsService {
    private final TransService transService;

    @Override
    public PageResult<TNewsVO> page(TNewsQuery query) {
        IPage<TNewsEntity> page = baseMapper.selectPage(getPage(query), getWrapper(query));

        return new PageResult<>(TNewsConvert.INSTANCE.convertList(page.getRecords()), page.getTotal());
    }


    private LambdaQueryWrapper<TNewsEntity> getWrapper(TNewsQuery query){
        LambdaQueryWrapper<TNewsEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.like(ObjectUtil.isNotEmpty(query.getTitle()), TNewsEntity::getTitle, query.getTitle());

        wrapper.like(ObjectUtil.isNotEmpty(query.getContent()), TNewsEntity::getContent, query.getContent());


        return wrapper;
    }


    @Override
    public TNewsVO get(Long id) {
        TNewsEntity entity = baseMapper.selectById(id);
        TNewsVO vo = TNewsConvert.INSTANCE.convert(entity);

        return vo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(TNewsVO vo) {
        TNewsEntity entity = TNewsConvert.INSTANCE.convert(vo);

        baseMapper.insert(entity);


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(TNewsVO vo) {
        TNewsEntity entity = TNewsConvert.INSTANCE.convert(vo);

        updateById(entity);


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Long> idList) {
        removeByIds(idList);

    }



    @Override
    public void export() {
        List<TNewsExcelVO> excelList = TNewsConvert.INSTANCE.convertExcelList(list());
        transService.transBatch(excelList);
        ExcelUtils.excelExport(TNewsExcelVO.class, "资讯表", null, excelList);
    }

}