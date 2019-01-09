package com.bjpowernode.springboot.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpowernode.springboot.mapper.UserMapper;
import com.bjpowernode.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ClassName:UserServiceImpl
 * Package:com.bjpowernode.springboot.service
 * Description:
 *
 * @Date:2018/10/9 20:06
 * @Author:hiwangxiaodong@hotmail.com
 */
@Service(interfaceClass = UserService.class)//相当于暴露服务
@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void add(User user) {
        userMapper.insertSelective(user);
    }
}
