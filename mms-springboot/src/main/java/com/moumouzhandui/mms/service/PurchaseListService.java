package com.moumouzhandui.mms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moumouzhandui.mms.common.PageResult;
import com.moumouzhandui.mms.entity.PurchaseList;
import com.moumouzhandui.mms.pojo.dto.PurchaseListDTO;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.pojo.vo.PurchaseListVO;

import java.util.List;

/**
* @author 冷血毒舌
* @description 针对表【tb_purchase_list】的数据库操作Service
* @createDate 2022-11-08 15:19:56
*/
public interface PurchaseListService extends IService<PurchaseList> {
    PageResult<PurchaseListDTO> listPurchase(ConditionVO conditionVO);

    void updatePurchaseList(PurchaseListVO purchaseListVO);

    void deletePurchaseList(List<Long> purchaseListIds);
}
