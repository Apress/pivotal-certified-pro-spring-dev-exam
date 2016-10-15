package com.ps.jms.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import javax.jms.ConnectionFactory;

/**
 * Created by iuliana.cosmina on 10/13/16.
 */
@Configuration
@ComponentScan(basePackages = {"com.ps.jms"})
public class JmsCommonConfig {

    @Bean
    public ConnectionFactory nativeConnectionFactory(){
        ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
        cf.setBrokerURL("tcp://localhost:61616");
        cf.setTrustAllPackages(true);
        return cf;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cf =  new CachingConnectionFactory();
        cf.setTargetConnectionFactory(nativeConnectionFactory());
        return cf;
    }

    @Bean
    public ActiveMQQueue userQueue(){
        return new ActiveMQQueue("com.queue.user");
    }

    @Bean
    public ActiveMQQueue confirmationQueue(){
        return new ActiveMQQueue("com.queue.confirmation");
    }

    @Bean
    public MessageConverter converter() {
        return new SimpleMessageConverter();
    }

}