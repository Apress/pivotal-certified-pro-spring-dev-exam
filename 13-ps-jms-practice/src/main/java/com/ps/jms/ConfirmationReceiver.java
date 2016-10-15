package com.ps.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Created by iuliana.cosmina on 10/11/16.
 */
public class ConfirmationReceiver implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(ConfirmationReceiver.class);

    @Autowired
    MessageConverter messageConverter;

    @Override
    public void onMessage(Message message) {
        try {
            Confirmation receivedConf = (Confirmation) messageConverter.fromMessage(message);
            logger.info(" >>  Received confirmation: " + receivedConf);
        } catch (JMSException e) {
            logger.error("Something went wrong ...", e);
        }

    }
}