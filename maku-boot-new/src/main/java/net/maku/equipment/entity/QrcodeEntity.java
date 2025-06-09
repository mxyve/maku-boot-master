package net.maku.equipment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("t_device_qrcode")
public class QrcodeEntity {
    private Long id;
    private String deviceId;         // 设备唯一标识（对应t_device.deviceId）
    private String picture;          // 二维码图片内容（Base64或存储路径）
    private Boolean isOpen;           // 是否开放（0：未开放，1：已开放）
//    private Boolean isDeleted;        // 是否删除（0：未删除，1：已删除）
    private LocalDateTime createTime;
}