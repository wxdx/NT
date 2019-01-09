package com.bjpowernode.seckill.mapper;

import com.bjpowernode.seckill.model.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> selectAllSecKillGoods();

    Goods selectRandomNameByIdAndRandom(@Param("id") Integer id,@Param("random") String random);

    void updateStore(@Param("id") Integer id, @Param("store") Integer store);
}