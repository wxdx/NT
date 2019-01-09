package com.bjpowernode.multi.service.impl;

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
    private com.bjpowernode.multi.mapper.db3307.UserMapper userMapper3307;
    @Autowired
    private com.bjpowernode.multi.mapper.db3308.UserMapper userMapper3308;
    @Autowired
    private com.bjpowernode.multi.mapper.db3309.UserMapper userMapper3309;
    @Autowired
    private com.bjpowernode.multi.mapper.db3310.UserMapper userMapper3310;



    public User findOne(Integer id) {
        return userMapper3310.selectByPrimaryKey(id);
    }

    public void add(User user) {
        userMapper3308.insertSelective(user);
    }
}
