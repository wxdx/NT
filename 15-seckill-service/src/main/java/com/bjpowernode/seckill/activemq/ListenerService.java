package com.bjpowernode.seckill.activemq;

import com.alibaba.fastjson.JSONObject;
import com.bjpowernode.seckill.model.Order;
import com.bjpowernode.seckill.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * ClassName:ListenerService
 * Package:com.bjpowernode.seckill.activemq
 * Description:
 *
 * @Date:2018/10/25 15:53
 * @Author:hiwangxiaodong@hotmail.com
 */
@Component
public class ListenerService {
    //concurrency属性 可以提高消息处理速度

    @Autowired
    private GoodsService goodsService;

    @JmsListener(destination = "${spring.jms.template.default-destination}",concurrency = "16")
    public void onMessage(Message message){
        if (message instanceof TextMessage){
            String orderJson = null;
            Order order = null;
            try {
                orderJson = ((TextMessage) message).getText();
                order = JSONObject.parseObject(orderJson, Order.class);
                goodsService.createOrder(order);
            } catch (JMSException e) {
                e.printStackTrace();
                System.out.println("发消息异常~~~~~~~~~~~~~~");

            } catch (Exception e) {
                e.printStackTrace();
                //处理订单异常
                goodsService.processException(order);
                System.out.println("数据库下订单异常~~~~~~~~~~~~~~");
            }

            System.out.println("接收到的消息:" + orderJson);
        }
    }
}
