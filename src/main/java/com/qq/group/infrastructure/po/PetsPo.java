package com.qq.group.infrastructure.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author HuoWB
 * @since 2022/11/14 16:21
 */
@Data
@TableName("t_pets")
public class PetsPo {

    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 别名
     */
    private String orName;
    /**
     * 缩写
     */
    private String acronyms;
    /**
     * 资料卡url
     */
    private String zlkUrl;
    /**
     * 解析卡url
     */
    private String jxkUrl;
}
