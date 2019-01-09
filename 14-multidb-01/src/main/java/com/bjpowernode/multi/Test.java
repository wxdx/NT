package com.bjpowernode.multi;

import com.bjpowernode.multi.model.User;
import com.bjpowernode.multi.service.UserService;
import com.bjpowernode.multi.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName:Test
 * Package:com.bjpowernode.multi
 * Description:
 *
 * @Date:2018/10/16 22:26
 * @Author:hiwangxiaodong@hotmail.com
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext ApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserService userService = ApplicationContext.getBean("userServiceImpl", UserServiceImpl.class);


        User user = userService.findOne(11);

        System.out.println("-----姓名:" + user.getName());


    }
}
