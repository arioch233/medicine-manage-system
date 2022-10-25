package com.moumouzhandui.mms.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 冷血毒舌
 * @create 2022/10/21 9:41
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LabelOptionDTO {
    /**
     * 选项id
     */
    private Integer id;

    /**
     * 选项名
     */
    private String label;

    /**
     * 子选项
     */
    private List<LabelOptionDTO> children;
}
