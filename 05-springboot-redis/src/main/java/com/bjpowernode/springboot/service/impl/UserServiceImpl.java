package com.bjpowernode.springboot.service.impl;

import com.bjpowernode.springboot.mapper.UserMapper;
import com.bjpowernode.springboot.model.User;
import com.bjpowernode.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName:UserServiceImpl
 * Package:com.bjpowernode.springboot.service.impl
 * Description:
 *
 * @Date:2018/10/9 15:22
 * @Author:hiwangxiaodong@hotmail.com
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    @Override
    public List<User> findUser() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        List<User> list = (List<User>) redisTemplate.opsForValue().get("userAll");
        if (null == list) {
            synchronized (this){
                list = (List<User>) redisTemplate.opsForValue().get("userAll");
                if (null == list) {
                    list = userMapper.selectAll();
                    redisTemplate.opsForValue().set("userAll", list);
                    System.out.println("数据库中查询到=======================");
                } else {
                    System.out.println("redis中查询到--------------------");
                }
            }
        }
        return list;
    }
}
