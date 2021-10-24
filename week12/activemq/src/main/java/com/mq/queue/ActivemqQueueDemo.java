package com.mq.queue;

import lombok.extern.slf4j.Slf4j;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Slf4j
public class ActivemqQueueDemo implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            log.info(((TextMessage)message).getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
