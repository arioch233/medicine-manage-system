package com.moumouzhandui.mms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moumouzhandui.mms.common.PageResult;
import com.moumouzhandui.mms.entity.UserAuth;
import com.moumouzhandui.mms.pojo.dto.UserBackDTO;
import com.moumouzhandui.mms.pojo.dto.UserDetailDTO;
import com.moumouzhandui.mms.pojo.vo.AddUserVO;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.pojo.vo.PasswordVO;
import com.moumouzhandui.mms.pojo.vo.UserVO;

import javax.mail.MessagingException;

/**
 * @author 冷血毒舌
 * @description 针对表【tb_user_auth】的数据库操作Service
 * @createDate 2022-11-06 23:47:40
 */
public interface UserAuthService extends IService<UserAuth> {
    /**
     * 发送邮箱验证码
     *
     * @param username 邮箱号
     */
    void sendCode(String username) throws MessagingException;

    /**
     * 用户注册
     *
     * @param user 用户对象
     */
    void register(UserVO user);

    /**
     * 修改密码
     *
     * @param user 用户对象
     */
    void updatePassword(UserVO user);

    /**
     * 修改管理员密码
     *
     * @param passwordVO 密码对象
     */
    void updateAdminPassword(PasswordVO passwordVO, UserDetailDTO userDetailDTO);


    /**
     * 查询后台用户列表
     *
     * @param condition 条件
     * @return 用户列表
     */
    PageResult<UserBackDTO> listUsers(ConditionVO condition);

    /**
     * 用户手动添加
     *
     * @param addUserVO
     */
    void addUserAccount(AddUserVO addUserVO);
}
