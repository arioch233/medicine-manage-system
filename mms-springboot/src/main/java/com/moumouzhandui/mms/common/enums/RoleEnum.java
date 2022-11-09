package com.moumouzhandui.mms.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 角色枚举
 *
 * @author 冷血毒舌
 * @create 2022/11/7 17:17
 */
@Getter
@AllArgsConstructor
public enum RoleEnum {
    /**
     * 管理员
     */
    ADMIN(1, "管理员", "admin"),
    /**
     * 员工
     */
    EMPLOYEE(2, "员工", "employee"),
    /**
     * 普通用户
     */
    USER(3, "用户", "user"),
    /**
     * 测试账号
     */
    TEST(4, "测试", "test");

    /**
     * 角色id
     */
    private final Integer roleId;

    /**
     * 描述
     */
    private final String name;

    /**
     * 权限标签
     */
    private final String label;

}
