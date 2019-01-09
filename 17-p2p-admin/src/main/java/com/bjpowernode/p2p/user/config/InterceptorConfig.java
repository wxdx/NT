package com.bjpowernode.p2p.user.config;

import com.bjpowernode.p2p.user.interceptor.PermissionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName:InterceptorConfig
 * Package:com.bjpowernode.p2p.user.config
 * Description:
 *
 * @Date:2018/10/29 16:30
 * @Author:hiwangxiaodong@hotmail.com
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*InterceptorRegistration interceptor = registry.addInterceptor(new PermissionInterceptor());
        String[] interceptorArray = {"/admin/**"};
        interceptor.addPathPatterns(interceptorArray);*/
    }
}
