package com.ps.jms;

import com.ps.ents.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

/**
 * Created by iuliana.cosmina on 10/10/16.
 */
@Component
public class UserSender {

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(final User user) {
        jmsTemplate.send ( (Session session) -> session.createObjectMessage(user));
    }

}
