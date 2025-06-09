package net.maku.equipment.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.*;
import net.maku.framework.common.query.Query;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 设备表查询
 *
 * @author MAKU babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "设备表查询")
public class TDeviceQuery extends Query {
    @Schema(description = "设备名称")
    private String name;

    @Schema(description = "设备状态： 0 离线 1 在线 ")
    private Integer status;

}