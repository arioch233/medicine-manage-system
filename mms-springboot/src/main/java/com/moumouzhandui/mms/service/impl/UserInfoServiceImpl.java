package com.moumouzhandui.mms.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moumouzhandui.mms.common.PageResult;
import com.moumouzhandui.mms.entity.UserInfo;
import com.moumouzhandui.mms.entity.UserRole;
import com.moumouzhandui.mms.exception.ServiceException;
import com.moumouzhandui.mms.mapper.UserInfoMapper;
import com.moumouzhandui.mms.pojo.dto.UserDetailDTO;
import com.moumouzhandui.mms.pojo.dto.UserOnlineDTO;
import com.moumouzhandui.mms.pojo.vo.*;
import com.moumouzhandui.mms.service.RedisService;
import com.moumouzhandui.mms.service.UserInfoService;
import com.moumouzhandui.mms.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.moumouzhandui.mms.common.RedisPrefixConst.USER_CODE_KEY;
import static com.moumouzhandui.mms.common.RedisPrefixConst.WEBSITE_NAME;
import static com.moumouzhandui.mms.utils.PageUtils.getLimitCurrent;
import static com.moumouzhandui.mms.utils.PageUtils.getSize;

/**
 * @author 冷血毒舌
 * @description 针对表【tb_user_info】的数据库操作Service实现
 * @createDate 2022-11-06 23:47:49
 */
@Slf4j
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
        implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;
    @Autowired
    private SessionRegistry sessionRegistry;
    @Autowired
    private RedisService redisService;

    @Autowired
    private UserRoleService userRoleService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUserInfo(UserInfoVO userInfoVO, UserDetailDTO userDetailDTO) {

        // 封装用户信息
        UserInfo userInfo = UserInfo.builder()
                .id(userDetailDTO.getUserInfoId())
                .nickname(userInfoVO.getNickname())
                .build();
        userInfoMapper.updateById(userInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String updateUserAvatar(String avatarUrl, UserDetailDTO userDetailDTO) {
        // 更新用户信息
        UserInfo userInfo = UserInfo.builder()
                .id(userDetailDTO.getUserInfoId())
                .avatar(avatarUrl)
                .build();
        userInfoMapper.updateById(userInfo);
        return avatarUrl;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveUserEmail(EmailVO emailVO, UserDetailDTO userDetailDTO) {
        if (!emailVO.getCode().equals(redisService.hGet(WEBSITE_NAME, USER_CODE_KEY + emailVO.getEmail()).toString())) {
            throw new ServiceException(600, "验证码错误！");
        }
        UserInfo userInfo = UserInfo.builder()
                .id(userDetailDTO.getUserInfoId())
                .email(emailVO.getEmail())
                .build();
        userInfoMapper.updateById(userInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUserRole(UserRoleVO userRoleVO) {
        // 更新用户角色和昵称
        UserInfo userInfo = UserInfo.builder()
                .id(userRoleVO.getUserInfoId())
                .nickname(userRoleVO.getNickname())
                .build();
        userInfoMapper.updateById(userInfo);
        // 删除用户角色重新添加
        userRoleService.remove(new LambdaQueryWrapper<UserRole>()
                .eq(UserRole::getUserId, userRoleVO.getUserInfoId()));
        List<UserRole> userRoleList = userRoleVO.getRoleIdList().stream()
                .map(roleId -> UserRole.builder()
                        .roleId(roleId)
                        .userId(userRoleVO.getUserInfoId())
                        .build())
                .collect(Collectors.toList());
        userRoleService.saveBatch(userRoleList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUserDisable(UserDisableVO userDisableVO) {
        // 更新用户禁用状态
        UserInfo userInfo = UserInfo.builder()
                .id(userDisableVO.getId())
                .isDisable(userDisableVO.getIsDisable())
                .build();
        userInfoMapper.updateById(userInfo);
    }

    @Override
    public PageResult<UserOnlineDTO> listOnlineUsers(ConditionVO conditionVO) {
        // 获取security在线session
        List<UserOnlineDTO> userOnlineDTOList = sessionRegistry.getAllPrincipals().stream()
                .filter(item -> sessionRegistry.getAllSessions(item, false).size() > 0)
                .map(item -> JSON.parseObject(JSON.toJSONString(item), UserOnlineDTO.class))
                .filter(item -> StringUtils.isBlank(conditionVO.getKeywords()) || item.getNickname().contains(conditionVO.getKeywords()))
                .sorted(Comparator.comparing(UserOnlineDTO::getLastLoginTime).reversed())
                .collect(Collectors.toList());
        // 执行分页
        int fromIndex = getLimitCurrent().intValue();
        int size = getSize().intValue();
        int toIndex = userOnlineDTOList.size() - fromIndex > size ? fromIndex + size : userOnlineDTOList.size();
        List<UserOnlineDTO> userOnlineList = userOnlineDTOList.subList(fromIndex, toIndex);
        return new PageResult<UserOnlineDTO>(userOnlineList, (long) userOnlineDTOList.size());
    }

    @Override
    public void removeOnlineUser(Integer userInfoId) {
        // 获取用户session
        List<Object> userInfoList = sessionRegistry.getAllPrincipals().stream().filter(item -> {
            UserDetailDTO userDetailDTO = (UserDetailDTO) item;
            return userDetailDTO.getUserInfoId().equals(userInfoId);
        }).collect(Collectors.toList());
        List<SessionInformation> allSessions = new ArrayList<>();
        userInfoList.forEach(item -> allSessions.addAll(sessionRegistry.getAllSessions(item, false)));
        // 注销session
        allSessions.forEach(SessionInformation::expireNow);
    }

}




