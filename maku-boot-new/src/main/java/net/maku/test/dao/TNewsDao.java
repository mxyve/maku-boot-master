package net.maku.test.dao;

import net.maku.framework.mybatis.dao.BaseDao;
import net.maku.test.entity.TNewsEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 资讯表
 *
 * @author MAKU babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Mapper
public interface TNewsDao extends BaseDao<TNewsEntity> {

}