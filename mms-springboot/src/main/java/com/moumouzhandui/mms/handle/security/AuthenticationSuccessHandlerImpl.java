package com.moumouzhandui.mms.handle.security;


import com.alibaba.fastjson2.JSON;
import com.moumouzhandui.mms.common.Result;
import com.moumouzhandui.mms.entity.UserAuth;
import com.moumouzhandui.mms.mapper.UserAuthMapper;
import com.moumouzhandui.mms.pojo.dto.UserDetailDTO;
import com.moumouzhandui.mms.pojo.dto.UserInfoDTO;
import com.moumouzhandui.mms.utils.BeanCopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.moumouzhandui.mms.common.CommonConst.APPLICATION_JSON;

/**
 * 登录成功处理
 *
 * @author 冷血毒舌
 * @create 2022/11/6 23:31
 */

@Slf4j
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Resource
    private UserAuthMapper userAuthMapper;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        UserDetailDTO userDetailDTO = (UserDetailDTO) authentication.getPrincipal();
        // 返回登录信息
        UserInfoDTO userLoginDTO = BeanCopyUtils.copyObject(userDetailDTO, UserInfoDTO.class);
        httpServletResponse.setContentType(APPLICATION_JSON);
        httpServletResponse.getWriter().write(JSON.toJSONString(Result.success(userLoginDTO)));
        // 更新用户ip，最近登录时间
        updateUserInfo(userDetailDTO);
    }

    /**
     * 更新用户信息
     */
    @Async
    public void updateUserInfo(UserDetailDTO userDetailDTO) {
        UserAuth userAuth = UserAuth.builder()
                .id(userDetailDTO.getId())
                .ipAddress(userDetailDTO.getIpAddress())
                .ipSource(userDetailDTO.getIpSource())
                .lastLoginTime(userDetailDTO.getLastLoginTime())
                .build();
        userAuthMapper.updateById(userAuth);
    }

}
