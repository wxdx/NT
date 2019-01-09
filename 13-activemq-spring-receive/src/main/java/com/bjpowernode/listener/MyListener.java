package com.bjpowernode.listener;


import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * ClassName:MyListener
 * Package:com.bjpowernode.listener
 * Description:
 *
 * @Date:2018/10/18 13:28
 * @Author:hiwangxiaodong@hotmail.com
 */
public class MyListener implements MessageListener {


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
