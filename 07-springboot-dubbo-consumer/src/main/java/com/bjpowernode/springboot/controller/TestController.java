package com.bjpowernode.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjpowernode.springboot.model.User;
import com.bjpowernode.springboot.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:TestController
 * Package:com.bjpowernode.springboot.controller
 * Description:
 *
 * @Date:2018/10/9 20:23
 * @Author:hiwangxiaodong@hotmail.com
 */
@RestController
public class TestController {
    @Reference
    private UserService userService;

    @RequestMapping("/dubbo/add")
    public Object add(){

        User user = new User();
        user.setName("wxd");
        user.setAge(21);

        userService.add(user);
        return "添加成功";
    }
}
