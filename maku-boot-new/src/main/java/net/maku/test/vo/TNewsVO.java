package net.maku.test.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import lombok.Data;
import java.io.Serializable;
import net.maku.framework.common.utils.DateUtils;
import java.time.LocalDateTime;

/**
 * 资讯表
 *
 * @author MAKU babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
@Schema(description = "资讯表")
public class TNewsVO {
    @Schema(description = "资讯ID")
    private Long id;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "管理员ID")
    private Long adminId;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private LocalDateTime updateTime;

    @Schema(description = "删除标识：0-正常，1-已删除")
    private Integer deleted;

    @Schema(description = "浏览量")
    private Integer viewCount;

    @Schema(description = "状态：0-草稿，1-已发布，2-已下架")
    private Integer status;

    @Schema(description = "封面图片URL")
    private String coverImage;

    @Schema(description = "资讯来源")
    private String source;

}