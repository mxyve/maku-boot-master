package net.maku.test.convert;

import net.maku.test.entity.TNewsEntity;
import net.maku.test.vo.TNewsVO;
import net.maku.test.vo.TNewsExcelVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 资讯表
 *
 * @author MAKU babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Mapper
public interface TNewsConvert {
    TNewsConvert INSTANCE = Mappers.getMapper(TNewsConvert.class);

    TNewsEntity convert(TNewsVO vo);

    TNewsVO convert(TNewsEntity entity);

    List<TNewsVO> convertList(List<TNewsEntity> list);

    List<TNewsEntity> convertList2(List<TNewsVO> list);

    List<TNewsExcelVO> convertExcelList(List<TNewsEntity> list);

    List<TNewsEntity> convertExcelList2(List<TNewsExcelVO> list);
}