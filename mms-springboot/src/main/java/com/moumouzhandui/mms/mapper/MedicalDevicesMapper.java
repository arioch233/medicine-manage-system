package com.moumouzhandui.mms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moumouzhandui.mms.entity.MedicalDevices;
import com.moumouzhandui.mms.pojo.dto.CargoDTO;

import java.util.List;

/**
* @author 冷血毒舌
* @description 针对表【tb_medical_devices】的数据库操作Service实现
* @createDate 2022-11-06 10:05:39
*/
public interface MedicalDevicesMapper extends BaseMapper<MedicalDevices> {

    List<CargoDTO> listMedicalDevicesOptions();
}




