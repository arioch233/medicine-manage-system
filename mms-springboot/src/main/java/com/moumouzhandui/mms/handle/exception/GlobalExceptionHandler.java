package com.moumouzhandui.mms.handle.exception;

import com.moumouzhandui.mms.common.Result;
import com.moumouzhandui.mms.exception.ServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @author 冷血毒舌
 * @description 全局异常处理
 * @createDate 2021-10-21 00:02:59
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 如果抛出的是ServiceException，则调用该方法
     *
     * @param serviceException 业务异常
     * @return Result对象
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result handle(ServiceException serviceException) {
        return Result.error(serviceException.getCode(), serviceException.getMessage());
    }
}
