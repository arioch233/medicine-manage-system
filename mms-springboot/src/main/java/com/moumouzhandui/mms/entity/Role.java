package com.moumouzhandui.mms.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色表
 *
 * @TableName sys_role
 */
@TableName(value = "sys_role")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleLabel;

    /**
     * 是否禁用  0否 1是
     */
    private Integer isDisable;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}