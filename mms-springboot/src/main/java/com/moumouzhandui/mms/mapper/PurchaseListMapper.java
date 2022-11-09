package com.moumouzhandui.mms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.moumouzhandui.mms.entity.PurchaseList;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 冷血毒舌
 * @description 针对表【tb_purchase_list】的数据库操作Service实现
 * @createDate 2022-11-08 15:19:56
 */
public interface PurchaseListMapper extends BaseMapper<PurchaseList> {
    List<PurchaseList> purchaseLists(@Param("current") Long current, @Param("size") Long size, @Param("condition") ConditionVO condition);
}




