package com.bjpowernode.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * ClassName:Send
 * Package:com.bjpowernode.springboot
 * Description:
 *
 * @Date:2018/10/18 14:34
 * @Author:hiwangxiaodong@hotmail.com
 */
@Component
public class Send {
    @Autowired
    private JmsTemplate jmsTemplate;
    public void send(){

        for(int i = 1;;i++) {
            jmsTemplate.send(session -> session.createTextMessage("springboot测试信息"));
            System.out.println("发送成功" + i);
        }
    }
}
