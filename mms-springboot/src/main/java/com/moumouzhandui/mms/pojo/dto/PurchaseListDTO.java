package com.moumouzhandui.mms.pojo.dto;

import com.moumouzhandui.mms.pojo.vo.CargoInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 冷血毒舌
 * @create 2022/11/8 15:23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseListDTO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 负责员工名称
     */
    private String employeeName;

    /**
     * 供货商名称
     */
    private String supplierName;

    /**
     * 货物列表
     */
    private List<CargoInfo> cargoList;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
