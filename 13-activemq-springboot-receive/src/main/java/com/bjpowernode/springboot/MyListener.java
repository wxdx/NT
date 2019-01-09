package com.bjpowernode.springboot;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * ClassName:MyListener
 * Package:com.bjpowernode.springboot
 * Description:
 *
 * @Date:2018/10/18 14:47
 * @Author:hiwangxiaodong@hotmail.com
 */
@Component
public class MyListener {
    @JmsListener(destination = "${spring.jms.template.default-destination}")
    public void onMessage(Message message){
        if (message instanceof TextMessage){
            try {
                System.out.println(((TextMessage) message).getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
