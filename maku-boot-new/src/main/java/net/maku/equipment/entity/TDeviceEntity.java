package net.maku.equipment.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.time.LocalDateTime;

/**
 * 设备表
 *
 * @author MAKU babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */

@Data
@TableName("t_device")
public class TDeviceEntity {
    /**
     * 设备ID
     */
    @TableId
    @TableField(value = "id")
    private Integer id;

    /**
     * 设备自动生成的id
     */
    @TableField(value = "device_id")
    private String deviceId;

    /**
     * 设备名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 设备二维码（对应qrcode表）
     */
    @TableField(value = "qrcode_id")
    private Integer qrcodeId;

    /**
     * 设备类型：0-灯，1-门锁，2-灌溉器，3-喂食器
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 设备物理地址
     */
    @TableField(value = "location")
    private String location;

    /**
     * 设备状态： 0 离线 1 在线
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 删除标识
     */
    @TableLogic
    @TableField(value = "deleted", fill = FieldFill.INSERT)
    private Integer deleted;

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

    @TableField(exist = false)
    private String picture;

}