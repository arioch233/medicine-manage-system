package com.moumouzhandui.mms.common;

import com.moumouzhandui.mms.common.enums.StatusCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 接口统一返回的包装类
 *
 * @author 冷血毒舌
 * @description 接口统一返回的包装类
 * @createDate 2021-10-21 00:10:57
 */
@ApiModel(description = "返回包装类")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    @ApiModelProperty(name = "code", value = "返回状态码", dataType = "Integer")
    private Integer code; // 返回码
    @ApiModelProperty(name = "message", value = "返回错误消息", dataType = "String")
    private String message; // 返回错误消息
    @ApiModelProperty(name = "data", value = "后台返回存放的信息", dataType = "Object")
    private Object data; // 后台返回存放的信息

    public Result(String message) {
        this.message = message;
    }

    public static Result success() {
        return new Result(StatusCodeEnum.SUCCESS.getCode(), StatusCodeEnum.SUCCESS.getDescription(), null);
    }

    public static Result success(Object data) {
        return new Result(StatusCodeEnum.SUCCESS.getCode(), "", data);
    }

    public static Result success(String message) {
        return new Result(StatusCodeEnum.SUCCESS.getCode(), message, null);
    }

    public static Result success(Object data, String message) {
        return new Result(StatusCodeEnum.SUCCESS.getCode(), message, data);
    }

    public static Result error() {
        return new Result(StatusCodeEnum.FAIL.getCode(), StatusCodeEnum.FAIL.getDescription(), null);
    }

    public static Result error(Integer code, String message) {
        return new Result(code, message, null);
    }

    public static Result error(Integer code, String message, Object data) {
        return new Result(code, message, data);
    }

    public static Result error(String message) {
        return new Result(message);
    }
}