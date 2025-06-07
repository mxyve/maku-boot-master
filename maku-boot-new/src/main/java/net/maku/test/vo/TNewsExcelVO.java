package net.maku.test.vo;

import lombok.Data;
import cn.idev.excel.annotation.ExcelIgnore;
import cn.idev.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.write.style.ColumnWidth;
import com.fhs.core.trans.vo.TransPojo;
import com.fhs.core.trans.anno.Trans;
import com.fhs.core.trans.constant.TransType;
import net.maku.framework.common.excel.LocalDateTimeConverter;
import java.time.LocalDateTime;

/**
 * 资讯表
 *
 * @author MAKU babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
@ColumnWidth(20)
public class TNewsExcelVO implements TransPojo {
    @ExcelIgnore
    private Long id;

    @ExcelProperty("标题")
    private String title;

    @ExcelProperty("内容")
    private String content;

    @ExcelProperty("管理员ID")
    private Long adminId;

    @ExcelProperty(value = "创建时间", converter = LocalDateTimeConverter.class)
    private LocalDateTime createTime;

    @ExcelProperty(value = "更新时间", converter = LocalDateTimeConverter.class)
    private LocalDateTime updateTime;

    @ExcelProperty("浏览量")
    private Integer viewCount;

    @ExcelProperty("状态：0-草稿，1-已发布，2-已下架")
    private Integer status;

    @ExcelProperty("封面图片URL")
    private String coverImage;

    @ExcelProperty("资讯来源")
    private String source;

}