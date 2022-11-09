package com.moumouzhandui.mms.controller;

import com.moumouzhandui.mms.common.Result;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.pojo.vo.PurchaseListVO;
import com.moumouzhandui.mms.service.PurchaseListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 进货控制器
 *
 * @author 冷血毒舌
 * @create 2022/11/6 16:27
 */
@Api(tags = "进货控制器")
@RestController
@RequestMapping(value = "/purchase_list")
public class PurchaseListController {

    @Autowired
    private PurchaseListService purchaseListService;

    /**
     * 获取当前进货单列表
     *
     * @param conditionVO
     * @return
     */
    @ApiOperation(value = "获取当前进货单列表")
    @GetMapping("/all")
    public Result listPurchase(ConditionVO conditionVO) {
        return Result.success(purchaseListService.listPurchase(conditionVO));
    }

    /**
     * 保存和更新进货单
     *
     * @param purchaseListVO
     * @return
     */
    @ApiOperation(value = "保存和更新进货单")
    @PostMapping("/update")
    public Result updatePurchaseList(@RequestBody PurchaseListVO purchaseListVO) {
        purchaseListService.updatePurchaseList(purchaseListVO);
        return Result.success();
    }

    /**
     * 根据id删除进货单
     *
     * @param purchaseListIds
     * @return
     */
    @ApiOperation(value = "根据id删除进货单")
    @DeleteMapping("/delete")
    public Result deletePurchaseList(@RequestBody List<Long> purchaseListIds) {
        purchaseListService.deletePurchaseList(purchaseListIds);
        return Result.success();
    }
}
