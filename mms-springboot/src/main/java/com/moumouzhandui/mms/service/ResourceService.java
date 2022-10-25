package com.moumouzhandui.mms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moumouzhandui.mms.entity.IResource;
import com.moumouzhandui.mms.pojo.dto.LabelOptionDTO;
import com.moumouzhandui.mms.pojo.dto.ResourceDTO;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.pojo.vo.ResourceVO;

import java.util.List;

/**
 * @author 冷血毒舌
 * @description 针对表【sys_resource】的数据库操作Service
 * @createDate 2022-10-21 00:00:24
 */
public interface ResourceService extends IService<IResource> {

    List<ResourceDTO> listResource(ConditionVO conditionVO);

    void updateResource(ResourceVO resourceVO);

    void deleteResource(Integer resourceId);


    List<LabelOptionDTO> listResourceOption();
}
