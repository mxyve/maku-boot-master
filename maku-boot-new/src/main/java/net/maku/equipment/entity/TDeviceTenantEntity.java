package net.maku.equipment.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 设备租户关联表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_device_tenant")
public class TDeviceTenantEntity {
    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 设备ID
     */
    private Integer deviceId;

    /**
     * 租户ID
     */
    private Long tenantId;

    /**
     * 删除标识（0：未删除，1：已删除）
     */
    @TableLogic
    private Integer deleted;


}