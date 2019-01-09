package com.bjpowernode.queues.send;

import com.alibaba.fastjson.JSONObject;
import com.bjpowernode.queues.domain.Student;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * ClassName:Send
 * Package:com.bjpowernode.queues
 * Description:
 *
 * @Date:2018/10/15 14:59
 * @Author:hiwangxiaodong@hotmail.com
 */
public class Send {

    public static final String BROKER_URL = "failover:(tcp://192.168.108.130:61617,tcp://192.168.108.130:61618,tcp://192.168.108.130:61619)";
    public static void main(String[] args) {

        send();
        //sendObjectByJsonString();
       /* List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        list.forEach(i -> {
            System.out.println(i);
            System.out.println("hello" + i);
        });*/
    }

    private static void send() {
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("TestQueues");
            producer = session.createProducer(destination);
            Message message = session.createTextMessage("测试消息");
            for(int i = 1;;i++) {

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

    private static void sendObjectByJsonString() {
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("ObjectJSONQueues");
            Student student = new Student();
            student.setName("wang");
            student.setAge(23);
            student.setAddress("hefei");

            String jsonString = JSONObject.toJSONString(student);
            Message message = session.createTextMessage(jsonString);
            producer = session.createProducer(destination);
            producer.send(message);
            session.commit();
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
