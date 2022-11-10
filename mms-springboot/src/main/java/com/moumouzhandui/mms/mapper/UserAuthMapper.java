package com.moumouzhandui.mms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moumouzhandui.mms.entity.UserAuth;
import com.moumouzhandui.mms.pojo.dto.UserBackDTO;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 冷血毒舌
 * @description 针对表【tb_user_auth】的数据库操作Service实现
 * @createDate 2022-11-06 23:47:40
 */

public interface UserAuthMapper extends BaseMapper<UserAuth> {

    List<UserBackDTO> listUsers(@Param("current") Long current, @Param("size") Long size, @Param("condition") ConditionVO condition);

    Long userCount(@Param("condition") ConditionVO condition);
}




