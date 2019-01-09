package com.bjpowernode.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName:TestController
 * Package:com.bjpowernode.springboot.controller
 * Description:
 *
 * @Date:2018/10/8 14:54
 * @Author:hiwangxiaodong@hotmail.com
 */
@Controller
public class TestController {
    @RequestMapping("/sayHi")
    public @ResponseBody String sayHi() {
        return "Hi，Spring boot";
    }
}
