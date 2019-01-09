package com.bjpowernode.springboot.service.impl;

import com.bjpowernode.springboot.mapper.UserMapper;
import com.bjpowernode.springboot.model.User;
import com.bjpowernode.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName:UserServiceImpl
 * Package:com.bjpowernode.springboot.service.impl
 * Description:
 *
 * @Date:2018/10/8 20:26
 * @Author:hiwangxiaodong@hotmail.com
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void addUser(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public List<User> findUserList() {
        return userMapper.selectList();
    }

    @Override
    @Transactional
    public void addTransaction(User user) {
        userMapper.insertSelective(user);
        userMapper.insertSelective(user);
    }

    @Override
    public void removeById(Long id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User findById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
