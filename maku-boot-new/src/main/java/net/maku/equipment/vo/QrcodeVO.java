package net.maku.equipment.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class QrcodeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String deviceId;
    private Boolean isOpen;
    private Boolean isDeleted;
    private String picture;
    private Date createTime;
}