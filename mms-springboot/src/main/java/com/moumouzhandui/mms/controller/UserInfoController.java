package com.moumouzhandui.mms.controller;

import com.moumouzhandui.mms.common.Result;
import com.moumouzhandui.mms.pojo.dto.UserDetailDTO;
import com.moumouzhandui.mms.pojo.vo.*;
import com.moumouzhandui.mms.service.UserInfoService;
import com.moumouzhandui.mms.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户信息控制器
 *
 * @author 冷血毒舌
 * @create 2022/11/7 0:27
 */
@Api(tags = "用户信息模块")
@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;


    @GetMapping("/user/info")
    public Result getUserInfo(Authentication authentication) {
        Map<String, Object> map = new HashMap<>();
        map.put("Principal", authentication.getPrincipal());
//        map.put("Name", authentication.getName());
//        map.put("Credentials", authentication.getCredentials());
//        map.put("Authorities", authentication.getAuthorities());
        map.put("userDetailDTO", UserUtils.getLoginUser());
        return Result.success(map);
    }

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
     * @param file 文件
     * @return {@link Result} 头像地址
     */
    @ApiOperation(value = "更新用户头像")
    @ApiImplicitParam(name = "file", value = "用户头像", required = true, dataType = "MultipartFile")
    @PostMapping("/users/avatar")
    public Result updateUserAvatar(MultipartFile file, Authentication authentication) {
        UserDetailDTO userDetailDTO = (UserDetailDTO) authentication.getPrincipal();
        return Result.success(userInfoService.updateUserAvatar(file, userDetailDTO));
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
    @PutMapping("/admin/users/role")
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
    @PutMapping("/admin/users/disable")
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
    public Result listOnlineUsers(ConditionVO conditionVO, Authentication authentication) {
//        return Result.success(userInfoService.listOnlineUsers(conditionVO));
        return Result.success(authentication.getPrincipal());
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
