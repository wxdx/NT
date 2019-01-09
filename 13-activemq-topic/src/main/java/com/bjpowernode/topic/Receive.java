package com.bjpowernode.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * ClassName:Receive
 * Package:com.bjpowernode.topic
 * Description:
 *
 * @Date:2018/10/15 15:38
 * @Author:hiwangxiaodong@hotmail.com
 */
public class Receive {
    public static void main(String[] args) {
        System.out.println(Datasource.DB3307);
       // receive();
    }

    private static void receive() {

        Connection connection = null;
        Session session = null;
        MessageConsumer consumer = null;
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.108.130:61616");
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createTopic("testTopic");
            consumer = session.createConsumer(destination);
            Message message = consumer.receive();
            System.out.println(((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (null != consumer) {
                try {
                    consumer.close();
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
