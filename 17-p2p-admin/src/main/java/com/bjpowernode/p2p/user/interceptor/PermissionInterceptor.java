package com.bjpowernode.p2p.user.interceptor;

import com.bjpowernode.p2p.user.model.UserInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Map;

/**
 * ClassName:PermissionInterceptor
 * Package:com.bjpowernode.p2p.user.interceptor
 * Description:
 *
 * @Date:2018/10/29 16:28
 * @Author:hiwangxiaodong@hotmail.com
 */
public class PermissionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        UserInfo userInfo = (UserInfo) request.getSession().getAttribute("session_user");

        if (userInfo == null){
            response.sendRedirect("/");
            return false;
        }

        String uri = request.getRequestURI();

        Enumeration<String> parameterNames = request.getParameterNames();

        String param = "";

        while (parameterNames.hasMoreElements()){
            String name = parameterNames.nextElement();
            if (StringUtils.equals(param, "")){
                param += "?" + name + "=" + request.getParameter(name);
            } else {
                param += "&" + name + "=" + request.getParameter(name);
            }
        }

        String url = uri + param;

        Map<String, String> urlMap = userInfo.getUrlMap();

        if (urlMap.get(url) != null){
            return true;
        }

        response.sendRedirect("/noPermission");

        return false;
    }
}
