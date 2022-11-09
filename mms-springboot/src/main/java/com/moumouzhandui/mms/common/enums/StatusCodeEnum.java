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
    FAIL(500, "操作失败"),


    /**
     * 该菜单与角色存在关联
     */
    MENU_ROLE(600, "该菜单与角色存在关联"),
    /**
     * 该资源与角色存在关联
     */
    RESOURCE_ROLE(601, "该资源与角色存在关联"),
    /**
     * 该角色已存在
     */
    ROLE(600, "该角色已存在"),
    /**
     * 目录创建失败
     */
    FILE_PATH(700, "目录创建失败"),
    /**
     * 内部错误删除文件失败
     */
    FILE_DELETE(701, "内部错误删除文件失败"),
    /**
     * 文件格式错误
     */
    FILE_TYPE(702, "文件格式错误"),
    /**
     * 文件保存异常
     */
    FILE_SAVE(703, "文件保存异常"),
    /**
     * 该分类已存在
     */
    CATEGORY(401, "该分类已存在"),

    /**
     * 该供货商已存在
     */
    SUPPLIER(402, "该供货商已存在"),
    /**
     * 该药品已存在
     */
    DRUG(403, "该药品已存在"),


    /**
     * 未登录
     */
    NO_LOGIN(40001, "用户未登录"),
    /**
     * 没有操作权限
     */
    AUTHORIZED(40300, "没有操作权限"),
    /**
     * 系统异常
     */
    SYSTEM_ERROR(50000, "系统异常"),

    /**
     * 参数校验失败
     */
    VALID_ERROR(52000, "参数格式不正确"),
    /**
     * 用户名已存在
     */
    USERNAME_EXIST(52001, "用户名已存在"),
    /**
     * 用户名不存在
     */
    USERNAME_NOT_EXIST(52002, "用户名不存在");


    /**
     * 状态码
     */
    private final Integer code;
    /**
     * 描述
     */
    private final String description;
}
