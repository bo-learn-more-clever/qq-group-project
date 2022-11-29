package com.qq.group.application.dto.request;

import lombok.Data;

/**
 * @author HuoWB
 * @since 2022/11/11 16:28
 */
@Data
public class GroupMsgRequestDto {
    /**
     * 群号
     */
    private String group_id;
    /**
     * 用户qq
     */
    private String user_id;
    /**
     * 消息
     */
    private String message;
    /**
     * 消息类型
     */
    private String message_type;
}
