package com.moumouzhandui.mms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moumouzhandui.mms.common.enums.StatusCodeEnum;
import com.moumouzhandui.mms.entity.Drug;
import com.moumouzhandui.mms.exception.ServiceException;
import com.moumouzhandui.mms.mapper.DrugMapper;
import com.moumouzhandui.mms.pojo.dto.DrugDTO;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.pojo.vo.DrugVO;
import com.moumouzhandui.mms.service.DrugService;
import com.moumouzhandui.mms.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author 冷血毒舌
 * @description 针对表【tb_drug】的数据库操作Service实现
 * @createDate 2022-10-28 21:57:34
 */
@Service
public class DrugServiceImpl extends ServiceImpl<DrugMapper, Drug>
        implements DrugService {

    @Resource
    private DrugMapper drugMapper;

    @Override
    public Page<DrugDTO> listDrug(ConditionVO conditionVO) {
        QueryWrapper<Drug> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        if (conditionVO.getKeywords() != null) {
            queryWrapper.like("drug_trade_name", conditionVO.getKeywords());
        }
        Page<Drug> page = page(new Page<>(conditionVO.getCurrent(), conditionVO.getSize()), queryWrapper);
        Page<DrugDTO> pageDTO = new Page<>();
        BeanCopyUtils.copyIPage(page, pageDTO, DrugDTO.class);
        return pageDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateDrug(DrugVO drugVO) {
        // 判断药品是否已经存在
        Drug existDrug = drugMapper.selectOne(new LambdaQueryWrapper<Drug>()
                .select(Drug::getId)
                .eq(Drug::getDrugNum, drugVO.getDrugNum())
                .eq(Drug::getDrugCommonName, drugVO.getDrugCommonName())
                .eq(Drug::getDrugManufacturer, drugVO.getDrugManufacturer()));
        if (Objects.nonNull(existDrug) && !existDrug.getId().equals(drugVO.getId())) {
            throw new ServiceException(StatusCodeEnum.DRUG.getCode(), StatusCodeEnum.DRUG.getDescription());
        }
        // 若不存在则保存
        Drug drug = BeanUtil.copyProperties(drugVO, Drug.class);
        this.saveOrUpdate(drug);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteDrug(List<Integer> drugIds) {
        this.removeBatchByIds(drugIds);
    }
}




