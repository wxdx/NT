package com.bjpowernode.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName:TestController
 * Package:com.bjpowernode.springboot.controller
 * Description:
 *
 * @Date:2018/10/8 17:43
 * @Author:hiwangxiaodong@hotmail.com
 */
@Controller
public class TestController {

    @RequestMapping("/boot/index")
    public String index(){
        return "index";
    }
}
