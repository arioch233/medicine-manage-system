package com.moumouzhandui.mms.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 器材模块
 *
 * @author 冷血毒舌
 * @create 2022/11/6 10:08
 */
@ApiModel(description = "器材模块")
@Data
public class MedicalDevicesVO {
    /**
     * 医疗器材id
     */
    @ApiModelProperty(name = "id", value = "器材id", dataType = "Integer")
    private Integer id;

    /**
     * 医疗器械编号
     */
    @ApiModelProperty(name = "medicalDevicesNum", value = "医疗器械编号", required = true, dataType = "String")
    private String medicalDevicesNum;

    /**
     * 医疗器材名称
     */
    @ApiModelProperty(name = "medicalDevicesName", value = "医疗器材名称", required = true, dataType = "String")
    private String medicalDevicesName;

    /**
     * 医疗器械图片
     */
    @ApiModelProperty(name = "medicalDevicesCover", value = "医疗器械图片", required = true, dataType = "String")
    private String medicalDevicesCover;

    /**
     * 医疗器械功能
     */
    @ApiModelProperty(name = "medicalDevicesEffect", value = "医疗器械功能", required = true, dataType = "String")
    private String medicalDevicesEffect;

    /**
     * 医疗器械型号规格
     */
    @ApiModelProperty(name = "medicalDevicesSpecification", value = "医疗器械型号规格", required = true, dataType = "String")
    private String medicalDevicesSpecification;

    /**
     * 医疗器械厂商
     */
    @ApiModelProperty(name = "medicalDevicesManufacturer", value = "医疗器械厂商", required = true, dataType = "String")
    private String medicalDevicesManufacturer;

    /**
     * 医疗器械分类
     */
    @ApiModelProperty(name = "medicalDevicesCategory", value = "医疗器械分类", required = true, dataType = "String")
    private String medicalDevicesCategory;

    /**
     * 医疗器械数量
     */
    @ApiModelProperty(name = "medicalDevicesQuantity", value = "医疗器械数量", required = true, dataType = "Integer")
    private Integer medicalDevicesQuantity;

    /**
     * 医疗器械单价
     */
    @ApiModelProperty(name = "medicalDevicesPrice", value = "医疗器械单价", required = true, dataType = "BigDecimal")
    private BigDecimal medicalDevicesPrice;
}
