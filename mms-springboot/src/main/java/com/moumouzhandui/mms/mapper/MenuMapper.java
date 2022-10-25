package com.moumouzhandui.mms.mapper;

import com.moumouzhandui.mms.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 冷血毒舌
* @description 针对表【sys_menu】的数据库操作Service实现
* @createDate 2022-10-20 23:57:05
*/
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> listMenusByUserInfoId(@Param("userInfoId") Integer userInfoId);
}




