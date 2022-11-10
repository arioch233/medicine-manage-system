package com.moumouzhandui.mms.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 冷血毒舌
 * @create 2022/11/9 23:37
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountDTO {

    private Long userCount;

    private Long drugCount;

    private Long medicalDeviceCount;

    private Long orderCount;

}
