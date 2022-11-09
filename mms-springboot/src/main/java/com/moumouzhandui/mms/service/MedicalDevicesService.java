package com.moumouzhandui.mms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moumouzhandui.mms.entity.MedicalDevices;
import com.moumouzhandui.mms.pojo.dto.CargoDTO;
import com.moumouzhandui.mms.pojo.dto.MedicalDevicesDTO;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.pojo.vo.MedicalDevicesVO;

import java.util.List;

/**
 * @author 冷血毒舌
 * @description 针对表【tb_medical_devices】的数据库操作Service
 * @createDate 2022-11-06 10:05:39
 */
public interface MedicalDevicesService extends IService<MedicalDevices> {

    Page<MedicalDevicesDTO> listMedicalDevices(ConditionVO conditionVO);

    void updateMedicalDevices(MedicalDevicesVO medicalDevicesVO);

    void deleteMedicalDevices(List<Integer> medicalDevicesIds);

    List<CargoDTO> listMedicalDevicesOptions();
}
