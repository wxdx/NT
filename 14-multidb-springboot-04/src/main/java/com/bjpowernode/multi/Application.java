package com.bjpowernode.multi;

import com.bjpowernode.multi.datasource.DataSourceEnum;
import com.bjpowernode.multi.datasource.ThreadLocalHolder;
import com.bjpowernode.multi.model.User;
import com.bjpowernode.multi.service.UserService;
import com.bjpowernode.multi.service.impl.UserServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Application {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Application.class, args);

        UserService userService = context.getBean("userServiceImpl", UserServiceImpl.class);
        //使用3308数据源
        ThreadLocalHolder.setDataSourceKey(DataSourceEnum.DB3307.getKey());
        User addUser = new User();
        addUser.setName("www");
        userService.add(addUser);


        //使用3310数据源
        ThreadLocalHolder.setDataSourceKey(DataSourceEnum.DB3310.getKey());

        User user = userService.findOne(5);

        System.out.println("-----姓名:" + user.getName());
    }
}
