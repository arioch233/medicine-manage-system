package com.moumouzhandui.mms.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author 冷血毒舌
 * @create 2022/11/6 10:07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalDevicesDTO {

    /**
     * 医疗器材id
     */
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
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
