package com.moumouzhandui.mms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moumouzhandui.mms.common.PageResult;
import com.moumouzhandui.mms.entity.Role;
import com.moumouzhandui.mms.pojo.dto.RoleDTO;
import com.moumouzhandui.mms.pojo.dto.UserRoleDTO;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.pojo.vo.RoleVO;

import java.util.List;

/**
 * @author 冷血毒舌
 * @description 针对表【sys_role】的数据库操作Service
 * @createDate 2022-10-20 23:58:25
 */
public interface RoleService extends IService<Role> {

    PageResult<RoleDTO> listRole(ConditionVO conditionVO);

    void saveOrUpdateRole(RoleVO roleVO);

    void deleteRole(List<Integer> roleIds);

    void updateDisableStatus(RoleVO roleVO);

    List<UserRoleDTO> listRoleOptions();
}
