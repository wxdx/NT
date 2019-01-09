package com.bjpowernode.p2p.user.service.impl;

import com.bjpowernode.p2p.user.mapper.PermissionMapper;
import com.bjpowernode.p2p.user.mapper.RoleMapper;
import com.bjpowernode.p2p.user.mapper.UserInfoMapper;
import com.bjpowernode.p2p.user.model.Permission;
import com.bjpowernode.p2p.user.model.Role;
import com.bjpowernode.p2p.user.model.UserInfo;
import com.bjpowernode.p2p.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * ClassName:UserServiceImpl
 * Package:com.bjpowernode.p2p.user.controller.impl
 * Description:
 *
 * @Date:2018/10/27 16:00
 * @Author:hiwangxiaodong@hotmail.com
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RoleMapper roleMapper;
    @Override
    public Integer login(UserInfo userInfo) {
        UserInfo userTemp = userInfoMapper.selectByUsername(userInfo.getUsername());
        if (null == userTemp) {
            return 1;
        }
        if (!StringUtils.equals(userInfo.getPassword(),userTemp.getPassword())){
            return 2;
        }

        BeanUtils.copyProperties(userTemp,userInfo);

        userInfo.setLastLoginTime(new Date());
        userInfo.setLoginCount(userInfo.getLoginCount() == null? 1 : userInfo.getLoginCount() + 1);
        userInfoMapper.updateByPrimaryKeySelective(userInfo);

        List<Permission> menuList =  permissionMapper.selectMenuListByUserId(userInfo.getId(),1);

        userInfo.setMenuList(menuList);

        List<String> urlList = permissionMapper.selectURLByUserId(userInfo.getId());

        Map<String, String> urlMap = new HashMap<>();

        if (urlList != null){
            for (String str : urlList) {
                urlMap.put(str,"");
            }
        }

        userInfo.setUrlMap(urlMap);


        return 0;
    }

    @Override
    public List<UserInfo> queryAllUserInfoAndStaffInfo() {
        return userInfoMapper.selectUserInfoAndStaffInfo();
    }

    @Override
    public List<Map> intiRoleDataByUserId(Integer userId) {
        List<Map> resultList = new ArrayList<>();
        List<Role> roleList = roleMapper.selectAllRole();
        List<Role> roleSelectedList = roleMapper.selectRoleByUserId(userId);
        Map selectedMap = new HashMap();
        for (Role role : roleSelectedList) {
            selectedMap.put(role.getId(),"");
        }
        for (Role role : roleList) {
            Map map = new HashMap();
            map.put("roleId",role.getId());
            map.put("roleName",role.getName());
            if (selectedMap !=null) {
                if (selectedMap.get(role.getId()) != null) {
                    map.put("selected", "");
                }
            }
            resultList.add(map);
        }
        return resultList;
    }

    @Override
    public void disRole(Integer userId, Integer[] roleIds) {
        userInfoMapper.deleteUserRoleByUserId(userId);
        if(roleIds!=null){
            for(Integer roleId:roleIds){
                userInfoMapper.insertUserRole(userId,roleId);
            }

        }
    }
}
