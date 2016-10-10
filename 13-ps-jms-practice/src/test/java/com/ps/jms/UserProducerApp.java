package com.ps.jms;

import com.ps.ents.User;
import com.ps.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 10/11/16.
 */
public class UserProducerApp {
    private static final Logger logger = LoggerFactory.getLogger(UserProducerApp.class);

    public static void main(String[] args) throws IOException {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/app-config.xml", "classpath:spring/jms-config-one.xml");
        UserService userService = context.getBean(UserService.class);
        UserSender userSender = context.getBean(UserSender.class);

        User user = userService.findByEmail("john.cusack@pet.com");
        assertNotNull(user);
        userSender.sendMessage(user);
        logger.info("User message sent. Wait for confirmation...");

        System.in.read();

        context.close();
    }
}
