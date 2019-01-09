package com.bjpowernode.springboot.controller;

import com.bjpowernode.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ClassName:TestController
 * Package:com.bjpowernode.springboot.controller
 * Description:
 *
 * @Date:2018/10/9 15:17
 * @Author:hiwangxiaodong@hotmail.com
 */
@RestController
public class TestController {
    @Autowired
    private UserService userService;
    @RequestMapping("/select")
    public Object redisTest(){
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                userService.findUser();
            }
        };
        for (int i = 0; i < 50; i++) {
            executorService.submit(runnable);
        }
        return "xxxx";
    }
}
