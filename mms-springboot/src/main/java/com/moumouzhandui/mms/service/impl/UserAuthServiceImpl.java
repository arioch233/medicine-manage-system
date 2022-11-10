package com.moumouzhandui.mms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moumouzhandui.mms.common.PageResult;
import com.moumouzhandui.mms.common.enums.LoginTypeEnum;
import com.moumouzhandui.mms.common.enums.RoleEnum;
import com.moumouzhandui.mms.entity.UserAuth;
import com.moumouzhandui.mms.entity.UserInfo;
import com.moumouzhandui.mms.entity.UserRole;
import com.moumouzhandui.mms.exception.ServiceException;
import com.moumouzhandui.mms.mapper.UserAuthMapper;
import com.moumouzhandui.mms.mapper.UserInfoMapper;
import com.moumouzhandui.mms.mapper.UserRoleMapper;
import com.moumouzhandui.mms.pojo.dto.EmailDTO;
import com.moumouzhandui.mms.pojo.dto.UserBackDTO;
import com.moumouzhandui.mms.pojo.dto.UserDetailDTO;
import com.moumouzhandui.mms.pojo.vo.AddUserVO;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.pojo.vo.PasswordVO;
import com.moumouzhandui.mms.pojo.vo.UserVO;
import com.moumouzhandui.mms.service.*;
import com.moumouzhandui.mms.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

import static com.moumouzhandui.mms.common.CommonConst.DEFAULT_PASSWORD;
import static com.moumouzhandui.mms.common.RedisPrefixConst.*;
import static com.moumouzhandui.mms.utils.CommonUtils.checkEmail;
import static com.moumouzhandui.mms.utils.CommonUtils.getRandomCode;

/**
 * @author 冷血毒舌
 * @description 针对表【tb_user_auth】的数据库操作Service实现
 * @createDate 2022-11-06 23:47:40
 */
