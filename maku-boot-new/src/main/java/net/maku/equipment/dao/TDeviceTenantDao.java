package net.maku.equipment.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.maku.equipment.entity.TDeviceTenantEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备租户关联表
 */
@Mapper
public interface TDeviceTenantDao extends BaseMapper<TDeviceTenantEntity> {

}