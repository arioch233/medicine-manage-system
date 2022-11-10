package com.moumouzhandui.mms.controller;

import com.moumouzhandui.mms.common.Result;
import com.moumouzhandui.mms.pojo.vo.ConditionVO;
import com.moumouzhandui.mms.service.WebsiteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 网站信息控制器
 *
 * @author 冷血毒舌
 * @create 2022/11/9 23:30
 */
@RestController
@RequestMapping("/website")
public class WebsiteInfoController {

    @Autowired
    private WebsiteInfoService websiteInfoService;

    @GetMapping("/info")
    public Result websiteInfo() {
        return Result.success(websiteInfoService.getCountInfo());
    }

    @GetMapping("/pie-chart")
    public Result CategoryPieChart() {

        return Result.success(websiteInfoService.getCategoryPieChart());
    }


}
