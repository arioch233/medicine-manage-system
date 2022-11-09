package com.moumouzhandui.mms.controller;

import com.moumouzhandui.mms.common.Result;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.pojo.vo.SupplierVO;
import com.moumouzhandui.mms.service.SupplierService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 供货商管理模块
 *
 * @author 冷血毒舌
 * @create 2022/10/27 23:39
 */
@Api(tags = "供货商管理模块")
@RestController
@RequestMapping(value = "/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    /**
     * 获取当前供货商列表
     *
     * @param conditionVO
     * @return
     */
    @ApiOperation(value = "获取当前供货商列表")
    @GetMapping("/all")
    public Result listSupplier(ConditionVO conditionVO) {
        return Result.success(supplierService.listSupplier(conditionVO));
    }

    /**
     * 保存和更新供货商
     *
     * @param supplierVO
     * @return
     */
    @ApiOperation(value = "保存和更新供货商")
    @PostMapping("/update")
    public Result updateSupplier(@RequestBody SupplierVO supplierVO) {
        supplierService.updateSupplier(supplierVO);
        return Result.success();
    }

    /**
     * 根据id删除供货商
     *
     * @param supplierIds
     * @return
     */
    @ApiOperation(value = "根据id删除供货商")
    @DeleteMapping("/delete")
    public Result deleteSupplier(@RequestBody List<Integer> supplierIds) {
        supplierService.deleteSupplier(supplierIds);
        return Result.success();
    }

    /**
     * 获取供货商选项列表
     *
     * @return
     */
    @ApiOperation(value = "获取供货商选项列表")
    @GetMapping("/options")
    public Result listSupplierOptions() {
        return Result.success(supplierService.listSupplierOptions());
    }
}
