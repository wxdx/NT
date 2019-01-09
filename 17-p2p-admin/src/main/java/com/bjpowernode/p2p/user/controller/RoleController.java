package com.bjpowernode.p2p.user.controller;

import com.bjpowernode.p2p.user.model.ReturnObject;
import com.bjpowernode.p2p.user.model.Role;
import com.bjpowernode.p2p.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * ClassName:RoleController
 * Package:com.bjpowernode.p2p.user.controller
 * Description:
 *
 * @Date:2018/10/29 19:58
 * @Author:hiwangxiaodong@hotmail.com
 */
@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/admin/role")
    public String roleInfo(Model model){
        List<Role> rolesList =  roleService.queryAllRole();
        model.addAttribute("rolesList", rolesList);
        return "roles";
    }

    @RequestMapping("/toDisPermission/{roleId}")
    public String toDisPermission(@PathVariable("roleId") Integer roleId, Model model){
        model.addAttribute("roleId", roleId);
        return "disPermission";
    }

    @RequestMapping("/initPermissionTree/{roleId}")
    @ResponseBody
    public Object initZTree(@PathVariable("roleId") Integer roleId){

        List<Map<Object, Object>> zTreeList = roleService.queryAllPermissionByRoleId(roleId);

        return zTreeList;
    }

    @RequestMapping("/disPermission")
    @ResponseBody
    public Object disPermission(String[] id,String roleId){

        ReturnObject returnObject = roleService.disPermission(id, roleId);
        return returnObject;
    }
}
