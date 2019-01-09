package com.bjpowernode.seckill.service;

import com.bjpowernode.seckill.commons.CommonsReturnObject;
import com.bjpowernode.seckill.model.Goods;
import com.bjpowernode.seckill.model.Order;

import java.util.List;

/**
 * ClassName:GoodsService
 * Package:com.bjpowernode.seckill.service
 * Description:
 *
 * @Date:2018/10/23 17:40
 * @Author:hiwangxiaodong@hotmail.com
 */
public interface GoodsService {
    /**
     * 查询所有秒杀商品
     * @return
     */
    List<Goods> queryAllSecKillGoods();

    /**
     * 根据商品id查询商品信息
     * @param id
     * @return
     */
    Goods querySecKillGoodsById(Integer id);

    /**
     * 执行秒杀处理
     * @param id
     * @param random
     * @return
     */
    CommonsReturnObject execSecKill(Integer id, String random, Integer uid);

    /**
     * 创建订单
     */
    Integer createOrder(Order order);

    /**
     * 下单异常处理
     */
    void processException(Order order);

    /**
     * 查询秒杀最终结果
     * @param id
     * @param uid
     * @return
     */
    CommonsReturnObject querySecKillResult(Integer id, Integer uid);

    /**
     * 同步更新库存
     * @param id
     * @param store
     */
    void modifyStore(Integer id, Integer store);
}
