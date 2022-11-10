package com.moumouzhandui.mms.pojo.dto;

import com.moumouzhandui.mms.entity.ChatRecord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 聊天记录
 *
 * @author 冷血毒舌
 * @create 2022/11/10 8:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRecordDTO {

    /**
     * 聊天记录
     */
    private List<ChatRecord> chatRecordList;

    /**
     * ip地址
     */
    private String ipAddress;

    /**
     * ip来源
     */
    private String ipSource;

}
