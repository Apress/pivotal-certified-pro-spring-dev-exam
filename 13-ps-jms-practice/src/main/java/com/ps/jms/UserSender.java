package com.ps.jms;

import com.ps.ents.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

/**
 * Created by iuliana.cosmina on 10/10/16.
 */
public class UserSender {

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(final User user) {

        jmsTemplate.setPubSubNoLocal(true);

        jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage objectMessage = session.createObjectMessage(user);
                return objectMessage;
            }
        });
    }

}
