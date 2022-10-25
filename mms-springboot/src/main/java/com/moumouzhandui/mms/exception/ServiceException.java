package com.moumouzhandui.mms.exception;

import lombok.Getter;


/**
 * 自定义异常
 *
 * @author 冷血毒舌
 * @description 自定义异常
 * @createDate 2021-10-21 00:06:59
 */
@Getter
public class ServiceException extends RuntimeException {
    private Integer code;

    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}