package com.moumouzhandui.mms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moumouzhandui.mms.entity.Drug;
import com.moumouzhandui.mms.pojo.dto.CargoDTO;

import java.util.List;

/**
 * @author Administrator
 * @description 针对表【tb_drug】的数据库操作Mapper
 * @createDate 2022-11-05 15:40:16
 */
public interface DrugMapper extends BaseMapper<Drug> {

    List<CargoDTO> listDrugOptions();
}




