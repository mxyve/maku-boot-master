package net.maku.test.service;

import net.maku.framework.common.utils.PageResult;
import net.maku.framework.mybatis.service.BaseService;
import net.maku.test.vo.TNewsVO;
import net.maku.test.query.TNewsQuery;
import net.maku.test.entity.TNewsEntity;
import java.util.List;

/**
 * 资讯表
 *
 * @author MAKU babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
public interface TNewsService extends BaseService<TNewsEntity> {

    PageResult<TNewsVO> page(TNewsQuery query);

    TNewsVO get(Long id);


    void save(TNewsVO vo);

    void update(TNewsVO vo);

    void delete(List<Long> idList);


    void export();
}