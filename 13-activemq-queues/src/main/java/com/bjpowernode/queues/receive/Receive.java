package com.bjpowernode.queues.receive;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bjpowernode.queues.domain.Student;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.List;
import java.util.Vector;

/**
 * ClassName:Receive
 * Package:com.bjpowernode.queues
 * Description:
 *
 * @Date:2018/10/15 15:18
 * @Author:hiwangxiaodong@hotmail.com
 */
public class Receive {
    public static final String BROKERURL = "failover:(tcp://192.168.108.130:61617,tcp://192.168.108.130:61618,tcp://192.168.108.130:61619)";
    public static void main(String[] args) {

        //receive();
        receiveJSONString();
    }

    private static void receive() {
        Connection connection = null;
        Session session = null;
        MessageConsumer consumer = null;
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKERURL);
        List<String> list = new Vector<>();
        list.add("com.bjpowernode.queues.domain");
        list.add("java.lang");
        ((ActiveMQConnectionFactory) connectionFactory).setTrustedPackages(list);
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("TestQueues");
            consumer = session.createConsumer(destination);
            Message message = consumer.receive();
            if (message instanceof ObjectMessage){
                Student student = (Student)((ObjectMessage) message).getObject();
                System.out.println(student.getName() + "" + student.getAge() + "" + student.getAddress());
            }
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

    private static void receiveJSONString() {
        Connection connection = null;
        Session session = null;
        MessageConsumer consumer = null;
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKERURL);
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("ObjectJSONQueues");
            consumer = session.createConsumer(destination);
            Message message = consumer.receive();

            if (message instanceof TextMessage){
                String ObjectString = ((TextMessage) message).getText();
                System.out.println(ObjectString);
                JSONObject jsonObject = JSON.parseObject(ObjectString);
                String name = jsonObject.getString("name");
                String address = jsonObject.getString("address");
                Integer age = jsonObject.getInteger("age");

                System.out.println(name);
                System.out.println(address);
                System.out.println(age);
            }
            session.commit();
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
