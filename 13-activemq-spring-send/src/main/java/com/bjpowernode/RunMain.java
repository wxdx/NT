package com.bjpowernode;

import com.bjpowernode.spring.Sender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName:RunMain
 * Package:com.bjpowernode
 * Description:
 *
 * @Date:2018/10/18 11:32
 * @Author:hiwangxiaodong@hotmail.com
 */
public class RunMain {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Sender sender  = (Sender) context.getBean("sender");
        sender.send();
    }
}
