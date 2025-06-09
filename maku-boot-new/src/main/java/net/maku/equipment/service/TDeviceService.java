package net.maku.equipment.service;

import net.maku.equipment.entity.QrcodeEntity;
import net.maku.framework.common.utils.PageResult;
import net.maku.framework.mybatis.service.BaseService;
import net.maku.equipment.vo.TDeviceVO;
import net.maku.equipment.query.TDeviceQuery;
import net.maku.equipment.entity.TDeviceEntity;
import java.util.List;

/**
 * 设备表
 *
 * @author MAKU babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
public interface TDeviceService extends BaseService<TDeviceEntity> {

    PageResult<TDeviceVO> page(TDeviceQuery query);

    TDeviceVO get(Long id);


    void save(TDeviceVO vo);

    void update(TDeviceVO vo);

    void delete(List<Long> idList);


    void export();

    QrcodeEntity getQrcodeByDeviceId(String deviceId);
}