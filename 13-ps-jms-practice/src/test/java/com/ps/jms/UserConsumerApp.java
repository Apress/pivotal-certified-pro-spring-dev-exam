package com.ps.jms;

import com.ps.config.ServiceConfig;
import com.ps.ents.User;
import com.ps.jms.config.JmsCommonConfig;
import com.ps.jms.config.JmsConsumerConfig;
import com.ps.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 10/11/16.
 */
public class UserConsumerApp {
    private static final Logger logger = LoggerFactory.getLogger(UserConsumerApp.class);

    public static void main(String[] args) throws IOException {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(
              JmsCommonConfig.class, JmsConsumerConfig.class);

        UserReceiver userReceiver = context.getBean(UserReceiver.class);
        assertNotNull(userReceiver);
        logger.info("Waiting for user ...");
        System.in.read();
        context.close();
    }
}
