package com.bjpowernode.p2p.user.controller;

import com.bjpowernode.p2p.user.mapper.UserInfoMapper;
import com.bjpowernode.p2p.user.model.UserInfo;
import com.bjpowernode.p2p.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * ClassName:UserController
 * Package:com.bjpowernode.p2p.user.controller
 * Description:
 *
 * @Date:2018/10/27 15:12
 * @Author:hiwangxiaodong@hotmail.com
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String toLogin(HttpServletRequest request, Model model, HttpSession session){
        Cookie[] cookies = request.getCookies();
        UserInfo userInfo = new UserInfo();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                if (StringUtils.equals("username",cookieName)){
                    userInfo.setUsername(cookie.getValue());
                    continue;
                }
                if (StringUtils.equals("password",cookieName)){
                    userInfo.setPassword(cookie.getValue());
                    continue;
                }
            }
        }

        if (StringUtils.isEmpty(userInfo.getUsername()) || StringUtils.isEmpty(userInfo.getPassword())){
            return "login";
        }

        Integer result = userService.login(userInfo);

        if (result == 1){
            model.addAttribute("errorMessage", "账号不存在!");
            return "login";
        }
        if (result == 2){
            model.addAttribute("username",userInfo.getUsername());
            model.addAttribute("errorMessage", "密码不正确!");
            return "login";
        }

        session.setAttribute("session_user", userInfo);

        return "index";
    }

    @PostMapping("/login")
    public String login(UserInfo userInfo, HttpSession session, Model model, HttpServletResponse response,
                        @RequestParam(value = "isAutoLogin",required = false) String isAutoLogin){
        Integer result = userService.login(userInfo);
        if (result == 1){
            model.addAttribute("errorMessage", "账号不存在!");
            return "login";
        }
        if (result == 2){
            model.addAttribute("username",userInfo.getUsername());
            model.addAttribute("errorMessage", "密码不正确!");
            return "login";
        }

        session.setAttribute("session_user", userInfo);

        if (null != isAutoLogin) {

            Cookie usernameCookie = new Cookie("username",userInfo.getUsername());
            usernameCookie.setPath("/");
            usernameCookie.setMaxAge(60 * 60 * 24 * 7);

            Cookie passwordCookie = new Cookie("password",userInfo.getPassword());
            passwordCookie.setPath("/");
            passwordCookie.setMaxAge(60 * 60 * 24 * 7);

            response.addCookie(usernameCookie);
            response.addCookie(passwordCookie);

        }

        return "index";
    }

    @RequestMapping("/noPermission")
    public String noPermission(){
        return "noPermission";
    }


    @RequestMapping("/admin/users")
    public String users(Model model){
        List<UserInfo> userInfoList = userService.queryAllUserInfoAndStaffInfo();
        model.addAttribute("userInfoList",userInfoList);
        return "users";
    }

    @RequestMapping("/toDisRole/{userId}")
    public String toDisRole(@PathVariable Integer userId , Model model){
        List<Map> roleData= userService.intiRoleDataByUserId(userId);
        model.addAttribute("roleDataList",roleData);
        model.addAttribute("userId",userId);
        return "distributionRole";
    }

    @RequestMapping("/admin/disRoles")
    @ResponseBody
    public String disRole(Integer userId,Integer[] roleIds){

        userService.disRole(userId,roleIds);

        return "ok";
    }

}
