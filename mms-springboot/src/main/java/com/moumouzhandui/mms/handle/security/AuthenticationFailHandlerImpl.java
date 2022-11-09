package com.moumouzhandui.mms.handle.security;

import com.alibaba.fastjson2.JSON;
import com.moumouzhandui.mms.common.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.moumouzhandui.mms.common.CommonConst.APPLICATION_JSON;

/**
 * 登录失败处理
 *
 * @author 冷血毒舌
 * @create 2022/11/6 23:27
 */

@Component
public class AuthenticationFailHandlerImpl implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        httpServletResponse.setContentType(APPLICATION_JSON);
        httpServletResponse.getWriter().write(JSON.toJSONString(Result.error(e.getMessage())));
    }

}
