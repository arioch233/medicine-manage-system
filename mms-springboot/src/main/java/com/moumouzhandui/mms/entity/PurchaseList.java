package com.moumouzhandui.mms.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName tb_purchase_list
 */
@TableName(value = "tb_purchase_list")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseList implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 负责员工id
     */
    private Integer employeeId;

    /**
     * 供货商id
     */
    private Integer supplierId;

    /**
     * 货物列表
     */
    private String cargoList;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}