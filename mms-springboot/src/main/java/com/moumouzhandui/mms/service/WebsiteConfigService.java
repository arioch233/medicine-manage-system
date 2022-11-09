package com.moumouzhandui.mms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.moumouzhandui.mms.entity.WebsiteConfig;
import com.moumouzhandui.mms.pojo.vo.WebsiteConfigVO;

/**
* @author 冷血毒舌
* @description 针对表【sys_website_config】的数据库操作Service
* @createDate 2022-11-07 15:37:06
*/
public interface WebsiteConfigService extends IService<WebsiteConfig> {

    /**
     * 保存或更新网站配置
     *
     * @param websiteConfigVO 网站配置
     */
    void updateWebsiteConfig(WebsiteConfigVO websiteConfigVO);

    /**
     * 获取网站配置
     *
     * @return {@link WebsiteConfigVO} 网站配置
     */
    WebsiteConfigVO getWebsiteConfig();
}
