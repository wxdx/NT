package com.bjpowernode.queues.receive;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.List;
import java.util.Vector;

/**
 * ClassName:ReceiverSelect
 * Package:com.bjpowernode.queues.receive
 * Description:
 *
 * @Date:2018/10/16 15:48
 * @Author:hiwangxiaodong@hotmail.com
 */
public class ReceiverSelect {
    public static final String BROKERURL = "failover:(tcp://192.168.108.130:61617,tcp://192.168.108.130:61618,tcp://192.168.108.130:61619)";
    public static void main(String[] args) {

        receive();
    }

    private static void receive() {
        Connection connection = null;
        Session session = null;
        MessageConsumer consumer = null;
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKERURL);

        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("TestQueues");
            consumer = session.createConsumer(destination);
            consumer.setMessageListener(message -> {
                if (message instanceof TextMessage){
                    try {
                        System.out.println(((TextMessage) message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
            System.out.println("监听器启动成功!");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
