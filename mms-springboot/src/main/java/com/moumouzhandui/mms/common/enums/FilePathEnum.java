package com.moumouzhandui.mms.common.enums;

/**
 * 文件路径枚举
 *
 * @author 冷血毒舌
 * @create 2022/11/10 8:30
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FilePathEnum {

    /**
     * 通用图片路径
     */
    IMAGES("images/", "通用图片路径"),

    /**
     * 头像路径
     */
    AVATAR("avatar/", "头像路径"),
    /**
     * 文章图片路径
     */
    ARTICLE("articles/", "文章图片路径"),
    /**
     * 音频路径
     */
    VOICE("voice/", "音频路径"),
    /**
     * 照片路径
     */
    PHOTO("photos/", "相册路径"),
    /**
     * 配置图片路径
     */
    CONFIG("config/", "配置图片路径"),
    /**
     * 说说图片路径
     */
    TALK("talks/", "说说图片路径"),
    /**
     * md文件路径
     */
    MD("markdown/", "md文件路径");

    /**
     * 路径
     */
    private final String path;

    /**
     * 描述
     */
    private final String desc;

}

