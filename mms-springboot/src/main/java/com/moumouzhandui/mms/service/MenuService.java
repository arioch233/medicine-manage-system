package com.moumouzhandui.mms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moumouzhandui.mms.entity.Menu;
import com.moumouzhandui.mms.pojo.dto.LabelOptionDTO;
import com.moumouzhandui.mms.pojo.dto.MenuDTO;
import com.moumouzhandui.mms.pojo.dto.UserMenuDTO;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.pojo.vo.MenuVO;

import java.util.List;

/**
 * @author 冷血毒舌
 * @description 针对表【sys_menu】的数据库操作Service
 * @createDate 2022-10-20 23:57:05
 */
public interface MenuService extends IService<Menu> {

    List<MenuDTO> listMenu(ConditionVO conditionVO);

    void saveOrUpdateMenu(MenuVO menuVO);

    void deleteMenu(Integer menuId);

    List<LabelOptionDTO> listMenuOptions();

    List<UserMenuDTO> listUserMenus();
}
