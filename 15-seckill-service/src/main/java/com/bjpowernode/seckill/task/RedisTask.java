package com.bjpowernode.seckill.task;

import com.bjpowernode.seckill.commons.CommonsConstants;
import com.bjpowernode.seckill.mapper.GoodsMapper;
import com.bjpowernode.seckill.model.Goods;
import com.bjpowernode.seckill.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * ClassName:RedisTask
 * Package:com.bjpowernode.seckill.task
 * Description:
 *
 * @Date:2018/10/24 17:19
 * @Author:hiwangxiaodong@hotmail.com
 */
@Configuration
@EnableScheduling
public class RedisTask {
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private  GoodsService goodsService;


    @Scheduled(cron = "0/5 * * * * *")
    public void initStore(){
        List<Goods> goodsList = goodsMapper.selectAllSecKillGoods();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        for (Goods goods : goodsList) {
            int store = goods.getStore();
            redisTemplate.opsForValue().setIfAbsent(CommonsConstants.SECKILL_STORE + goods.getId(), String.valueOf(store));
        }
    }

    @Scheduled(cron = "0/5 * * * * *")
    public void syncStore(){
        Set<String> keys = redisTemplate.keys(CommonsConstants.SECKILL_STORE + "*");
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            Integer id = Integer.parseInt(key.split(":")[1]);
            Integer store = Integer.parseInt(redisTemplate.opsForValue().get(key));
            goodsService.modifyStore(id, store);
        }
    }
}
