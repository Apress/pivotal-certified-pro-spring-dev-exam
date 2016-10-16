package com.ps.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by iuliana.cosmina on 8/22/16.
 */
@SpringBootApplication(scanBasePackages = {"com.ps.ws"})
public class Application extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String... args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        logger.info("started...");
        System.in.read();
        context.close();
    }

    @Autowired
    private AppSettings appSettings;

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.setPort(appSettings.getPort());
        factory.setSessionTimeout(appSettings.getSessionTimeout(), TimeUnit.MINUTES);
        factory.setContextPath(appSettings.getContext());
        return factory;
    }
}
