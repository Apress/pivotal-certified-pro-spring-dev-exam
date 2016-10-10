package com.ps.jms.client;

import com.ps.ents.User;
import org.springframework.jms.core.JmsTemplate;

import java.util.List;

/**
 * Created by iuliana.cosmina on 10/9/16.
 */
public class JmsUserBatchProducer implements UserBatchProducer {
    private JmsTemplate jmsTemplate;

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void processUsers(List<User> users) {
        for (User user : users) {
            jmsTemplate.convertAndSend(user);
        }
    }
}
