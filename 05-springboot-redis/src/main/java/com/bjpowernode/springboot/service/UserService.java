package com.bjpowernode.springboot.service;

import com.bjpowernode.springboot.model.User;

import java.util.List;

/**
 * ClassName:UserService
 * Package:com.bjpowernode.springboot.service
 * Description:
 *
 * @Date:2018/10/9 15:21
 * @Author:hiwangxiaodong@hotmail.com
 */
public interface UserService {
    List<User> findUser();
}
