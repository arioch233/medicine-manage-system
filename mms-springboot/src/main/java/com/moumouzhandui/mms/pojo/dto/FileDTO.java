package com.moumouzhandui.mms.pojo.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 冷血毒舌
 * @create 2022/10/22 0:13
 */
@Data
public class FileDTO {
    /**
     * id
     */
    private Integer id;

    /**
     * 文件名称
     */
    private String filename;
    /**
     * 文件类型
     */
    private String type;

    /**
     * 文件大小(KB)
     */
    private Long size;

    /**
     * 文件链接
     */
    private String url;

    /**
     * 是否禁用链接(1:是  0:否)
     */
    private Integer enable;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
