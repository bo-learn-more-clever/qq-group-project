package com.qq.group.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qq.group.infrastructure.mapper.PetsMapper;
import com.qq.group.infrastructure.po.PetsPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author HuoWB
 * @since 2022/11/14 16:24
 */
@Repository
public class PetsRepository {

    @Autowired
    PetsMapper petsMapper;


    /**
     * 根据首字母缩写获取
     *
     * @param acronyms 首字母
     * @return com.qq.group.infrastructure.po.PetsPo
     * @author HuoWB
     * @since 2022/11/14 16:25
     */
    public PetsPo getPetsByAcronyms(String acronyms) {
        LambdaQueryWrapper<PetsPo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PetsPo::getAcronyms, acronyms);
        wrapper.last(" limit 0,1");

        return petsMapper.selectOne(wrapper);
    }

    public PetsPo getPetsPoByOrName(String orName) {
        LambdaQueryWrapper<PetsPo> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(PetsPo::getOrName, orName);
        wrapper.last(" limit 0,1");

        return petsMapper.selectOne(wrapper);
    }

    public PetsPo getPetsPoByName(String name) {
        LambdaQueryWrapper<PetsPo> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(PetsPo::getName, name);
        wrapper.last(" limit 0,1");

        return petsMapper.selectOne(wrapper);
    }

    public void create(PetsPo petsPo) {
        petsMapper.insert(petsPo);
    }

    public PetsPo getById(String id) {
        return petsMapper.selectById(id);
    }

}
