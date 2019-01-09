package com.bjpowernode.springboot.service;

import com.bjpowernode.springboot.model.User;

import java.util.List;

/**
 * ClassName:UserService
 * Package:com.bjpowernode.springboot.service
 * Description:
 *
 * @Date:2018/10/8 20:25
 * @Author:hiwangxiaodong@hotmail.com
 */
public interface UserService {
    void addUser(User user);

    List<User> findUserList();

    void addTransaction(User user);

    void removeById(Long id);

    User findById(Long id);
}
