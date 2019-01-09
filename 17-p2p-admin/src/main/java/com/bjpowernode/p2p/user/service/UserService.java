package com.bjpowernode.p2p.user.service;

import com.bjpowernode.p2p.user.model.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * ClassName:UserService
 * Package:com.bjpowernode.p2p.user.service
 * Description:
 *
 * @Date:2018/10/27 16:00
 * @Author:hiwangxiaodong@hotmail.com
 */
public interface UserService {
    Integer login(UserInfo userInfo);

    List<UserInfo> queryAllUserInfoAndStaffInfo();

    List<Map> intiRoleDataByUserId(Integer userId);

    void disRole(Integer userId, Integer[] roleIds);
}
