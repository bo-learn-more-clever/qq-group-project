package com.qq.group.application.dto.request;

import lombok.Data;
import org.apache.http.entity.mime.content.FileBody;

import java.awt.image.BufferedImage;

/**
 * @author HuoWB
 * @since 2022/11/11 16:51
 */
@Data
public class SendMsgRequestDto {
    /**
     * 群号
     */
    private String group_id;
    /**
     * 内容
     */
    private String message;
    /**
     * 是否为纯文本
     */
    private Boolean auto_escape;
}
