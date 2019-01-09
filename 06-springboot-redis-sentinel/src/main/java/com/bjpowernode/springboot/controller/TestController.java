package com.bjpowernode.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName:TestController
 * Package:com.bjpowernode.springboot.controller
 * Description:
 *
 * @Date:2018/10/9 16:34
 * @Author:hiwangxiaodong@hotmail.com
 */
@Controller
public class TestController {
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @RequestMapping("/boot/setData")
    public @ResponseBody Object sentinelTest(){
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        for (int i = 0; i >= 0; i++) {
            redisTemplate.opsForValue().set("key" + i, "value" + i);
            System.out.println("key" + i + "      value" + i);
        }

        return "设置成功";
    }
}
