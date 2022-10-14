package com.moumouzhandui.mms.exception;

import lombok.Getter;

/**
 * 自定义异常
 */
@Getter
public class ServiceException extends RuntimeException {
    private Integer code;

    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}