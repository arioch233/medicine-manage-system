package com.moumouzhandui.mms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moumouzhandui.mms.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 冷血毒舌
 * @description 针对表【sys_menu】的数据库操作Service实现
 * @createDate 2022-10-20 23:57:05
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> listMenusByUserInfoId(@Param("userInfoId") Integer userInfoId);
}




