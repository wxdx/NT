package com.bjpowernode.p2p.user.service;

import com.bjpowernode.p2p.user.model.ReturnObject;
import com.bjpowernode.p2p.user.model.Role;

import java.util.List;
import java.util.Map;

/**
 * ClassName:RoleService
 * Package:com.bjpowernode.p2p.user.service
 * Description:
 *
 * @Date:2018/10/29 20:00
 * @Author:hiwangxiaodong@hotmail.com
 */
public interface RoleService {
    List<Role> queryAllRole();

    List<Map<Object, Object>> queryAllPermissionByRoleId(Integer roleId);

    ReturnObject disPermission(String[] id, String roleId);
}
