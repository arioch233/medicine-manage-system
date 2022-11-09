package com.moumouzhandui.mms.common;

import io.jsonwebtoken.Claims;
import lombok.Data;

/**
 * jwt验证信息
 *
 * @author 冷血毒舌
 * @create 2022/11/9 15:09
 */
@Data
public class CheckResult {

    private int errCode;

    private boolean success;

    private Claims claims;
}

