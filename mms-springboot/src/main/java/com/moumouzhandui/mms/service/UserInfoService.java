package com.moumouzhandui.mms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moumouzhandui.mms.common.PageResult;
import com.moumouzhandui.mms.entity.UserInfo;
import com.moumouzhandui.mms.pojo.dto.UserDetailDTO;
import com.moumouzhandui.mms.pojo.dto.UserOnlineDTO;
import com.moumouzhandui.mms.pojo.vo.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 冷血毒舌
 * @description 针对表【tb_user_info】的数据库操作Service
 * @createDate 2022-11-06 23:47:49
 */
public interface UserInfoService extends IService<UserInfo> {
    /**
     * 修改用户资料
     *
     * @param userInfoVO 用户资料
     */
    void updateUserInfo(UserInfoVO userInfoVO, UserDetailDTO userDetailDTO);

    /**
     * 修改用户头像
     *
     * @return 头像地址
     */
    String updateUserAvatar(String avatarUrl,  UserDetailDTO userDetailDTO);

    /**
     * 绑定用户邮箱
     *
     * @param emailVO 邮箱
     */
    void saveUserEmail(EmailVO emailVO, UserDetailDTO userDetailDTO);

    /**
     * 更新用户角色
     *
     * @param userRoleVO 更新用户角色
     */
    void updateUserRole(UserRoleVO userRoleVO);

    /**
     * 修改用户禁用状态
     *
     * @param userDisableVO 用户禁用信息
     */
    void updateUserDisable(UserDisableVO userDisableVO);

    /**
     * 查看在线用户列表
     *
     * @param conditionVO 条件
     * @return 在线用户列表
     */
    PageResult<UserOnlineDTO> listOnlineUsers(ConditionVO conditionVO);

    /**
     * 下线用户
     *
     * @param userInfoId 用户信息id
     */
    void removeOnlineUser(Integer userInfoId);
}
