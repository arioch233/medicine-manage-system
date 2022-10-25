package com.moumouzhandui.mms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moumouzhandui.mms.common.Result;
import com.moumouzhandui.mms.pojo.dto.MenuDTO;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.pojo.vo.MenuVO;
import com.moumouzhandui.mms.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 菜单模块
 *
 * @author 冷血毒舌
 * @create 2022/10/21 0:18
 */
@Api(tags = "菜单模块")
@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;


    /**
     * 查询所有菜单列表
     *
     * @param conditionVO 条件
     * @return Result {@link Page<MenuDTO>} 对象
     */
    @ApiOperation(value = "获取菜单列表")
    @GetMapping("/all")
    public Result listMenu(ConditionVO conditionVO) {
        return Result.success(menuService.listMenu(conditionVO));
    }

    /**
     * 新增或修改保存菜单
     *
     * @param menuVO
     * @return Result.success()
     */
    @ApiOperation(value = "更新保存菜单")
    @PostMapping("/update")
    public Result updateMenu(@RequestBody MenuVO menuVO) {
        menuService.saveOrUpdateMenu(menuVO);
        return Result.success();
    }

    /**
     * 删除菜单
     *
     * @param menuId
     * @return Result.success()
     */
    @ApiOperation(value = "删除菜单")
    @DeleteMapping("/delete/{menuId}")
    public Result deleteMenu(@PathVariable("menuId") Integer menuId) {
        menuService.deleteMenu(menuId);
        return Result.success();
    }

    /**
     * 查看角色菜单选项
     *
     * @return {@link Result} 查看角色菜单选项
     */
    @ApiOperation(value = "查看角色菜单选项")
    @GetMapping("/role")
    public Result listMenuOptions() {
        return Result.success(menuService.listMenuOptions());
    }

    /**
     * 查看当前用户菜单
     *
     * @return {@link Result} 菜单列表
     */
    @ApiOperation(value = "查看当前用户菜单")
    @GetMapping("/user")
    public Result listUserMenus() {
        return Result.success(menuService.listUserMenus());
    }

}
