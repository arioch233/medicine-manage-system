package com.moumouzhandui.mms.utils;

import com.moumouzhandui.mms.pojo.dto.UserDetailDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * 用户工具类
 *
 * @author 冷血毒舌
 * @create 2022/10/22 23:26
 */

@Slf4j
@Component
public class UserUtils {

    /**
     * 获取当前登录用户
     *
     * @return 用户登录信息
     */
    public static UserDetailDTO getLoginUser() {
//        log.debug((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return (UserDetailDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
