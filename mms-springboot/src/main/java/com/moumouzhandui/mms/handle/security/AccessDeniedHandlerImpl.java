package com.moumouzhandui.mms.handle.security;

import com.alibaba.fastjson2.JSON;
import com.moumouzhandui.mms.common.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.moumouzhandui.mms.common.CommonConst.APPLICATION_JSON;

/**
 * 用户权限处理
 *
 * @author 冷血毒舌
 * @create 2022/11/7 0:05
 */

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        httpServletResponse.setContentType(APPLICATION_JSON);
        httpServletResponse.getWriter().write(JSON.toJSONString(Result.error("权限不足")));
    }

}
