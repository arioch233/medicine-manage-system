package com.moumouzhandui.mms.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 网站配置
 *
 * @author 冷血毒舌
 * @create 2022/11/7 15:40
 */
@Data
@ApiModel(description = "网站配置")
public class WebsiteConfigVO {

    /**
     * 用户头像
     */
    @ApiModelProperty(name = "userAvatar", value = "用户头像", required = true, dataType = "String")
    private String userAvatar;

    /**
     * 是否邮箱通知
     */
    @ApiModelProperty(name = "isEmailNotice", value = "是否邮箱通知", required = true, dataType = "Integer")
    private Integer isEmailNotice;

    /**
     * 产品封面
     */
    @ApiModelProperty(name = "cargoCover", value = "产品封面", required = true, dataType = "String")
    private String cargoCover;

    /**
     * 是否开启客服
     */
    @ApiModelProperty(name = "isChatRoom", value = "是否开启客服", required = true, dataType = "Integer")
    private Integer isChatRoom;

    /**
     * websocket地址
     */
    @ApiModelProperty(name = "websocketUrl", value = "websocket地址", required = true, dataType = "String")
    private String websocketUrl;
}
