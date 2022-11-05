package com.moumouzhandui.mms.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 药品模块
 *
 * @author 冷血毒舌
 * @create 2022/10/28 22:31
 */
@ApiModel(description = "药品模块")
@Data
public class DrugVO {

    /**
     * 药品id
     */
    @ApiModelProperty(name = "id", value = "药品id", dataType = "Integer")
    private Integer id;

    /**
     * 药品封图
     */
    @ApiModelProperty(name = "drugCover", value = "药品封图", required = true, dataType = "String")
    private String drugCover;

    /**
     * 药品编号
     */
    @ApiModelProperty(name = "drugNum", value = "药品编号", required = true, dataType = "String")
    private String drugNum;
    /**
     * 药品通用名
     */
    @ApiModelProperty(name = "drugCommonName", value = "药品通用名", required = true, dataType = "String")
    private String drugCommonName;

    /**
     * 药品商品名
     */
    @ApiModelProperty(name = "drugTradeName", value = "药品商品名", required = true, dataType = "String")
    private String drugTradeName;

    /**
     * 药品剂型
     */
    @ApiModelProperty(name = "drugForm", value = "药品剂型", required = true, dataType = "String")
    private String drugForm;

    /**
     * 药品规格
     */
    @ApiModelProperty(name = "drugSpecification", value = "药品规格", required = true, dataType = "String")
    private String drugSpecification;

    /**
     * 药品包装材质
     */
    @ApiModelProperty(name = "drugPackagingMaterial", value = "药品包装材质", required = true, dataType = "String")
    private String drugPackagingMaterial;

    /**
     * 药品功效
     */
    @ApiModelProperty(name = "drugEffect", value = "药品功效", required = true, dataType = "String")
    private String drugEffect;

    /**
     * 药品厂商
     */
    @ApiModelProperty(name = "drugManufacturer", value = "药品厂商", required = true, dataType = "String")
    private String drugManufacturer;

    /**
     * 药品数量
     */
    @ApiModelProperty(name = "drugQuantity", value = "药品数量", required = true, dataType = "Integer")
    private Integer drugQuantity;

    /**
     * 药品单价
     */
    @ApiModelProperty(name = "drugPrice", value = "药品单价", required = true, dataType = "BigDecimal")
    private BigDecimal drugPrice;

    /**
     * 药品分类
     */
    @ApiModelProperty(name = "drugCategory", value = "药品分类", required = true, dataType = "String")
    private String drugCategory;
}
