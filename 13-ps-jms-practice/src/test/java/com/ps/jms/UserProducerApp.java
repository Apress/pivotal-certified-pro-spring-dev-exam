package com.ps.jms;

import com.ps.config.ServiceConfig;
import com.ps.ents.User;
import com.ps.jms.config.JmsCommonConfig;
import com.ps.jms.config.JmsConsumerConfig;
import com.ps.jms.config.JmsProducerConfig;
import com.ps.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by iuliana.cosmina on 10/11/16.
 */
public class UserProducerApp {
    private static final Logger logger = LoggerFactory.getLogger(UserProducerApp.class);

    public static void main(String[] args) throws IOException {
        AbstractApplicationContext context= new AnnotationConfigApplicationContext(
                ServiceConfig.class, JmsCommonConfig.class, JmsProducerConfig.class);
        UserService userService = context.getBean(UserService.class);
        UserSender userSender = context.getBean(UserSender.class);

        List<User> users = userService.findAll();

        assertTrue(users.size() >0);
        for (User user: users) {
            userSender.sendMessage(user);
        }

        logger.info("User message sent. Wait for confirmation...");

        System.in.read();

        context.close();
    }
}
