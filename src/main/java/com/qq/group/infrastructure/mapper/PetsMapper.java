package com.qq.group.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qq.group.infrastructure.po.PetsPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author HuoWB
 * @since 2022/11/14 16:21
 */
@Mapper
public interface PetsMapper extends BaseMapper<PetsPo> {
}
