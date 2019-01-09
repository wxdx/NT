package com.bjpowernode.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName:BeanConfig
 * Package:com.bjpowernode.springcloud.config
 * Description:
 *
 * @Date:2018/10/26 17:30
 * @Author:hiwangxiaodong@hotmail.com
 */
@Configuration
public class BeanConfig {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    @Bean
    public IRule iRule(){
        return new RandomRule();
    }

}
