package com.moumouzhandui.mms.controller;

import com.moumouzhandui.mms.common.Result;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.pojo.vo.MedicalDevicesVO;
import com.moumouzhandui.mms.service.MedicalDevicesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 医疗器材管理模块
 *
 * @author 冷血毒舌
 * @create 2022/11/6 10:12
 */
@Api(tags = "医疗器材管理模块")
@RestController
@RequestMapping(value = "/medical_devices")
public class MedicalDevicesController {

    @Autowired
    private MedicalDevicesService medicalDevicesService;

    /**
     * 获取当前器材条目列表
     *
     * @param conditionVO
     * @return
     */
    @ApiOperation(value = "获取当前器材条目列表")
    @GetMapping("/all")
    public Result listMedicalDevices(ConditionVO conditionVO) {
        return Result.success(medicalDevicesService.listMedicalDevices(conditionVO));
    }

    /**
     * 保存和更新器材条目
     *
     * @param medicalDevicesVO
     * @return
     */
    @ApiOperation(value = "保存和更新器材条目")
    @PostMapping("/update")
    public Result updateMedicalDevices(@RequestBody MedicalDevicesVO medicalDevicesVO) {
        medicalDevicesService.updateMedicalDevices(medicalDevicesVO);
        return Result.success();
    }

    /**
     * 根据id删除器材条目
     *
     * @param medicalDevicesIds
     * @return
     */
    @ApiOperation(value = "根据id删除器材条目")
    @DeleteMapping("/delete")
    public Result deleteMedicalDevices(@RequestBody List<Integer> medicalDevicesIds) {
        medicalDevicesService.deleteMedicalDevices(medicalDevicesIds);
        return Result.success();
    }
}
