package com.moumouzhandui.mms.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 供货商
 *
 * @author 冷血毒舌
 * @create 2022/10/27 23:43
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDTO {
    /**
     * 供货商id
     */
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
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
