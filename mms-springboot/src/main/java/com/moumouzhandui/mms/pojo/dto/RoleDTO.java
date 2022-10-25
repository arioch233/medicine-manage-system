package com.moumouzhandui.mms.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 冷血毒舌
 * @create 2022/10/21 9:24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    /**
     * 角色id
     */
    private Integer id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色描述
     */
    private String roleLabel;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否禁用
     */
    private Integer isDisable;

    /**
     * 资源id列表
     */
    private List<Integer> resourceIdList;

    /**
     * 菜单id列表
     */
    private List<Integer> menuIdList;
}
