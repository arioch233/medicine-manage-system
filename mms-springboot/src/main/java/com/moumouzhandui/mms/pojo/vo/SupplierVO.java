package com.moumouzhandui.mms.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 供货商模块
 *
 * @author 冷血毒舌
 * @create 2022/10/27 23:41
 */
@ApiModel(description = "供货商模块")
@Data
public class SupplierVO {
    /**
     * 供货商id
     */
    @ApiModelProperty(name = "id", value = "供货商id", dataType = "Integer")
    private Integer id;

    /**
     * 供货商名称
     */
    @ApiModelProperty(name = "supplierName", value = "供货商名称", dataType = "String")
    @NotBlank(message = "供货商名称不能为空")
    private String supplierName;

    /**
     * 供货商负责人
     */
    @ApiModelProperty(name = "supplierPrincipal", value = "供货商负责人", dataType = "String")
    @NotBlank(message = "供货商负责人不能为空")
    private String supplierPrincipal;

    /**
     * 供货商联系方式
     */
    @ApiModelProperty(name = "supplierContract", value = "供货商联系方式", dataType = "String")
    @NotBlank(message = "供货商联系方式不能为空")
    private String supplierContract;

    /**
     * 供货商邮箱
     */
    @ApiModelProperty(name = "categoryName", value = "供货商邮箱", dataType = "String")
    @NotBlank(message = "供货商邮箱不能为空")
    private String supplierEmail;

    /**
     * 供货商地址
     */
    @ApiModelProperty(name = "supplierAddress", value = "供货商地址", dataType = "String")
    @NotBlank(message = "供货商地址不能为空")
    private String supplierAddress;

    /**
     * 供货商开户行
     */
    @ApiModelProperty(name = "supplierBank", value = "供货商开户行", dataType = "String")
    @NotBlank(message = "供货商开户行不能为空")
    private String supplierBank;

    /**
     * 供货商开户行账号
     */
    @ApiModelProperty(name = "supplierBankCard", value = "供货商开户行账号", dataType = "String")
    @NotBlank(message = "供货商开户行账号不能为空")
    private String supplierBankCard;
}
