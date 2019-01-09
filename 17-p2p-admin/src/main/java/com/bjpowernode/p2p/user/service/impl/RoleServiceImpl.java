package com.bjpowernode.p2p.user.service.impl;

import com.bjpowernode.p2p.user.mapper.PermissionMapper;
import com.bjpowernode.p2p.user.mapper.RoleMapper;
import com.bjpowernode.p2p.user.model.Permission;
import com.bjpowernode.p2p.user.model.ReturnObject;
import com.bjpowernode.p2p.user.model.Role;
import com.bjpowernode.p2p.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:RoleServiceImpl
 * Package:com.bjpowernode.p2p.user.service.impl
 * Description:
 *
 * @Date:2018/10/29 20:01
 * @Author:hiwangxiaodong@hotmail.com
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Role> queryAllRole() {
        return roleMapper.selectAllRole();
    }

    @Override
    public List<Map<Object, Object>> queryAllPermissionByRoleId(Integer roleId) {

        /*
        *   { id:1, pId:0, name:"父节点1 - 展开", open:true},
			{ id:11, pId:1, name:"父节点11 - 折叠"},
			{ id:111, pId:11, name:"叶子节点111"},
			{ id:112, pId:11, name:"叶子节点112"},
			{ id:113, pId:11, name:"叶子节点113"},
			{ id:114, pId:11, name:"叶子节点114"},
			{ id:12, pId:1, name:"父节点12 - 折叠"},
			{ id:121, pId:12, name:"叶子节点121"},
        *
        *
        * */
        List<Map<Object, Object>> permissionTreeData=new ArrayList<>();
        List<Permission> permissionList = permissionMapper.selectAll();
        List<Permission> rolePermissionList = permissionMapper.selectByRoleId(roleId);
        Map<Object, Object> zTreeMap = new HashMap<>();
        for (Permission permission : rolePermissionList) {
            zTreeMap.put(permission.getId(), "");
        }

        for (Permission permission : permissionList) {
            Map<Object,Object> zTreeNodeMap = new HashMap<>();
            zTreeNodeMap.put("id", permission.getId());
            zTreeNodeMap.put("pId", permission.getParentId() == null ? 0 : permission.getParentId());
            zTreeNodeMap.put("name", permission.getName());
            if (zTreeMap.get(permission.getId()) != null){
                zTreeNodeMap.put("checked", true);
            }
            if (permission.getId() == 1){
                zTreeNodeMap.put("open", true);
            }
            permissionTreeData.add(zTreeNodeMap);

        }

        return permissionTreeData;
    }

    @Override
    public ReturnObject disPermission(String[] id, String roleId) {
        ReturnObject returnObject = new ReturnObject();
        returnObject.setErrorCode("no");

        if (null != roleId) {
            roleMapper.deletePermissionByRoleId(Integer.parseInt(roleId));
        }

        if (null != id) {
            for (int i = 0; i < id.length ; i++) {
                int tmpId = Integer.parseInt(id[i]);
                roleMapper.insertByPermissionIdAndRoleId(Integer.parseInt(roleId),tmpId);
            }
            returnObject.setErrorCode("ok");
        }
        return returnObject;
    }


}
