package com.bjpowernode.multi.service.impl;

import com.bjpowernode.multi.mapper.UserMapper;
import com.bjpowernode.multi.model.User;
import com.bjpowernode.multi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName:UserServiceImpl
 * Package:com.bjpowernode.multi.service.impl
 * Description:
 *
 * @Date:2018/10/16 22:21
 * @Author:hiwangxiaodong@hotmail.com
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User findOne(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public void add(User user) {
        userMapper.insertSelective(user);
    }
}
