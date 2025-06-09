package net.maku.equipment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.maku.equipment.dao.TDeviceTenantDao;
import net.maku.equipment.entity.TDeviceTenantEntity;
import net.maku.equipment.service.TDeviceTenantService;
import org.springframework.stereotype.Service;

/**
 * 设备租户关联表
 */
@Service
public class TDeviceTenantServiceImpl extends ServiceImpl<TDeviceTenantDao, TDeviceTenantEntity> implements TDeviceTenantService {

}