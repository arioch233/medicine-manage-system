package com.moumouzhandui.mms.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName tb_supplier
 */
@TableName(value = "tb_supplier")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Supplier implements Serializable {
    /**
     * 供货商id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 供货商名称
     */
    private String supplierName;

    /**
     * 供货商负责人
     */
    private String supplierPrincipal;

    /**
     * 供货商联系方式
     */
    private String supplierContract;

    /**
     * 供货商邮箱
     */
    private String supplierEmail;

    /**
     * 供货商地址
     */
    private String supplierAddress;

    /**
     * 供货商开户行
     */
    private String supplierBank;

    /**
     * 供货商开户行账号
     */
    private String supplierBankCard;

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