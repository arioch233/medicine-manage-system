package com.moumouzhandui.mms.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moumouzhandui.mms.common.PageResult;
import com.moumouzhandui.mms.entity.Drug;
import com.moumouzhandui.mms.entity.MedicalDevices;
import com.moumouzhandui.mms.entity.PurchaseList;
import com.moumouzhandui.mms.mapper.*;
import com.moumouzhandui.mms.pojo.dto.PurchaseListDTO;
import com.moumouzhandui.mms.pojo.vo.CargoInfo;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.pojo.vo.PurchaseListVO;
import com.moumouzhandui.mms.service.PurchaseListService;
import com.moumouzhandui.mms.utils.GenerateOrderUtils;
import com.moumouzhandui.mms.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 冷血毒舌
 * @description 针对表【tb_purchase_list】的数据库操作Service实现
 * @createDate 2022-11-08 15:19:56
 */
@Slf4j
@Service
public class PurchaseListServiceImpl extends ServiceImpl<PurchaseListMapper, PurchaseList> implements PurchaseListService {

    @Resource
    private DrugMapper drugMapper;

    @Resource
    private MedicalDevicesMapper medicalDevicesMapper;

    @Resource
    private PurchaseListMapper purchaseListMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private SupplierMapper supplierMapper;

    @Override
    public PageResult<PurchaseListDTO> listPurchase(ConditionVO conditionVO) {
        // 记录条数
        Long count = purchaseListMapper.selectCount(null);
        // 查询数据库并封装数据
        List<PurchaseList> purchaseLists = purchaseListMapper.purchaseLists(PageUtils.getLimitCurrent(), PageUtils.getSize(), conditionVO);
        List<PurchaseListDTO> purchaseListDTOList = new ArrayList<PurchaseListDTO>();
        for (PurchaseList purchaseList : purchaseLists) {
            purchaseListDTOList.add(PurchaseListDTO.builder()
                    .id(purchaseList.getId())
                    .employeeName(userInfoMapper.selectById(purchaseList.getEmployeeId()).getNickname())
                    .supplierName(supplierMapper.selectById(purchaseList.getSupplierId()).getSupplierName())
                    .cargoList(JSON.parseArray(purchaseList.getCargoList(), CargoInfo.class).stream()
                            .map(cargoInfo -> getCargoName(cargoInfo)).collect(Collectors.toList()))
                    .createTime(purchaseList.getCreateTime())
                    .build());
        }
        // 构造分页对象
        return PageResult.<PurchaseListDTO>builder()
                .recordList(purchaseListDTOList)
                .count(count).build();
    }

    /**
     * 获取货物商品名
     *
     * @param cargoInfo
     * @return
     */
    private CargoInfo getCargoName(CargoInfo cargoInfo) {
        Drug drug = drugMapper.selectOne(new LambdaQueryWrapper<Drug>().eq(Drug::getDrugNum, cargoInfo.getCargoId()));
        if (!Objects.isNull(drug)) {
            cargoInfo.setCargoName(drug.getDrugTradeName());
        } else {
            MedicalDevices medicalDevices = medicalDevicesMapper.selectOne(new LambdaQueryWrapper<MedicalDevices>().eq(MedicalDevices::getMedicalDevicesNum, cargoInfo.getCargoId()));
            cargoInfo.setCargoName(medicalDevices.getMedicalDevicesName());
        }
        return cargoInfo;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePurchaseList(PurchaseListVO purchaseListVO) {
        // 生成主键
        String key = GenerateOrderUtils.generateOrder();
        Long primaryKey = Long.valueOf(key);
        // 更新库存
        List<CargoInfo> cargoInfos = purchaseListVO.getCargoList();
        for (CargoInfo cargoInfo : cargoInfos) {
            Drug drug = drugMapper.selectOne(new LambdaQueryWrapper<Drug>().eq(Drug::getDrugNum, cargoInfo.getCargoId()));
            if (!Objects.isNull(drug)) {
                drug.setDrugQuantity(cargoInfo.getCargoQuantity() + drug.getDrugQuantity());
                drugMapper.updateById(drug);
                continue;
            }
            MedicalDevices medicalDevices = medicalDevicesMapper.selectOne(new LambdaQueryWrapper<MedicalDevices>().eq(MedicalDevices::getMedicalDevicesNum, cargoInfo.getCargoId()));
            medicalDevices.setMedicalDevicesQuantity(cargoInfo.getCargoQuantity() + medicalDevices.getMedicalDevicesQuantity());
            medicalDevicesMapper.updateById(medicalDevices);
        }
        // 保存订单记录
        PurchaseList purchaseList = PurchaseList.builder().id(primaryKey).employeeId(purchaseListVO.getEmployeeId()).supplierId(purchaseListVO.getSupplierId()).cargoList(JSON.toJSONString(purchaseListVO.getCargoList())).build();
        log.error(purchaseList.toString());
        this.save(purchaseList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deletePurchaseList(List<Long> purchaseListIds) {
        this.removeBatchByIds(purchaseListIds);
    }
}




