package com.mq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MQProducer {

    public static void main(String[] args) {
        ConnectionFactory factory;
        Connection connection = null;

        Session session;

        Destination destination;

        MessageProducer messageProducer;

        try {
            factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://127.0.0.1:61616");
            connection = factory.createConnection();
            connection.start();

            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("myQueue");

            messageProducer = session.createProducer(destination);

            TextMessage textMessage = session.createTextMessage("hello activemq 111");
            messageProducer.send(textMessage);
            session.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
