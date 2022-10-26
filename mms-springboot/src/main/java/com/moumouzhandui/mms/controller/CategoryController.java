package com.moumouzhandui.mms.controller;

import com.moumouzhandui.mms.common.Result;
import com.moumouzhandui.mms.pojo.vo.CategoryVO;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类模块
 *
 * @author 冷血毒舌
 * @create 2022/10/25 19:28
 */
@Api(tags = "分类模块")
@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取当前分类列表
     *
     * @param conditionVO
     * @return
     */
    @ApiOperation(value = "获取当前分类列表")
    @GetMapping("/all")
    public Result listCategory(ConditionVO conditionVO) {
        return Result.success(categoryService.listCategory(conditionVO));
    }

    /**
     * 保存和更新分类
     *
     * @param categoryVO
     * @return
     */
    @ApiOperation(value = "保存和更新分类")
    @PostMapping("/update")
    public Result updateCategory(@RequestBody CategoryVO categoryVO) {
        categoryService.updateCategory(categoryVO);
        return Result.success();
    }

    /**
     * 根据id删除分类
     *
     * @param categoryIds
     * @return
     */
    @ApiOperation(value = "根据id删除分类")
    @DeleteMapping("/delete")
    public Result deleteCategory(@RequestBody List<Integer> categoryIds) {
        categoryService.deleteCategory(categoryIds);
        return Result.success();
    }


}
