package com.moumouzhandui.mms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.moumouzhandui.mms.entity.Supplier;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moumouzhandui.mms.pojo.dto.SupplierDTO;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.pojo.vo.SupplierVO;

import java.util.List;

/**
* @author 冷血毒舌
* @description 针对表【tb_supplier】的数据库操作Service
* @createDate 2022-10-27 23:37:23
*/
public interface SupplierService extends IService<Supplier> {

    Page<SupplierDTO> listSupplier(ConditionVO conditionVO);

    void updateSupplier(SupplierVO supplierVO);

    void deleteSupplier(List<Integer> supplierIds);
}
