package com.bjpowernode.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName:TestController
 * Package:com.bjpowernode.springcloud.controller
 * Description:
 *
 * @Date:2018/10/26 17:32
 * @Author:hiwangxiaodong@hotmail.com
 */
@RestController
public class TestController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hello")
    public String hello(){
        String body = restTemplate.getForEntity("http://16-SPRINGCLOUD-PROVIDER/hello", String.class).getBody();
        return body;
    }
}
