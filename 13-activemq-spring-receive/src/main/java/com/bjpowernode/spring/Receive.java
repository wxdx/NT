package com.bjpowernode.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * ClassName:Receive
 * Package:com.bjpowernode.spring
 * Description:
 *
 * @Date:2018/10/18 12:43
 * @Author:hiwangxiaodong@hotmail.com
 */
@Service
public class Receive {
    @Autowired
    private JmsTemplate jmsTemplate;
    public void receive(){
        jmsTemplate.setDefaultDestinationName("SpringQueues");
        Message message = jmsTemplate.receive();
        if (message instanceof TextMessage){
            try {
                System.out.println(((TextMessage) message).getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
