package com.moumouzhandui.mms.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品
 *
 * @author 冷血毒舌
 * @create 2022/11/8 14:49
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CargoDTO {

    /**
     * 商品id
     */
    private String cargoId;

    /**
     * 商品名
     */
    private String cargoName;
}
