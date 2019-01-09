package com.bjpowernode.springboot.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:LoginInterceptor
 * Package:com.bjpowernode.springboot.interceptor
 * Description:
 *
 * @Date:2018/10/9 20:55
 * @Author:hiwangxiaodong@hotmail.com
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入到拦截器");
        boolean flag = false;
        if (null == request.getSession().getAttribute("user")) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("请先登录");
        } else {
            flag = true;
        }
        System.out.println("拦截器结束");
        return flag;

    }
}
