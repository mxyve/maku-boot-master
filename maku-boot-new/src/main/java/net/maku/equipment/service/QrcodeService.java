package net.maku.equipment.service;

import net.maku.equipment.vo.QrcodeVO;
import net.maku.framework.mybatis.service.BaseService;
import net.maku.equipment.entity.QrcodeEntity;

public interface QrcodeService extends BaseService<QrcodeEntity> {
    QrcodeEntity getByQrcodeId(String qrcodeId);
    void generateQrcode(String deviceId); // 生成二维码
    void deleteQrcode(Long qrcodeId);     // 删除二维码
    QrcodeVO getQrcodeByDeviceId(String deviceId);

}