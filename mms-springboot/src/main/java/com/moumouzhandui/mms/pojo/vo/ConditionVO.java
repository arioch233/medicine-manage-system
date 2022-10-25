package com.moumouzhandui.mms.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询条件
 *
 * @author 冷血毒舌
 * @create 2022/10/15 22:51
 */

@ApiModel(description = "查询条件")
@Data
public class ConditionVO {
    /**
     * 页码
     */
    @ApiModelProperty(name = "current", value = "页码", dataType = "Long")
    private Long current;

    /**
     * 条数
     */
    @ApiModelProperty(name = "size", value = "条数", dataType = "Long")
    private Long size;

    /**
     * 搜索内容
     */
    @ApiModelProperty(name = "keywords", value = "搜索内容", dataType = "String")
    private String keywords;
}
