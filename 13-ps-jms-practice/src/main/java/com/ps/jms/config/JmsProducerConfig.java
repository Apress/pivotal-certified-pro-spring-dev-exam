package com.ps.jms.config;

import com.ps.jms.ConfirmationReceiver;
import com.ps.jms.UserReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

/**
 * Created by iuliana.cosmina on 10/13/16.
 */
@Configuration
public class JmsProducerConfig {

    @Autowired
    JmsCommonConfig jmsCommonConfig;

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(jmsCommonConfig.connectionFactory());
        jmsTemplate.setDefaultDestination(jmsCommonConfig.userQueue());
        return  jmsTemplate;
    }

    @Bean
    public ConfirmationReceiver confirmationReceiver(){
        return new ConfirmationReceiver();
    }

    @Bean
    public DefaultMessageListenerContainer containerListener() {
        DefaultMessageListenerContainer listener = new DefaultMessageListenerContainer();
        listener.setConnectionFactory(jmsCommonConfig.connectionFactory());
        listener.setDestination(jmsCommonConfig.confirmationQueue());
        listener.setMessageListener(confirmationReceiver());
        return listener;
    }
}