package com.moumouzhandui.mms.handle.security;

import com.alibaba.fastjson2.JSON;
import com.moumouzhandui.mms.common.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.moumouzhandui.mms.common.CommonConst.APPLICATION_JSON;


/**
 * 注销处理
 *
 * @author 冷血毒舌
 * @create 2022/11/6 23:17
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
        httpServletResponse.setContentType(APPLICATION_JSON);
        httpServletResponse.getWriter().write(JSON.toJSONString(Result.success()));
    }

}