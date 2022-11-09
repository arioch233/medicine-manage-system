package com.moumouzhandui.mms.handle.security;


import com.alibaba.fastjson2.JSON;
import com.moumouzhandui.mms.common.Result;
import com.moumouzhandui.mms.common.enums.StatusCodeEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.moumouzhandui.mms.common.CommonConst.APPLICATION_JSON;


/**
 * 用户未登录处理
 *
 * @author 冷血毒舌
 * @create 2022/11/6 23:21
 */

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setContentType(APPLICATION_JSON);
        httpServletResponse.getWriter().write(JSON.toJSONString(Result.error(StatusCodeEnum.NO_LOGIN.getCode(),StatusCodeEnum.NO_LOGIN.getDescription())));
    }

}