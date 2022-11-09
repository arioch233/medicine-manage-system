package com.moumouzhandui.mms.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author 冷血毒舌
 * @create 2022/10/28 22:32
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrugDTO {
    /**
     * 药品id
     */
    private Integer id;

    /**
     * 药品封图
     */
    private String drugCover;

    /**
     * 药品编号
     */
    private String drugNum;

    /**
     * 药品通用名
     */
    private String drugCommonName;

    /**
     * 药品商品名
     */
    private String drugTradeName;

    /**
     * 药品剂型
     */
    private String drugForm;

    /**
     * 药品规格
     */
    private String drugSpecification;

    /**
     * 药品包装材质
     */
    private String drugPackagingMaterial;

    /**
     * 药品功效
     */
    private String drugEffect;

    /**
     * 药品厂商
     */
    private String drugManufacturer;

    /**
     * 药品数量
     */
    private Integer drugQuantity;

    /**
     * 药品单价
     */
    private BigDecimal drugPrice;

    /**
     * 药品分类
     */
    private String drugCategory;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
