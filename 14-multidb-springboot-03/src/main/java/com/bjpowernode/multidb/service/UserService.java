package com.bjpowernode.multidb.service;

import com.bjpowernode.multidb.model.User;

/**
 * ClassName:UserService
 * Package:com.bjpowernode.multi.service
 * Description:
 *
 * @Date:2018/10/16 22:20
 * @Author:hiwangxiaodong@hotmail.com
 */
public interface UserService {
    User findOne(Integer id);
    void add(User user);
}
