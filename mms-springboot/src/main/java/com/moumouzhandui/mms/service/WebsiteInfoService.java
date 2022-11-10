package com.moumouzhandui.mms.service;

import com.moumouzhandui.mms.pojo.dto.CategoryPieChartDTO;
import com.moumouzhandui.mms.pojo.dto.CountDTO;

import java.util.List;
import java.util.Map;

/**
 * @author 冷血毒舌
 * @create 2022/11/9 23:35
 */
public interface WebsiteInfoService {
    CountDTO getCountInfo();

    Map<String, List<CategoryPieChartDTO>> getCategoryPieChart();
}
