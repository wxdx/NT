package com.bjpowernode.springboot.controller;

import com.bjpowernode.springboot.mapper.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName:TestController
 * Package:com.bjpowernode.springboot.controller
 * Description:
 *
 * @Date:2018/10/8 16:11
 * @Author:hiwangxiaodong@hotmail.com
 */
@Controller
public class TestController {

    @Value("${bjpowernode.name}")
    private String name;

    @Value("${bjpowernode.age}")
    private Integer age;

    @Autowired
    private User user;

    @RequestMapping("/config")
    public @ResponseBody  String config(){
        return user.toString();
    }

}
