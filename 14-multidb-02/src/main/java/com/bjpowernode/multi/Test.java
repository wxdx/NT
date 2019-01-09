package com.bjpowernode.multi;

import com.bjpowernode.multi.datasource.Datasource;
import com.bjpowernode.multi.datasource.ThreadLocalHolder;
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
        //使用3308数据源
        ThreadLocalHolder.setDataSourceKey(Datasource.DB3308.getKey());
        User addUser = new User();
        addUser.setName("fkj");
        userService.add(addUser);


        //使用3310数据源
        ThreadLocalHolder.setDataSourceKey(Datasource.DB3310.getKey());

        User user = userService.findOne(15);

        System.out.println("-----姓名:" + user.getName());




    }
}
