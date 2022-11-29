package com.qq.group.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author HuoWB
 * @since 2022/11/16 8:45
 */
@Data
@TableName("t_other_data")
public class OtherPo {
    private String id;
    /**
     *
     */
    private String name;
    /**
     *
     */
    private String url;
}
