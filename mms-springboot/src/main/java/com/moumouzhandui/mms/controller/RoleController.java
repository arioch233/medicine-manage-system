package com.moumouzhandui.mms.controller;

import com.moumouzhandui.mms.common.Result;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.pojo.vo.RoleVO;
import com.moumouzhandui.mms.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色模块
 *
 * @author 冷血毒舌
 * @create 2022/10/21 0:13
 */
@Api(tags = "角色模块")
@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询所有角色
     *
     * @param conditionVO 条件
     * @return Result List<RoleDTO>
     */
    @ApiOperation(value = "查询角色列表")
    @GetMapping("/all")
    public Result listRole(ConditionVO conditionVO) {
        return Result.success(roleService.listRole(conditionVO));
    }


    /**
     * 保存和更新角色信息
     *
     * @param roleVO
     * @return Result
     */
    @ApiOperation(value = "更新角色信息")
    @PostMapping("/update")
    public Result updateRole(@RequestBody RoleVO roleVO) {
        roleService.saveOrUpdateRole(roleVO);
        return Result.success();
    }

    /**
     * 更新禁用状态
     *
     * @param roleVO
     * @return Result
     */
    @ApiOperation(value = "更新角色信息")
    @PostMapping("/disable")
    public Result isDisable(@RequestBody RoleVO roleVO) {
        roleService.updateDisableStatus(roleVO);
        return Result.success();
    }

    /**
     * 根据id删除角色
     *
     * @param roleIds
     * @return Result Boolean
     */
    @ApiOperation(value = "删除角色")
    @DeleteMapping("/delete")
    public Result deleteRoles(@RequestBody List<Integer> roleIds) {
        roleService.deleteRole(roleIds);
        return Result.success();
    }

    /**
     * 获取角色选项列表
     *
     * @return
     */
    @ApiOperation(value = "获取角色选项列表")
    @GetMapping("/options")
    public Result listRoleOptions() {
        return Result.success(roleService.listRoleOptions());
    }
}
