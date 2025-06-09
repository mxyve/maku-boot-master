package net.maku.equipment.dao;

import net.maku.framework.mybatis.dao.BaseDao;
import net.maku.equipment.entity.QrcodeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface QrcodeDao extends BaseDao<QrcodeEntity> {
    @Select("SELECT id, device_id AS deviceId, picture, is_open, create_time AS createTime " +
            "FROM t_device_qrcode WHERE device_id = #{deviceId} ")
    QrcodeEntity getByDeviceId(String deviceId);
}