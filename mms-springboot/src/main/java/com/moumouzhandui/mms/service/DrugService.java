package com.moumouzhandui.mms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.moumouzhandui.mms.entity.Drug;
import com.moumouzhandui.mms.pojo.dto.DrugDTO;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.pojo.vo.DrugVO;

import java.util.List;

/**
 * @author 冷血毒舌
 * @description 针对表【tb_drug】的数据库操作Service
 * @createDate 2022-10-28 21:57:34
 */
public interface DrugService extends IService<Drug> {

    Page<DrugDTO> listDrug(ConditionVO conditionVO);

    void updateDrug(DrugVO drugVO);

    void deleteDrug(List<Integer> drugIds);
}
