package com.bjpowernode.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * ClassName:TestController
 * Package:com.bjpowernode.springboot.controller
 * Description:
 *
 * @Date:2018/10/10 14:50
 * @Author:hiwangxiaodong@hotmail.com
 */
@RestController
public class TestController {
    @RequestMapping("/boot/set")
    public String setSession(HttpSession session){
        session.setAttribute("session", "Session data");
        return "set data success";
    }
}
