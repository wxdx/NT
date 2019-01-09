package com.bjpowernode.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * ClassName:Send
 * Package:com.bjpowernode.topic
 * Description:
 *
 * @Date:2018/10/15 15:35
 * @Author:hiwangxiaodong@hotmail.com
 */
public class Send {
    public static void main(String[] args) {
        send();
    }

    private static void send() {
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.108.130:61616");
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic("testTopic");
            Message message = session.createTextMessage("测试信息topic");
            producer = session.createProducer(destination);
            producer.send(message);
            System.out.println("消息发送成功!");
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (null != producer) {
                try {
                    producer.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (null != session) {
                try {
                    session.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (null != connection) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
