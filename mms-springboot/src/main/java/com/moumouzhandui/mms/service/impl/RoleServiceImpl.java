package com.moumouzhandui.mms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moumouzhandui.mms.common.CommonConst;
import com.moumouzhandui.mms.common.PageResult;
import com.moumouzhandui.mms.common.enums.StatusCodeEnum;
import com.moumouzhandui.mms.entity.Role;
import com.moumouzhandui.mms.entity.RoleMenu;
import com.moumouzhandui.mms.entity.RoleResource;
import com.moumouzhandui.mms.exception.ServiceException;
import com.moumouzhandui.mms.handle.security.FilterInvocationSecurityMetadataSourceImpl;
import com.moumouzhandui.mms.mapper.RoleMapper;
import com.moumouzhandui.mms.pojo.dto.RoleDTO;
import com.moumouzhandui.mms.pojo.dto.UserRoleDTO;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.pojo.vo.RoleVO;
import com.moumouzhandui.mms.service.RoleMenuService;
import com.moumouzhandui.mms.service.RoleResourceService;
import com.moumouzhandui.mms.service.RoleService;
import com.moumouzhandui.mms.utils.BeanCopyUtils;
import com.moumouzhandui.mms.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.moumouzhandui.mms.common.CommonConst.FALSE;

/**
 * @author 冷血毒舌
 * @description 针对表【sys_role】的数据库操作Service实现
 * @createDate 2022-10-20 23:58:25
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Autowired
    private RoleResourceService roleResourceService;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private FilterInvocationSecurityMetadataSourceImpl filterInvocationSecurityMetadataSourceImpl;
    ;

    @Override
    public PageResult<RoleDTO> listRole(ConditionVO conditionVO) {
        List<RoleDTO> roleDTOList = roleMapper.listRole(PageUtils.getLimitCurrent(), PageUtils.getSize(), conditionVO);
        Long count = roleMapper.selectCount(new LambdaQueryWrapper<Role>()
                .like(StringUtils.isNotBlank(conditionVO.getKeywords()), Role::getRoleName, conditionVO.getKeywords()));
        return new PageResult<>(roleDTOList, count);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateRole(RoleVO roleVO) {
        // 判断角色名重复
        Role existRole = roleMapper.selectOne(new LambdaQueryWrapper<Role>()
                .select(Role::getId)
                .eq(Role::getRoleName, roleVO.getRoleName()));
        if (Objects.nonNull(existRole) && !existRole.getId().equals(roleVO.getId())) {
            throw new ServiceException(StatusCodeEnum.ROLE.getCode(), StatusCodeEnum.ROLE.getDescription());
        }
        // 保存或更新角色信息
        Role role = Role.builder()
                .id(roleVO.getId())
                .roleName(roleVO.getRoleName())
                .roleLabel(roleVO.getRoleLabel())
                .isDisable(CommonConst.FALSE)
                .build();
        this.saveOrUpdate(role);
        // 更新角色资源关系
        if (Objects.nonNull(roleVO.getResourceIdList())) {
            if (Objects.nonNull(roleVO.getId())) {
                roleResourceService.remove(new LambdaQueryWrapper<RoleResource>()
                        .eq(RoleResource::getRoleId, roleVO.getId()));
            }
            List<RoleResource> roleResourceList = roleVO.getResourceIdList().stream()
                    .map(resourceId -> RoleResource.builder()
                            .roleId(role.getId())
                            .resourceId(resourceId)
                            .build())
                    .collect(Collectors.toList());
            roleResourceService.saveBatch(roleResourceList);
            // 重新加载角色资源信息
            filterInvocationSecurityMetadataSourceImpl.clearDataSource();
        }
        // 更新角色菜单关系
        if (Objects.nonNull(roleVO.getMenuIdList())) {
            if (Objects.nonNull(roleVO.getId())) {
                roleMenuService.remove(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, roleVO.getId()));
            }
            List<RoleMenu> roleMenuList = roleVO.getMenuIdList().stream()
                    .map(menuId -> RoleMenu.builder()
                            .roleId(role.getId())
                            .menuId(menuId)
                            .build())
                    .collect(Collectors.toList());
            roleMenuService.saveBatch(roleMenuList);
        }
        this.saveOrUpdate(role);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteRole(List<Integer> roleIds) {
        this.removeBatchByIds(roleIds);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateDisableStatus(RoleVO roleVO) {
        this.saveOrUpdate(BeanUtil.copyProperties(roleVO, Role.class));
    }


    @Override
    public List<UserRoleDTO> listRoleOptions() {
        // 查询角色列表
        List<Role> roleList = roleMapper.selectList(new LambdaQueryWrapper<Role>()
                .select(Role::getId, Role::getRoleName)
                .eq(Role::getIsDisable, FALSE));
        return BeanCopyUtils.copyList(roleList, UserRoleDTO.class);
    }
}




