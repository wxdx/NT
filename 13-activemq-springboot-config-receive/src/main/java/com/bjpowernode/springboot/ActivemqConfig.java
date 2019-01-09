package com.bjpowernode.springboot;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

/**
 * ClassName:ActivemqConfig
 * Package:com.bjpowernode.springboot
 * Description:
 *
 * @Date:2018/10/18 15:13
 * @Author:hiwangxiaodong@hotmail.com
 */
@Configuration
public class ActivemqConfig {
    @Autowired
    private ActiveMQConnectionFactory activeMQConnectionFactory;
    @Autowired
    private MyListener listener;
    @Value("${spring.jms.pub-sub-domain}")
    private Boolean pubSubDomain;
    @Value("${spring.jms.template.default-destination}")
    private String destination;

    /**
     *
     * <bean class="org.springframework.jms.listener.DefaultMessageListenerContainer">
     *         <property name="connectionFactory" ref="activeMQConnectionFactory"/>
     *         <property name="destinationName" value="SpringQueues"/>
     *         <property name="messageListener" ref="myListener"/>
     *         <property name="pubSubDomain" value="true"/>
     *     </bean>
     */
    @Bean
    public DefaultMessageListenerContainer defaultMessageListenerContainer(){
        DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setConnectionFactory(activeMQConnectionFactory);
        defaultMessageListenerContainer.setDestinationName(destination);
        defaultMessageListenerContainer.setMessageListener(listener);
        defaultMessageListenerContainer.setPubSubDomain(pubSubDomain);

        return defaultMessageListenerContainer;
    }


}
