package com.qq.group.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qq.group.infrastructure.mapper.AutoMsgMapper;
import com.qq.group.infrastructure.po.AutoMsgPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author HuoWB
 * @since 2022/11/17 16:18
 */
@Repository
public class AutoRepository {

    @Autowired
    AutoMsgMapper autoMsgMapper;


    public AutoMsgPo getByAuto(String auto) {
        LambdaQueryWrapper<AutoMsgPo> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(AutoMsgPo::getAuto, auto);

        return autoMsgMapper.selectOne(wrapper);
    }

    public void create(AutoMsgPo autoMsgPo){
        autoMsgMapper.insert(autoMsgPo);
    }


}
