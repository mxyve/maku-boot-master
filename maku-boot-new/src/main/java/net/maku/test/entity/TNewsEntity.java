package net.maku.test.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;

/**
 * 资讯表
 *
 * @author MAKU babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */

@Data
@TableName("t_news")
public class TNewsEntity {
    /**
     * 资讯ID
     */
    @TableId
    @TableField(value = "id")
    private Long id;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 管理员ID
     */
    @TableField(value = "admin_id")
    private Long adminId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 删除标识：0-正常，1-已删除
     */
    @TableLogic
    @TableField(value = "deleted", fill = FieldFill.INSERT)
    private Integer deleted;

    /**
     * 浏览量
     */
    @TableField(value = "view_count")
    private Integer viewCount;

    /**
     * 状态：0-草稿，1-已发布，2-已下架
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 封面图片URL
     */
    @TableField(value = "cover_image")
    private String coverImage;

    /**
     * 资讯来源
     */
    @TableField(value = "source")
    private String source;

}