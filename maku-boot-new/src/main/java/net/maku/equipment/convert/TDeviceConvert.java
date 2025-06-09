package net.maku.equipment.convert;

import net.maku.equipment.entity.TDeviceEntity;
import net.maku.equipment.vo.TDeviceVO;
import net.maku.equipment.vo.TDeviceExcelVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 设备表
 *
 * @author MAKU babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Mapper
public interface TDeviceConvert {
    TDeviceConvert INSTANCE = Mappers.getMapper(TDeviceConvert.class);

    TDeviceEntity convert(TDeviceVO vo);

    TDeviceVO convert(TDeviceEntity entity);

    List<TDeviceVO> convertList(List<TDeviceEntity> list);

    List<TDeviceEntity> convertList2(List<TDeviceVO> list);

    List<TDeviceExcelVO> convertExcelList(List<TDeviceEntity> list);

    List<TDeviceEntity> convertExcelList2(List<TDeviceExcelVO> list);
}