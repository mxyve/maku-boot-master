package net.maku.equipment.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import net.maku.equipment.query.TDeviceQuery;
import net.maku.framework.mybatis.dao.BaseDao;
import net.maku.equipment.entity.TDeviceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 设备表
 *
 * @author MAKU babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Mapper
public interface TDeviceDao extends BaseDao<TDeviceEntity> {
    // 自定义关联查询 SQL（通过租户ID过滤设备）
    @Select("""
        SELECT d.* 
        FROM t_device d
        LEFT JOIN t_device_tenant dt ON d.id = dt.device_id
        WHERE dt.tenant_id = #{tenantId}

    """)
    IPage<TDeviceEntity> selectPageWithTenant(IPage<TDeviceEntity> page, @Param("tenantId") String tenantId, @Param("query") TDeviceQuery query);
}