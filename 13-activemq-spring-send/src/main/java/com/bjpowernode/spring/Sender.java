package com.bjpowernode.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;


/**
 * ClassName:Sender
 * Package:com.bjpowernode.spring
 * Description:
 *
 * @Date:2018/10/18 11:27
 * @Author:hiwangxiaodong@hotmail.com
 */

@Service
public class Sender {
    @Autowired
    private JmsTemplate jmsTemplate;
    public void send(){
        jmsTemplate.setDefaultDestinationName("SpringQueues");
        jmsTemplate.send(session-> session.createTextMessage("spring测试消息1111"));

    }
}