@Slf4j
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth>
        implements UserAuthService {

    @Resource
    private UserAuthMapper userAuthMapper;
    @Resource
    private JavaMailSenderImpl mailSender;
    @Autowired
    private RedisService redisService;
    @Autowired
    private WebsiteConfigService websiteConfigService;

    @Autowired
    private UserInfoService userInfoService;
    @Resource
    private UserInfoMapper userInfoMapper;


    @Autowired
    private UserRoleService userRoleService;
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public void sendCode(String username) {
        // 校验账号是否合法
        if (!checkEmail(username)) {
            throw new ServiceException(600, "请输入正确邮箱");
        }
        // 生成六位随机验证码发送
        String code = getRandomCode();
        // 发送验证码
        EmailDTO emailDTO = EmailDTO.builder()
                .email(username)
                .subject("验证码")
                .content("您的验证码为 " + code + " 有效期15分钟，请不要告诉他人哦！")
                .build();
        //定制纯文本邮件信息SimpleMailMessage
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailSender.getUsername());       //发件人地址
        message.setTo(emailDTO.getEmail());           //收件人地址
        message.setSubject(emailDTO.getSubject()); //邮件标题
        message.setText(emailDTO.getContent());       //邮件内容
        try {
            //发送邮件
            mailSender.send(message);
            log.info("验证码邮件发送成功");
        } catch (MailException e) {
            log.error("验证码邮件发送失败" + e.getMessage());
            e.printStackTrace();
        }
        // 将验证码存入redis，设置过期时间为15分钟
        redisService.hSet(WEBSITE_NAME, USER_CODE_KEY + username, code, CODE_EXPIRE_TIME);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(UserVO user) {
        // 校验账号是否合法
        if (checkUser(user)) {
            throw new ServiceException(600, "该邮箱已被注册");
        }
        // 新增用户信息
        UserInfo userInfo = UserInfo.builder()
                .email(user.getUsername())
                .nickname(user.getNickname())
                .avatar(websiteConfigService.getWebsiteConfig().getUserAvatar())
                .build();
        userInfoMapper.insert(userInfo);
        // 绑定用户角色
        UserRole userRole = UserRole.builder()
                .userId(userInfo.getId())
                .roleId(RoleEnum.EMPLOYEE.getRoleId())
                .build();
        userRoleMapper.insert(userRole);
        // 新增用户账号
        UserAuth userAuth = UserAuth.builder()
                .userInfoId(userInfo.getId())
                .username(user.getUsername())
                .loginType(LoginTypeEnum.EMAIL.getType())
                .password(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()))
                .build();
        userAuthMapper.insert(userAuth);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addUserAccount(AddUserVO addUserVO) {
        //查询用户名是否存在
        UserAuth userAuth = userAuthMapper.selectOne(new LambdaQueryWrapper<UserAuth>()
                .select(UserAuth::getUsername)
                .eq(UserAuth::getUsername, addUserVO.getUsername()));
        // 校验账号是否合法
        if (Objects.nonNull(userAuth)) {
            throw new ServiceException(600, "该邮箱已被注册");
        }
        // 新增用户信息
        UserInfo userInfo = UserInfo.builder()
                .email(addUserVO.getUsername())
                .nickname(addUserVO.getNickname())
                .avatar(websiteConfigService.getWebsiteConfig().getUserAvatar())
                .build();
        userInfoMapper.insert(userInfo);
        // 绑定用户角色
        for (Integer roleId : addUserVO.getRoleId()) {
            UserRole userRole = UserRole.builder()
                    .userId(userInfo.getId())
                    .roleId(roleId)
                    .build();
            userRoleMapper.insert(userRole);
        }
        // 新增用户账号
        UserAuth addUserAuth = UserAuth.builder()
                .userInfoId(userInfo.getId())
                .username(addUserVO.getUsername())
                .loginType(LoginTypeEnum.EMAIL.getType())
                .password(BCrypt.hashpw(DEFAULT_PASSWORD, BCrypt.gensalt()))
                .build();
        userAuthMapper.insert(addUserAuth);
    }

    /**
     * 校验用户数据是否合法
     *
     * @param user 用户数据
     * @return 结果
     */
    private Boolean checkUser(UserVO user) {
        if (!user.getCode().equals(redisService.hGet(WEBSITE_NAME, USER_CODE_KEY + user.getUsername()))) {
            throw new ServiceException(600, "验证码错误！");
        }
        //查询用户名是否存在
        UserAuth userAuth = userAuthMapper.selectOne(new LambdaQueryWrapper<UserAuth>()
                .select(UserAuth::getUsername)
                .eq(UserAuth::getUsername, user.getUsername()));
        return Objects.nonNull(userAuth);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePassword(UserVO user) {
        // 校验账号是否合法
        if (!checkUser(user)) {
            throw new ServiceException(600, "邮箱尚未注册！");
        }
        // 根据用户名修改密码
        userAuthMapper.update(new UserAuth(), new LambdaUpdateWrapper<UserAuth>()
                .set(UserAuth::getPassword, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()))
                .eq(UserAuth::getUsername, user.getUsername()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateAdminPassword(PasswordVO passwordVO, UserDetailDTO userDetailDTO) {
        // 查询旧密码是否正确
        UserAuth user = userAuthMapper.selectOne(new LambdaQueryWrapper<UserAuth>()
                .eq(UserAuth::getId, userDetailDTO.getId()));
        // 正确则修改密码，错误则提示不正确
        if (Objects.nonNull(user) && BCrypt.checkpw(passwordVO.getOldPassword(), user.getPassword())) {
            UserAuth userAuth = UserAuth.builder()
                    .id(userDetailDTO.getId())
                    .password(BCrypt.hashpw(passwordVO.getNewPassword(), BCrypt.gensalt()))
                    .build();
            userAuthMapper.updateById(userAuth);
        } else {
            throw new ServiceException(600, "旧密码不正确");
        }
    }

    @Override
    public PageResult<UserBackDTO> listUsers(ConditionVO condition) {
        Long count = userAuthMapper.userCount(condition);
        List<UserBackDTO> userBackDTOList = userAuthMapper.listUsers(PageUtils.getLimitCurrent(), PageUtils.getSize(), condition);
        return new PageResult<>(userBackDTOList, count);
    }
}




