package com.moumouzhandui.mms.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moumouzhandui.mms.entity.WebsiteConfig;
import com.moumouzhandui.mms.mapper.WebsiteConfigMapper;
import com.moumouzhandui.mms.pojo.vo.WebsiteConfigVO;
import com.moumouzhandui.mms.service.RedisService;
import com.moumouzhandui.mms.service.WebsiteConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

import static com.moumouzhandui.mms.common.CommonConst.DEFAULT_CONFIG_ID;
import static com.moumouzhandui.mms.common.RedisPrefixConst.WEBSITE_CONFIG;
import static com.moumouzhandui.mms.common.RedisPrefixConst.WEBSITE_NAME;

/**
 * @author 冷血毒舌
 * @description 针对表【sys_website_config】的数据库操作Service实现
 * @createDate 2022-11-07 15:37:06
 */
@Service
public class WebsiteConfigServiceImpl extends ServiceImpl<WebsiteConfigMapper, WebsiteConfig>
        implements WebsiteConfigService {

    @Resource
    private WebsiteConfigMapper websiteConfigMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public void updateWebsiteConfig(WebsiteConfigVO websiteConfigVO) {
        // 修改网站配置
        WebsiteConfig websiteConfig = WebsiteConfig.builder()
                .id(DEFAULT_CONFIG_ID)
                .config(JSON.toJSONString(websiteConfigVO))
                .build();
        websiteConfigMapper.updateById(websiteConfig);
        // 删除缓存
        redisService.hDel(WEBSITE_NAME, WEBSITE_CONFIG);
    }

    @Override
    public WebsiteConfigVO getWebsiteConfig() {
        WebsiteConfigVO websiteConfigVO;
        // 从缓存中加载
        Object websiteConfigInRedis = redisService.hGet(WEBSITE_NAME, WEBSITE_CONFIG);
        if (Objects.nonNull(websiteConfigInRedis)) {
            websiteConfigVO = JSON.parseObject(websiteConfigInRedis.toString(), WebsiteConfigVO.class);
        } else {
            // 从数据库中加载
            String config = websiteConfigMapper.selectById(DEFAULT_CONFIG_ID).getConfig();
            websiteConfigVO = JSON.parseObject(config, WebsiteConfigVO.class);
            // 重新设置缓存
            redisService.hSet(WEBSITE_NAME, WEBSITE_CONFIG, config);
        }
        return websiteConfigVO;
    }
}




