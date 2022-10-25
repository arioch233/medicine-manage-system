package com.moumouzhandui.mms.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 冷血毒舌
 * @create 2022/10/21 9:36
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceRoleDTO {
    /**
     * 资源id
     */
    private Integer id;

    /**
     * 路径
     */
    private String url;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 角色名
     */
    private List<String> roleList;
}
