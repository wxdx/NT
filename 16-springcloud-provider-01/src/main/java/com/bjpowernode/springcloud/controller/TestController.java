package com.bjpowernode.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:TestController
 * Package:com.bjpowernode.springcloud.controller
 * Description:
 *
 * @Date:2018/10/26 17:10
 * @Author:hiwangxiaodong@hotmail.com
 */
@RestController
public class TestController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello SpringCloud provider9200";
    }
}
