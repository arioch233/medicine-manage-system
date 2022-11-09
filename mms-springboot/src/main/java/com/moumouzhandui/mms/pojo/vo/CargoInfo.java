package com.moumouzhandui.mms.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 商品信息
 *
 * @author 冷血毒舌
 * @create 2022/11/8 15:25
 */
@ApiModel(description = "商品信息")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CargoInfo {

    /**
     * 商品id
     */
    @ApiModelProperty(name = "cargoId", value = "商品id", dataType = "String")
    private String cargoId;


    /**
     * 商品名
     */
    @ApiModelProperty(name = "cargoName", value = "商品名", dataType = "String")
    private String cargoName;


    /**
     * 商品单价
     */
    @ApiModelProperty(name = "cargoPrice", value = "商品单价", dataType = "BigDecimal")
    private BigDecimal cargoPrice;

    /**
     * 商品数量
     */
    @ApiModelProperty(name = "cargoQuantity", value = "商品数量", dataType = "Integer")
    private Integer cargoQuantity;

}
