package com.bjpowernode.springboot.service;

import com.bjpowernode.springboot.model.User;
import com.bjpowernode.springboot.model.vo.Pagination;


import java.util.List;

/**
 * ClassName:UserService
 * Package:com.bjpowernode.springboot.service
 * Description:
 *
 * @Date:2018/10/11 15:50
 * @Author:hiwangxiaodong@hotmail.com
 */
public interface UserService {
    /**
     * 查询列表
     * @return
     */
    List<User> findAll();

    User findOne(Integer id);

    void modify(User user);

    void removeById(Integer id);

    Pagination<User> findAllByPage(Integer p, Integer pageSize);
}
