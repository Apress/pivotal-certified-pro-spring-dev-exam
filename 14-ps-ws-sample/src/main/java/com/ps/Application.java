package com.ps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

/**
 * Created by iuliana.cosmina on 8/22/16.
 */
@SpringBootApplication
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String... args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        logger.info("started...");
        System.in.read();
        context.close();
    }

}
