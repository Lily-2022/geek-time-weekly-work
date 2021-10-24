package com.mq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class MQConsumer {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory;
        Connection connection;
        Session session;
        Destination destination;
        try {
            connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://127.0.0.1:61616");
            connection = connectionFactory.createConnection();
            connection.start();

            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("myQueue");
            MessageConsumer consumer = session.createConsumer(destination);
            consumer.setMessageListener(new ActivemqQueueDemo());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
