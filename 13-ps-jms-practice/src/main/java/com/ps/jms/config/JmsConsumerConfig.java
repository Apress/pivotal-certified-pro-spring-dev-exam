package com.ps.jms.config;

import com.ps.jms.ConfirmationSender;
import com.ps.jms.UserReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

/**
 * Created by iuliana.cosmina on 10/13/16.
 */
@Configuration
public class JmsConsumerConfig {

    @Autowired
    JmsCommonConfig jmsCommonConfig;

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(jmsCommonConfig.connectionFactory());
        jmsTemplate.setDefaultDestination(jmsCommonConfig.confirmationQueue());
        return  jmsTemplate;
    }

    @Bean
    public UserReceiver userReceiver(){
        return new UserReceiver();
    }

    @Bean
    public DefaultMessageListenerContainer containerListener() {
        DefaultMessageListenerContainer listener = new DefaultMessageListenerContainer();
        listener.setConnectionFactory(jmsCommonConfig.connectionFactory());
        listener.setDestination(jmsCommonConfig.userQueue());
        listener.setMessageListener(userReceiver());
        return listener;
    }
}