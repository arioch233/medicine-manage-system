package com.moumouzhandui.mms.controller;

import com.moumouzhandui.mms.common.Result;
import com.moumouzhandui.mms.pojo.dto.UserDetailDTO;
import com.moumouzhandui.mms.pojo.vo.*;
import com.moumouzhandui.mms.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户信息控制器
 *
 * @author 冷血毒舌
 * @create 2022/11/7 0:27
 */
@Slf4j
@Api(tags = "用户信息模块")
@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;


    /**
     * 更新用户信息
     *
     * @param userInfoVO 用户信息
     * @return {@link Result}
     */
    @ApiOperation(value = "更新用户信息")
    @PutMapping("/user/info")
    public Result updateUserInfo(@Valid @RequestBody UserInfoVO userInfoVO, Authentication authentication) {
        UserDetailDTO userDetailDTO = (UserDetailDTO) authentication.getPrincipal();
        userInfoService.updateUserInfo(userInfoVO, userDetailDTO);
        return Result.success();
    }

    /**
     * 更新用户头像
     *
     * @return {@link Result} 头像地址
     */
    @ApiOperation(value = "更新用户头像")
    @PutMapping("/users/avatar")
    public Result updateUserAvatar(@RequestBody AvatarVO avatarVO, Authentication authentication) {
        log.error(avatarVO.getAvatarUrl());
        UserDetailDTO userDetailDTO = (UserDetailDTO) authentication.getPrincipal();
        return Result.success(userInfoService.updateUserAvatar(avatarVO.getAvatarUrl(), userDetailDTO), "头像更新成功");
    }

    /**
     * 绑定用户邮箱
     *
     * @param emailVO 邮箱信息
     * @return {@link Result}
     */
    @ApiOperation(value = "绑定用户邮箱")
    @PostMapping("/users/email")
    public Result saveUserEmail(@Valid @RequestBody EmailVO emailVO, Authentication authentication) {
        UserDetailDTO userDetailDTO = (UserDetailDTO) authentication.getPrincipal();
        userInfoService.saveUserEmail(emailVO, userDetailDTO);
        return Result.success();
    }

    /**
     * 修改用户角色
     *
     * @param userRoleVO 用户角色信息
     * @return {@link Result}
     */
    @ApiOperation(value = "修改用户角色")
    @PutMapping("/users/role")
    public Result updateUserRole(@Valid @RequestBody UserRoleVO userRoleVO) {
        userInfoService.updateUserRole(userRoleVO);
        return Result.success();
    }

    /**
     * 修改用户禁用状态
     *
     * @param userDisableVO 用户禁用信息
     * @return {@link Result}
     */
    @ApiOperation(value = "修改用户禁用状态")
    @PutMapping("/users/disable")
    public Result updateUserDisable(@Valid @RequestBody UserDisableVO userDisableVO) {
        userInfoService.updateUserDisable(userDisableVO);
        return Result.success();
    }


    /**
     * 查看在线用户
     *
     * @param conditionVO 条件
     * @return {@link Result} 在线用户列表
     */
    @ApiOperation(value = "查看在线用户")
    @GetMapping("/user/online")
    public Result listOnlineUsers(ConditionVO conditionVO) {
        return Result.success(userInfoService.listOnlineUsers(conditionVO));
    }

    /**
     * 下线用户
     *
     * @param userInfoId 用户信息
     * @return {@link Result}
     */
    @ApiOperation(value = "下线用户")
    @DeleteMapping("/admin/users/{userInfoId}/online")
    public Result removeOnlineUser(@PathVariable("userInfoId") Integer userInfoId) {
        userInfoService.removeOnlineUser(userInfoId);
        return Result.success();
    }
}
