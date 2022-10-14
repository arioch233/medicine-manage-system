package com.moumouzhandui.mms.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 接口状态码枚举
 *
 * @author 冷血毒舌
 * @create 2022/10/15 22:48
 */
@Getter
@AllArgsConstructor
public enum StatusCodeEnum {

    /**
     * 成功
     */
    SUCCESS(200, "操作成功"),
    /**
     * 失败
     */
    FAIL(500, "操作失败");

    /**
     * 状态码
     */
    private final Integer code;
    /**
     * 描述
     */
    private final String description;
}
