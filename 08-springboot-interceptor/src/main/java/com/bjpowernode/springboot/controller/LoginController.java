package com.bjpowernode.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * ClassName:LoginController
 * Package:com.bjpowernode.springboot.controller
 * Description:
 *
 * @Date:2018/10/9 20:56
 * @Author:hiwangxiaodong@hotmail.com
 */
@RestController
public class LoginController {
    @RequestMapping("/private/login")
    public String login(HttpSession session){
        session.setAttribute("user", "user");
        return "登录成功";
    }
    @RequestMapping("/private/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "已退出登录";
    }
    @RequestMapping("/private/xx")
    public String xx(){
        return "进入private";
    }
}
