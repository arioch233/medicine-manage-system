package com.moumouzhandui.mms.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @TableName tb_drug
 */
@TableName(value = "tb_drug")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Drug implements Serializable {
    /**
     * 药品id
     */
    @TableId(type = IdType.AUTO)
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