package com.moumouzhandui.mms.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @TableName tb_medical_devices
 */
@TableName(value = "tb_medical_devices")
@Data
public class MedicalDevices implements Serializable {
    /**
     * 医疗器材id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 医疗器械编号
     */
    private String medicalDevicesNum;

    /**
     * 医疗器材名称
     */
    private String medicalDevicesName;

    /**
     * 医疗器械图片
     */
    private String medicalDevicesCover;

    /**
     * 医疗器械功能
     */
    private String medicalDevicesEffect;

    /**
     * 医疗器械型号规格
     */
    private String medicalDevicesSpecification;

    /**
     * 医疗器械厂商
     */
    private String medicalDevicesManufacturer;

    /**
     * 医疗器械分类
     */
    private String medicalDevicesCategory;

    /**
     * 医疗器械数量
     */
    private Integer medicalDevicesQuantity;

    /**
     * 医疗器械单价
     */
    private BigDecimal medicalDevicesPrice;

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