package com.moumouzhandui.mms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moumouzhandui.mms.entity.Role;
import com.moumouzhandui.mms.pojo.dto.ResourceRoleDTO;
import com.moumouzhandui.mms.pojo.dto.RoleDTO;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 冷血毒舌
 * @description 针对表【sys_role】的数据库操作Service实现
 * @createDate 2022-10-20 23:58:25
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<RoleDTO> listRole(@Param("current") Long current, @Param("size") Long size, @Param("condition") ConditionVO condition);

    /**
     * 查询路由角色列表
     *
     * @return 角色标签
     */
    List<ResourceRoleDTO> listResourceRoles();

    List<String> listRolesByUserInfoId(@Param("userInfoId") Integer userInfoId);
}




