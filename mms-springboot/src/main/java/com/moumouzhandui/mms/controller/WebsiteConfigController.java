package com.moumouzhandui.mms.controller;

import com.moumouzhandui.mms.common.Result;
import com.moumouzhandui.mms.pojo.vo.WebsiteConfigVO;
import com.moumouzhandui.mms.service.WebsiteConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 网站配置控制器
 *
 * @author 冷血毒舌
 * @create 2022/11/7 15:36
 */
@Api(tags = "网站配置控制器")
@RestController
@RequestMapping("/config")
public class WebsiteConfigController {

    @Autowired
    private WebsiteConfigService websiteConfigService;

    /**
     * 获取网站配置
     *
     * @return {@link Result<WebsiteConfigVO>} 网站配置
     */
    @ApiOperation(value = "获取网站配置")
    @GetMapping("/website")
    public Result getWebsiteConfig() {
        return Result.success(websiteConfigService.getWebsiteConfig());
    }

    /**
     * 更新网站配置
     *
     * @param websiteConfigVO 网站配置信息
     * @return {@link Result}
     */
    @ApiOperation(value = "更新网站配置")
    @PutMapping("/update")
    public Result saveOrUpdateWebsiteConfig(@Valid @RequestBody WebsiteConfigVO websiteConfigVO) {
        websiteConfigService.updateWebsiteConfig(websiteConfigVO);
        return Result.success();
    }

}
