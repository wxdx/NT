package com.bjpowernode.seckill.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * ClassName:SendService
 * Package:com.bjpowernode.seckill.activemq
 * Description:
 *
 * @Date:2018/10/25 15:25
 * @Author:hiwangxiaodong@hotmail.com
 */
@Component
public class SendService {
    @Autowired
    private JmsTemplate jmsTemplate;
    public void send(String str){
        jmsTemplate.send(session -> session.createTextMessage(str));
    }
}
