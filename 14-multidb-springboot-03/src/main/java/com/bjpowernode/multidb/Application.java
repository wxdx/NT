package com.bjpowernode.multidb;

import com.bjpowernode.multidb.model.User;
import com.bjpowernode.multidb.service.UserService;
import com.bjpowernode.multidb.service.impl.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Application {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Application.class, args);

        UserService userService = context.getBean("userServiceImpl", UserServiceImpl.class);


        User user = userService.findOne(6);

        System.out.println("-----姓名:" + user.getName());
    }
}
