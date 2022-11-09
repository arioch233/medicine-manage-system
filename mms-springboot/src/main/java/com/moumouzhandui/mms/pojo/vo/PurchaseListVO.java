package com.moumouzhandui.mms.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 进货单模块
 *
 * @author 冷血毒舌
 * @create 2022/11/8 15:17
 */
@ApiModel(description = "进货单模块")
@Data
public class PurchaseListVO {
    /**
     * 负责员工id
     */
    @ApiModelProperty(name = "employeeId", value = "负责员工id", dataType = "Integer")
    private Integer employeeId;

    /**
     * 供货商id
     */
    @ApiModelProperty(name = "supplierId", value = "供货商id", dataType = "Integer")
    private Integer supplierId;

    /**
     * 货物列表
     */
    @ApiModelProperty(name = "cargoList", value = "货物列表", dataType = "List")
    private List<CargoInfo> cargoList;
}
