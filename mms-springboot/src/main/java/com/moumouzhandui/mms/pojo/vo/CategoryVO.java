package com.moumouzhandui.mms.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 分类模块
 *
 * @author 冷血毒舌
 * @create 2022/10/25 19:54
 */
@ApiModel(description = "分类模块")
@Data
public class CategoryVO {
    /**
     * id
     */
    @ApiModelProperty(name = "id", value = "分类id", dataType = "Integer")
    private Integer id;
    /**
     * 分类名称
     */
    @ApiModelProperty(name = "categoryName", value = "分类名", dataType = "String")
    @NotBlank(message = "分类名不能为空")
    private String categoryName;
    /**
     * 分类描述
     */
    @ApiModelProperty(name = "categoryDescription", value = "分类描述", dataType = "String")
    @NotBlank(message = "分类描述不能为空")
    private String categoryDescription;

    /**
     * 分类类型
     */
    @ApiModelProperty(name = "type", value = "分类类型", dataType = "integer")
    @NotBlank(message = "分类类型")
    private Integer categoryType;

}
