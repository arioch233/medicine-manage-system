package com.moumouzhandui.mms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moumouzhandui.mms.common.enums.StatusCodeEnum;
import com.moumouzhandui.mms.entity.MedicalDevices;
import com.moumouzhandui.mms.exception.ServiceException;
import com.moumouzhandui.mms.mapper.MedicalDevicesMapper;
import com.moumouzhandui.mms.pojo.dto.MedicalDevicesDTO;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.pojo.vo.MedicalDevicesVO;
import com.moumouzhandui.mms.service.MedicalDevicesService;
import com.moumouzhandui.mms.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author 冷血毒舌
 * @description 针对表【tb_medical_devices】的数据库操作Service实现
 * @createDate 2022-11-06 10:05:39
 */
@Service
public class MedicalDevicesServiceImpl extends ServiceImpl<MedicalDevicesMapper, MedicalDevices>
        implements MedicalDevicesService {


    @Resource
    private MedicalDevicesMapper medicalDevicesMapper;

    @Override
    public Page<MedicalDevicesDTO> listMedicalDevices(ConditionVO conditionVO) {
        QueryWrapper<MedicalDevices> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        if (conditionVO.getKeywords() != null) {
            queryWrapper.like("medical_devices_name", conditionVO.getKeywords());
        }
        Page<MedicalDevices> page = page(new Page<>(conditionVO.getCurrent(), conditionVO.getSize()), queryWrapper);
        Page<MedicalDevicesDTO> pageDTO = new Page<>();
        BeanCopyUtils.copyIPage(page, pageDTO, MedicalDevicesDTO.class);
        return pageDTO;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateMedicalDevices(MedicalDevicesVO medicalDevicesVO) {
        // 判断药品是否已经存在
        MedicalDevices existMedicalDevices = medicalDevicesMapper.selectOne(new LambdaQueryWrapper<MedicalDevices>()
                .select(MedicalDevices::getId)
                .eq(MedicalDevices::getMedicalDevicesNum, medicalDevicesVO.getMedicalDevicesNum())
                .eq(MedicalDevices::getMedicalDevicesName, medicalDevicesVO.getMedicalDevicesName())
                .eq(MedicalDevices::getMedicalDevicesManufacturer, medicalDevicesVO.getMedicalDevicesManufacturer()));
        if (Objects.nonNull(existMedicalDevices) && !existMedicalDevices.getId().equals(medicalDevicesVO.getId())) {
            throw new ServiceException(StatusCodeEnum.DRUG.getCode(), StatusCodeEnum.DRUG.getDescription());
        }
        // 若不存在则保存
        MedicalDevices medicalDevices = BeanUtil.copyProperties(medicalDevicesVO, MedicalDevices.class);
        this.saveOrUpdate(medicalDevices);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteMedicalDevices(List<Integer> medicalDevicesIds) {
        this.removeBatchByIds(medicalDevicesIds);
    }
}




