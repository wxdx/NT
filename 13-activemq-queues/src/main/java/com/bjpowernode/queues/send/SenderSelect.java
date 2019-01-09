package com.bjpowernode.queues.send;

import com.bjpowernode.queues.domain.Student;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * ClassName:SenderSelect
 * Package:com.bjpowernode.queues.send
 * Description:
 *
 * @Date:2018/10/16 15:44
 * @Author:hiwangxiaodong@hotmail.com
 */
public class SenderSelect {
    public static final String BROKER_URL = "tcp://192.168.108.130:61616";
    public static void main(String[] args) {

        send();
    }

    private static void send() {
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("SelectQueues1");
            producer = session.createProducer(destination);

            for (int i = 1; i <= 100; i++){
                Message message = session.createTextMessage("测试消息" + i);
                message.setIntProperty("age", i);
                producer.send(message);
                System.out.println("消息发送成功!" + i);
            }

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
