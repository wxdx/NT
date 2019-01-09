package com.bjpowernode.springboot;

import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * ClassName:MyListener
 * Package:com.bjpowernode.springboot
 * Description:
 *
 * @Date:2018/10/18 15:11
 * @Author:hiwangxiaodong@hotmail.com
 */
@Component
public class MyListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage){
            try {
                System.out.println(((TextMessage) message).getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }
}
