package com.bjpowernode.springboot.config;

import com.bjpowernode.springboot.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName:LoginInterceptorConfig
 * Package:com.bjpowernode.springboot.config
 * Description:
 *
 * @Date:2018/10/9 21:07
 * @Author:hiwangxiaodong@hotmail.com
 */
@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptor = registry.addInterceptor(new LoginInterceptor());
        String[] interceptorArray = {"/private/**"};
        interceptor.addPathPatterns(interceptorArray);
        String[] excludeArray = {"/private/login", "/private/logout"};
        interceptor.excludePathPatterns(excludeArray);
    }
}
