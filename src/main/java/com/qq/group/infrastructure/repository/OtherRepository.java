package com.qq.group.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qq.group.infrastructure.mapper.OtherMapper;
import com.qq.group.infrastructure.po.OtherPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author HuoWB
 * @since 2022/11/16 8:46
 */
@Repository
public class OtherRepository {

    @Autowired
    OtherMapper otherMapper;


    public OtherPo getOtherPoByName(String name) {
        LambdaQueryWrapper<OtherPo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OtherPo::getName, name);
        wrapper.last(" limit 0,1");

        return otherMapper.selectOne(wrapper);
    }

}
