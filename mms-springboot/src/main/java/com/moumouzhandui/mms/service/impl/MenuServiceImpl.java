package com.moumouzhandui.mms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moumouzhandui.mms.common.enums.StatusCodeEnum;
import com.moumouzhandui.mms.entity.Menu;
import com.moumouzhandui.mms.entity.RoleMenu;
import com.moumouzhandui.mms.exception.ServiceException;
import com.moumouzhandui.mms.mapper.MenuMapper;
import com.moumouzhandui.mms.mapper.RoleMenuMapper;
import com.moumouzhandui.mms.pojo.dto.LabelOptionDTO;
import com.moumouzhandui.mms.pojo.dto.MenuDTO;
import com.moumouzhandui.mms.pojo.dto.UserMenuDTO;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.pojo.vo.MenuVO;
import com.moumouzhandui.mms.service.MenuService;
import com.moumouzhandui.mms.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static com.moumouzhandui.mms.common.CommonConst.COMPONENT;
import static com.moumouzhandui.mms.common.CommonConst.TRUE;

/**
 * @author 冷血毒舌
 * @description 针对表【sys_menu】的数据库操作Service实现
 * @createDate 2022-10-20 23:57:05
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
        implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<MenuDTO> listMenu(ConditionVO conditionVO) {
        // 查询菜单数据
        List<Menu> menuList = menuMapper.selectList(new LambdaQueryWrapper<Menu>()
                .like(StrUtil.isNotBlank(conditionVO.getKeywords()), Menu::getMenuName, conditionVO.getKeywords()));
        // 获取目录列表
        List<Menu> catalogList = listCatalog(menuList);
        // 获取目录下的子菜单列表
        Map<Integer, List<Menu>> childrenMenuMap = getMenuMap(menuList);
        System.out.println(childrenMenuMap);
        // 拼接目录菜单数据
        List<MenuDTO> menuDTOList = catalogList.stream()
                .map(item -> {
                    MenuDTO menuDTO = BeanUtil.copyProperties(item, MenuDTO.class);
                    // 获取目录下的菜单排序
                    List<MenuDTO> list = BeanCopyUtils.copyList(childrenMenuMap.get(item.getId()), MenuDTO.class).stream()
                            .sorted(Comparator.comparing(MenuDTO::getOrderNum))
                            .toList();
                    menuDTO.setChildren(list);
                    childrenMenuMap.remove(item.getId());
                    return menuDTO;
                }).sorted(Comparator.comparing(MenuDTO::getOrderNum))
                .toList();
        // 若还有菜单未取出则拼接
        if (CollectionUtil.isNotEmpty(childrenMenuMap)) {
            List<Menu> childrenList = new ArrayList<>();
            childrenMenuMap.values().forEach(childrenList::addAll);
            List<MenuDTO> childrenMenuDTOList = childrenList.stream()
                    .map(item -> BeanUtil.copyProperties(item, MenuDTO.class))
                    .sorted(Comparator.comparing(MenuDTO::getOrderNum))
                    .toList();
            menuDTOList.addAll(childrenMenuDTOList);
        }
        return menuDTOList;
    }

    /**
     * 获取目录列表
     *
     * @param menuList
     * @return
     */
    private List<Menu> listCatalog(List<Menu> menuList) {
        return menuList.stream()
                .filter(item -> Objects.isNull(item.getParentId()))
                .sorted(Comparator.comparing(Menu::getOrderNum))
                .toList();
    }

    /**
     * 获取目录下菜单列表
     *
     * @param menuList
     * @return
     */
    private Map<Integer, List<Menu>> getMenuMap(List<Menu> menuList) {
        return menuList.stream()
                .filter(item -> Objects.nonNull(item.getParentId()))
                .collect(Collectors.groupingBy(Menu::getParentId));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateMenu(MenuVO menuVO) {
        Menu menu = BeanUtil.copyProperties(menuVO, Menu.class);
        this.saveOrUpdate(menu);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteMenu(Integer menuId) {
        // 查询是否有角色关联
        Long count = roleMenuMapper.selectCount(new LambdaQueryWrapper<RoleMenu>()
                .eq(RoleMenu::getMenuId, menuId));
        if (count > 0) {
            throw new ServiceException(StatusCodeEnum.MENU_ROLE.getCode(), StatusCodeEnum.MENU_ROLE.getDescription());
        }
        // 查询子菜单
        List<Integer> menuIdList = menuMapper.selectList(new LambdaQueryWrapper<Menu>()
                        .select(Menu::getId)
                        .eq(Menu::getParentId, menuId))
                .stream()
                .map(Menu::getId)
                .collect(Collectors.toList());
        menuIdList.add(menuId);
        this.removeBatchByIds(menuIdList);
    }

    @Override
    public List<LabelOptionDTO> listMenuOptions() {
        // 查询菜单数据
        List<Menu> menuList = menuMapper.selectList(new LambdaQueryWrapper<Menu>()
                .select(Menu::getId, Menu::getMenuName, Menu::getParentId, Menu::getOrderNum));
        // 获取目录列表
        List<Menu> catalogList = listCatalog(menuList);
        // 获取目录下的子菜单
        Map<Integer, List<Menu>> childrenMap = getMenuMap(menuList);
        // 组装目录菜单数据
        return catalogList.stream().map(item -> {
            // 获取目录下的菜单排序
            List<LabelOptionDTO> list = new ArrayList<>();
            List<Menu> children = childrenMap.get(item.getId());
            if (CollectionUtils.isNotEmpty(children)) {
                list = children.stream()
                        .sorted(Comparator.comparing(Menu::getOrderNum))
                        .map(menu -> LabelOptionDTO.builder()
                                .id(menu.getId())
                                .label(menu.getMenuName())
                                .build())
                        .collect(Collectors.toList());
            }
            return LabelOptionDTO.builder()
                    .id(item.getId())
                    .label(item.getMenuName())
                    .children(list)
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public List<UserMenuDTO> listUserMenus() {
        // 查询用户菜单信息
        List<Menu> menuList = menuMapper.listMenusByUserInfoId(1);
        // 获取目录列表
        List<Menu> catalogList = listCatalog(menuList);
        // 获取目录下的子菜单
        Map<Integer, List<Menu>> childrenMap = getMenuMap(menuList);
        // 转换前端菜单格式
        return convertUserMenuList(catalogList, childrenMap);
    }

    /**
     * 转换用户菜单格式
     *
     * @param catalogList 目录
     * @param childrenMap 子菜单
     */
    private List<UserMenuDTO> convertUserMenuList(List<Menu> catalogList, Map<Integer, List<Menu>> childrenMap) {
        return catalogList.stream().map(item -> {
            // 获取目录
            UserMenuDTO userMenuDTO = new UserMenuDTO();
            List<UserMenuDTO> list = new ArrayList<>();
            // 获取目录下的子菜单
            List<Menu> children = childrenMap.get(item.getId());
            if (CollectionUtils.isNotEmpty(children)) {
                // 多级菜单处理
                userMenuDTO = BeanUtil.copyProperties(item, UserMenuDTO.class);
                userMenuDTO.setName(item.getMenuName());
                list = children.stream()
                        .sorted(Comparator.comparing(Menu::getOrderNum))
                        .map(menu -> {
                            UserMenuDTO dto = BeanUtil.copyProperties(menu, UserMenuDTO.class);
                            dto.setName(menu.getMenuName());
                            dto.setHidden(menu.getIsHidden().equals(TRUE));
                            return dto;
                        })
                        .collect(Collectors.toList());
            } else {
                // 一级菜单处理
                userMenuDTO.setPath(item.getPath());
                userMenuDTO.setComponent(COMPONENT);
                list.add(UserMenuDTO.builder()
                        .path("")
                        .name(item.getMenuName())
                        .icon(item.getIcon())
                        .component(item.getComponent())
                        .build());
            }
            userMenuDTO.setHidden(item.getIsHidden().equals(TRUE));
            userMenuDTO.setChildren(list);
            return userMenuDTO;
        }).collect(Collectors.toList());
    }
}




