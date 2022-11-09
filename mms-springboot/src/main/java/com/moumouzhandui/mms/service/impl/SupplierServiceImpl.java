package com.moumouzhandui.mms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moumouzhandui.mms.common.enums.StatusCodeEnum;
import com.moumouzhandui.mms.entity.Supplier;
import com.moumouzhandui.mms.exception.ServiceException;
import com.moumouzhandui.mms.mapper.SupplierMapper;
import com.moumouzhandui.mms.pojo.dto.SupplierDTO;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.pojo.vo.SupplierVO;
import com.moumouzhandui.mms.service.SupplierService;
import com.moumouzhandui.mms.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author 冷血毒舌
 * @description 针对表【tb_supplier】的数据库操作Service实现
 * @createDate 2022-10-27 23:37:23
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier>
        implements SupplierService {

    @Resource
    private SupplierMapper supplierMapper;

    @Override
    public Page<SupplierDTO> listSupplier(ConditionVO conditionVO) {
        QueryWrapper<Supplier> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        if (conditionVO.getKeywords() != null) {
            queryWrapper.like("supplier_name", conditionVO.getKeywords());
        }
        Page<Supplier> page = page(new Page<>(conditionVO.getCurrent(), conditionVO.getSize()), queryWrapper);
        Page<SupplierDTO> pageDTO = new Page<>();
        BeanCopyUtils.copyIPage(page, pageDTO, SupplierDTO.class);
        return pageDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateSupplier(SupplierVO supplierVO) {
        // 判断供货商是否已经存在
        Supplier existSupplier = supplierMapper.selectOne(new LambdaQueryWrapper<Supplier>()
                .select(Supplier::getId)
                .eq(Supplier::getSupplierName, supplierVO.getSupplierName()));
        if (Objects.nonNull(existSupplier) && !existSupplier.getId().equals(supplierVO.getId())) {
            throw new ServiceException(StatusCodeEnum.SUPPLIER.getCode(), StatusCodeEnum.SUPPLIER.getDescription());
        }
        // 若不存在则保存
        Supplier supplier = BeanUtil.copyProperties(supplierVO, Supplier.class);
        this.saveOrUpdate(supplier);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteSupplier(List<Integer> supplierIds) {
        this.removeBatchByIds(supplierIds);
    }


    @Override
    public List<SupplierDTO> listSupplierOptions() {
        return supplierMapper.listSupplierOptions();
    }
}




