package com.moumouzhandui.mms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moumouzhandui.mms.entity.Supplier;
import com.moumouzhandui.mms.pojo.dto.SupplierDTO;

import java.util.List;

/**
* @author 冷血毒舌
* @description 针对表【tb_supplier】的数据库操作Service实现
* @createDate 2022-10-27 23:37:23
*/
public interface SupplierMapper extends BaseMapper<Supplier> {

    List<SupplierDTO> listSupplierOptions();
}




