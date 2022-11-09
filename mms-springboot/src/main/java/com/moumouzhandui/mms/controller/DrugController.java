package com.moumouzhandui.mms.controller;

import com.moumouzhandui.mms.common.Result;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.pojo.vo.DrugVO;
import com.moumouzhandui.mms.service.DrugService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 医疗药品管理模块
 *
 * @author 冷血毒舌
 * @create 2022/10/28 15:22
 */
@Api(tags = "医疗药品管理模块")
@RestController
@RequestMapping(value = "/drug")
public class DrugController {
    @Autowired
    private DrugService drugService;

    /**
     * 获取当前药品条目列表
     *
     * @param conditionVO
     * @return
     */
    @ApiOperation(value = "获取当前药品条目列表")
    @GetMapping("/all")
    public Result listDrug(ConditionVO conditionVO) {
        return Result.success(drugService.listDrug(conditionVO));
    }

    /**
     * 保存和更新药品条目
     *
     * @param drugVO
     * @return
     */
    @ApiOperation(value = "保存和更新药品条目")
    @PostMapping("/update")
    public Result updateDrug(@RequestBody DrugVO drugVO) {
        drugService.updateDrug(drugVO);
        return Result.success();
    }

    /**
     * 根据id删除药品条目
     *
     * @param drugIds
     * @return
     */
    @ApiOperation(value = "根据id删除药品条目")
    @DeleteMapping("/delete")
    public Result deleteDrug(@RequestBody List<Integer> drugIds) {
        drugService.deleteDrug(drugIds);
        return Result.success();
    }

    /**
     * 获取药品选项列表
     *
     * @return
     */
    @ApiOperation(value = "获取药品选项列表")
    @GetMapping("/options")
    public Result listDrugOptions() {
        return Result.success(drugService.listDrugOptions());
    }


}
