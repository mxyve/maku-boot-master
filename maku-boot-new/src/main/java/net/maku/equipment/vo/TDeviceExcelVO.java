package net.maku.equipment.vo;

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
 * 设备表
 *
 * @author MAKU babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Data
@ColumnWidth(20)
public class TDeviceExcelVO implements TransPojo {
    @ExcelProperty("设备ID")
    private Integer id;

    @ExcelProperty("设备自动生成的id")
    private String deviceId;

    @ExcelProperty("设备名称")
    private String name;

    @ExcelProperty("设备二维码（对应qrcode表）")
    private Integer qrcodeId;

    @ExcelProperty("设备类型：0-灯，1-门锁，2-灌溉器，3-喂食器")
    private Integer type;

    @ExcelProperty("设备物理地址")
    private String location;

    @ExcelProperty("设备状态： 0 离线 1 在线 ")
    private Integer status;

    @ExcelProperty(value = "创建时间", converter = LocalDateTimeConverter.class)
    private LocalDateTime createTime;

    @ExcelProperty(value = "更新时间", converter = LocalDateTimeConverter.class)
    private LocalDateTime updateTime;


}