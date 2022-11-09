package com.moumouzhandui.mms.controller;

import com.moumouzhandui.mms.annotation.AccessLimit;
import com.moumouzhandui.mms.common.Result;
import com.moumouzhandui.mms.pojo.dto.UserDetailDTO;
import com.moumouzhandui.mms.pojo.vo.PasswordVO;
import com.moumouzhandui.mms.pojo.vo.UserVO;
import com.moumouzhandui.mms.service.UserAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

/**
 * 用户账号控制器
 *
 * @author 冷血毒舌
 * @create 2022/11/7 0:26
 */
@Api(tags = "用户账号模块")
@RestController
@RequestMapping("/user")
public class UserAuthController {

    @Autowired
    private UserAuthService userAuthService;

    /**
     * 发送邮箱验证码
     *
     * @param username
     * @return
     * @throws MessagingException
     */
    @AccessLimit(seconds = 60, maxCount = 1)
    @ApiOperation(value = "发送邮箱验证码")
    @ApiImplicitParam(name = "username", dataType = "String", required = true, value = "用户名")
    @GetMapping("/code")
    public Result sendCode(String username) throws MessagingException {
        userAuthService.sendCode(username);
        return Result.success();
    }


    /**
     * 用户注册
     *
     * @param user 用户信息
     * @return {@link Result<>}
     */
    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public Result register(@Valid @RequestBody UserVO user) {
        userAuthService.register(user);
        return Result.success();
    }

    /**
     * 修改密码
     *
     * @param user 用户信息
     * @return {@link Result<>}
     */
    @ApiOperation(value = "修改密码")
    @PutMapping("/password")
    public Result updatePassword(@Valid @RequestBody UserVO user) {
        userAuthService.updatePassword(user);
        return Result.success();
    }

    /**
     * 修改管理员密码
     *
     * @param passwordVO 密码信息
     * @return {@link Result<>}
     */
    @ApiOperation(value = "修改管理员密码")
    @PutMapping("/admin/password")
    public Result updateAdminPassword(@Valid @RequestBody PasswordVO passwordVO, Authentication authentication) {
        UserDetailDTO userDetailDTO = (UserDetailDTO) authentication.getPrincipal();
        userAuthService.updateAdminPassword(passwordVO,userDetailDTO);
        return Result.success();
    }

}
