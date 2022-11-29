package com.qq.group.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author HuoWB
 * @since 2022/11/17 16:10
 */
@Data
@TableName("t_auto_msg")
public class AutoMsgPo {

    private String id;
    /**
     *
     */
    private String auto;
    /**
     *
     */
    private String returnMsg;
}
