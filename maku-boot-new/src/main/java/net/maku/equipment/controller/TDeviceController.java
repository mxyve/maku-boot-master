package net.maku.equipment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.maku.equipment.service.QrcodeService;
import net.maku.equipment.vo.QrcodeVO;
import net.maku.framework.common.utils.PageResult;
import net.maku.framework.common.utils.Result;
import net.maku.framework.operatelog.annotations.OperateLog;
import net.maku.framework.operatelog.enums.OperateTypeEnum;
import net.maku.equipment.service.TDeviceService;
import net.maku.equipment.query.TDeviceQuery;
import net.maku.equipment.vo.TDeviceVO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

/**
 * 设备表
 *
 * @author MAKU babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@RestController
@RequestMapping("/equipment/device")
@Tag(name="设备表")
@AllArgsConstructor
public class TDeviceController {
    private final TDeviceService tDeviceService;

    @GetMapping("page")
    @Operation(summary = "分页查询设备（含二维码信息）")
    @PreAuthorize("hasAuthority('equipment:device')")
    public Result<PageResult<TDeviceVO>> page(@ParameterObject @Valid TDeviceQuery query) {
        PageResult<TDeviceVO> page = tDeviceService.page(query);
        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('equipment:device')")
    public Result<TDeviceVO> get(@PathVariable("id") Long id){
        TDeviceVO data = tDeviceService.get(id);
        return Result.ok(data);
    }

    @PostMapping
    @Operation(summary = "保存")
    @OperateLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('equipment:device')")
    public Result<String> save(@RequestBody TDeviceVO vo){
        tDeviceService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @OperateLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('equipment:device')")
    public Result<String> update(@RequestBody @Valid TDeviceVO vo){
        tDeviceService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @OperateLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('equipment:device')")
    public Result<String> delete(@RequestBody List<Long> idList){
        tDeviceService.delete(idList);

        return Result.ok();
    }


    @GetMapping("export")
    @Operation(summary = "导出")
    @OperateLog(type = OperateTypeEnum.EXPORT)
    @PreAuthorize("hasAuthority('equipment:device')")
    public void export() {
        tDeviceService.export();
    }

    private final QrcodeService qrcodeService;

    // 生成二维码
    @PostMapping("/{deviceId}/generate-qrcode")
    @Operation(summary = "生成设备二维码")
    public Result<String> generateQrcode(@PathVariable String deviceId) {
        qrcodeService.generateQrcode(deviceId);
        return Result.ok("二维码生成成功");
    }

    // 删除二维码
    @DeleteMapping("/qrcode/{qrcodeId}")
    @Operation(summary = "删除设备二维码")
    public Result<String> deleteQrcode(@PathVariable Long qrcodeId) {
        qrcodeService.deleteQrcode(qrcodeId);
        return Result.ok("二维码删除成功");
    }

    @GetMapping("/qrcode/by-device-id/{deviceId}")
    @Operation(summary = "获取设备二维码信息")
    public Result<QrcodeVO> getQrcodeByDeviceId(@PathVariable String deviceId) {
        QrcodeVO qrcode = qrcodeService.getQrcodeByDeviceId(deviceId);
        return Result.ok(qrcode);
    }
}