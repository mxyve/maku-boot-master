package net.maku.equipment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import lombok.Data;
import java.io.Serializable;
import net.maku.framework.common.utils.DateUtils;
import java.time.LocalDateTime;

/**
 * 设备表
 *
 * @author MAKU babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
@Schema(description = "设备表")
public class TDeviceVO {
    @Schema(description = "设备ID")
    private Integer id;

    @Schema(description = "设备自动生成的id")
    private String deviceId;

    @Schema(description = "设备名称")
    private String name;

    @Schema(description = "设备二维码（对应qrcode表）")
    private Integer qrcodeId;

    @Schema(description = "设备类型：0-灯，1-门锁，2-灌溉器，3-喂食器")
    private Integer type;

    @Schema(description = "设备物理地址")
    private String location;

    @Schema(description = "设备状态： 0 离线 1 在线 ")
    private Integer status;

    @Schema(description = "删除标识")
    private Integer deleted;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @JsonFormat(pattern = DateUtils.DATE_TIME_PATTERN)
    private LocalDateTime updateTime;

    @Schema(description = "设备二维码图片Base64数据")
    private String picture;

    @Schema(description = "设备二维码是否开放")
    private Boolean isOpen;

}