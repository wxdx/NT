package com.bjpowernode.seckill.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.bjpowernode.seckill.activemq.SendService;
import com.bjpowernode.seckill.commons.CommonsConstants;
import com.bjpowernode.seckill.commons.CommonsReturnObject;
import com.bjpowernode.seckill.mapper.GoodsMapper;
import com.bjpowernode.seckill.mapper.OrderMapper;
import com.bjpowernode.seckill.model.Goods;
import com.bjpowernode.seckill.model.Order;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * ClassName:GoodsServiceImpl
 * Package:com.bjpowernode.seckill.service
 * Description:
 *
 * @Date:2018/10/23 17:45
 * @Author:hiwangxiaodong@hotmail.com
 */
@Service(interfaceClass = GoodsService.class)
@Component
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private SendService sendService;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Goods> queryAllSecKillGoods() {
        return goodsMapper.selectAllSecKillGoods();
    }

    @Override
    public Goods querySecKillGoodsById(Integer id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    /**
     * 执行秒杀处理
     * @param id
     * @param random
     * @return
     */
    @Override
    public CommonsReturnObject execSecKill(Integer id, String random, Integer uid) {
        CommonsReturnObject commonsReturnObject = new CommonsReturnObject();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //1.验证用户请求的商品秒杀唯一标识是否正确
        Goods goods = goodsMapper.selectRandomNameByIdAndRandom(id, random);
        if (null == goods){
            commonsReturnObject.setErrorCode(CommonsConstants.ONE);
            commonsReturnObject.setErrorMessage("商品信息有误,请重试~~~~");
            return commonsReturnObject;
        } else {
            //2.验证秒杀是否开始(再次验证)
            long startTime = goods.getStartTime().getTime();
            long endTime = goods.getEndTime().getTime();
            long nowTime = System.currentTimeMillis();

            if (startTime > nowTime){
                //秒杀未开始
                commonsReturnObject.setErrorCode(CommonsConstants.ONE);
                commonsReturnObject.setErrorMessage("秒杀未开始!");
                return commonsReturnObject;
            } else if (nowTime > endTime){
                //秒杀已结束
                commonsReturnObject.setErrorCode(CommonsConstants.ONE);
                commonsReturnObject.setErrorMessage("秒杀已结束!");
                return commonsReturnObject;
            } else {
                //3.验证用户是否已经秒杀过该商品,规定一个用户只能购买同一商品一次
                String hold = redisTemplate.opsForValue().get(CommonsConstants.HANDLE_USER + id + ':' + uid);

                if (StringUtils.isNotEmpty(hold)){
                    commonsReturnObject.setErrorCode(CommonsConstants.ONE);
                    commonsReturnObject.setErrorMessage("同一商品只能秒杀一次!");
                    return commonsReturnObject;
                }
                //4.验证商品库存是否抢光了
                Integer store = StringUtils.isEmpty(redisTemplate.opsForValue().get(CommonsConstants.SECKILL_STORE + id))? 0 : Integer.parseInt(redisTemplate.opsForValue().get(CommonsConstants.SECKILL_STORE + id));

                if (store <= 0){
                    commonsReturnObject.setErrorCode(CommonsConstants.ONE);
                    commonsReturnObject.setErrorMessage("来晚啦!!商品已被抢购~~~~");
                    return commonsReturnObject;
                }
                //5.限流(限制访问流量)
                long maxLimit = 100 * CommonsConstants.LIMIT_GOODS_MULTIPLE;

                long currentLimit = redisTemplate.opsForList().size(CommonsConstants.LIMITING_LIST + id);

                if (currentLimit > maxLimit){
                    commonsReturnObject.setErrorCode(CommonsConstants.ONE);
                    commonsReturnObject.setErrorMessage("服务器出小差了~~~,请重试~~~~~");
                    return commonsReturnObject;
                } else {
                    System.out.println("进入限流~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    //向list中加流量
                    redisTemplate.opsForList().leftPush(CommonsConstants.LIMITING_LIST + id, String.valueOf(uid));
                }
                //6.可以正式秒杀   1)减库存 2)下订单(数据库中操作,流量瞬间很大,不要把请求直接落入到数据库,数据库并发性能很弱,有性能瓶颈.放在activemq更好)
                //1)减库存
                Long leftStore = redisTemplate.opsForValue().increment(CommonsConstants.SECKILL_STORE + id, -1);
                if (leftStore >= 0){
                    //此时用户没有标识,给用户一个已购买的标识
                    redisTemplate.opsForValue().set(CommonsConstants.HANDLE_USER + id + ':' + uid, String.valueOf(uid));
                    //2)下订单
                    Order order = new Order();
                    order.setBuyNum(1);
                    order.setBuyPrice(goods.getPrice());
                    order.setCreateTime(new Date());
                    order.setGoodsId(id);
                    order.setOrderMoney(goods.getPrice().multiply(new BigDecimal(1)));
                    order.setStatus(1);
                    order.setUid(uid);
                    String orderString = JSONObject.toJSONString(order);
                    System.out.println(orderString);
                    //将消息放入队列
                    sendService.send(orderString);

                    commonsReturnObject.setErrorCode(CommonsConstants.ZERO);
                    commonsReturnObject.setErrorMessage("秒杀请求成功!系统正在处理~~~~~~~~~");
                    return commonsReturnObject;
                } else {
                    //此时为负数,不能下订单
                    redisTemplate.opsForValue().increment(CommonsConstants.SECKILL_STORE + id, 1);
                    commonsReturnObject.setErrorCode(CommonsConstants.ONE);
                    commonsReturnObject.setErrorMessage("来晚啦!!商品已被抢购~~~~");
                    return commonsReturnObject;
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer createOrder(Order order) {
        int insertCount = orderMapper.insertSelective(order);
        if (insertCount > 0){
            //下订单成功
            //从redis List弹出一个流量
            redisTemplate.opsForList().rightPop(CommonsConstants.LIMITING_LIST + order.getGoodsId());

            //整个秒杀流程执行完毕!前台轮询后台秒杀结果
            CommonsReturnObject commonsReturnObject = new CommonsReturnObject();
            commonsReturnObject.setErrorCode(CommonsConstants.ZERO);
            commonsReturnObject.setErrorMessage("秒杀成功");
            commonsReturnObject.setData(order);
            String resultJson = JSONObject.toJSONString(commonsReturnObject);

            redisTemplate.opsForValue().set(CommonsConstants.ORDER_RESULT + order.getGoodsId() + ":" + order.getUid(), resultJson);

        } else {
            throw new RuntimeException("秒杀失败");
        }

        return 0;
    }
    @Override
    public void processException(Order order){
        //把库存加回来
        redisTemplate.opsForValue().increment(CommonsConstants.SECKILL_STORE + order.getGoodsId(), 1);

        //把已经抢购的标志删除
        redisTemplate.delete(CommonsConstants.HANDLE_USER + order.getGoodsId() + ':' + order.getUid());

        //限流删除
        redisTemplate.opsForList().rightPop(CommonsConstants.LIMITING_LIST + order.getGoodsId());

        //秒杀结果放入到redis中
        CommonsReturnObject commonsReturnObject = new CommonsReturnObject();
        commonsReturnObject.setErrorCode(CommonsConstants.ONE);
        commonsReturnObject.setErrorMessage("秒杀失败");
        String resultJson = JSONObject.toJSONString(commonsReturnObject);

        redisTemplate.opsForValue().set(CommonsConstants.ORDER_RESULT + order.getGoodsId() + ":" + order.getUid(), resultJson);

    }

    @Override
    public CommonsReturnObject querySecKillResult(Integer id, Integer uid) {

        String commonsReturnObjectJSON = redisTemplate.opsForValue().get(CommonsConstants.ORDER_RESULT + id + ":" + uid);

        CommonsReturnObject commonsReturnObject = StringUtils.isEmpty(commonsReturnObjectJSON) ? new CommonsReturnObject() : JSONObject.parseObject(commonsReturnObjectJSON, CommonsReturnObject.class);

        return commonsReturnObject;
    }

    @Override
    public void modifyStore(Integer id, Integer store) {
        goodsMapper.updateStore(id,store);
    }
}
