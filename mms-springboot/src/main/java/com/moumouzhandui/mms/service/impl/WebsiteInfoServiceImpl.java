package com.moumouzhandui.mms.service.impl;

import com.moumouzhandui.mms.mapper.DrugMapper;
import com.moumouzhandui.mms.mapper.MedicalDevicesMapper;
import com.moumouzhandui.mms.mapper.PurchaseListMapper;
import com.moumouzhandui.mms.mapper.UserAuthMapper;
import com.moumouzhandui.mms.pojo.dto.CategoryPieChartDTO;
import com.moumouzhandui.mms.pojo.dto.CountDTO;
import com.moumouzhandui.mms.service.RedisService;
import com.moumouzhandui.mms.service.WebsiteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 冷血毒舌
 * @create 2022/11/9 23:36
 */
@Service
public class WebsiteInfoServiceImpl implements WebsiteInfoService {

    @Resource
    private DrugMapper drugMapper;

    @Resource
    private MedicalDevicesMapper medicalDevicesMapper;

    @Resource
    private UserAuthMapper userAuthMapper;

    @Resource
    private PurchaseListMapper purchaseListMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public CountDTO getCountInfo() {
        return CountDTO.builder()
                .drugCount(drugMapper.selectCount(null))
                .medicalDeviceCount(medicalDevicesMapper.selectCount(null))
                .orderCount(purchaseListMapper.selectCount(null))
                .userCount(userAuthMapper.selectCount(null))
                .build();
    }

    @Override
    public Map<String, List<CategoryPieChartDTO>> getCategoryPieChart() {
        Map<String, List<CategoryPieChartDTO>> categoryPieChart = new HashMap<>();
        List<CategoryPieChartDTO> drugCategory = drugMapper.nameAndCount();
        List<CategoryPieChartDTO> medicalDeviceCategory = medicalDevicesMapper.nameAndCount();
        categoryPieChart.put("drugCategory", drugCategory);
        categoryPieChart.put("medicalDeviceCategory", medicalDeviceCategory);
        return categoryPieChart;
    }
}
