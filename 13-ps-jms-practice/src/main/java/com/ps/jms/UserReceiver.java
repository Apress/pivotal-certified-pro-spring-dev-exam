package com.ps.jms;

import com.ps.ents.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by iuliana.cosmina on 10/10/16.
 */
public class UserReceiver implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(UserReceiver.class);
    private static AtomicInteger id = new AtomicInteger();

    @Autowired
    MessageConverter messageConverter;

    @Autowired
    ConfirmationSender confirmationSender;

    @Override
    public void onMessage(Message message) {
        try {
            User receivedUser = (User) messageConverter.fromMessage(message);
            logger.info(" >> Received user: " + receivedUser);
            confirmationSender.sendMessage(new Confirmation(id.incrementAndGet(), "User " + receivedUser.getEmail() + " received."));
        } catch (JMSException e) {
            logger.error("Something went wrong ...", e);
        }

    }
}
