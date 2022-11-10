package com.moumouzhandui.mms.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 冷血毒舌
 * @create 2022/11/10 1:18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPieChartDTO {
    /**
     * id
     */
    private Integer id;

    /**
     * 分类名
     */
    private String categoryName;

    /**
     * 分类下的货物种类数量
     */
    private Integer cargoCount;
}
