package com.ps.start;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

/**
 * Created by iuliana.cosmina on 8/22/16.
 */
@SpringBootApplication(scanBasePackages = {"com.ps.web", "com.ps.start"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("Started ...");
    }


    @Value("${app.port}")
    private Integer value;

    @Value("${app.context}")
    private String contextPath;


    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
        factory.setPort(value);
        factory.setSessionTimeout(10, TimeUnit.MINUTES);
        factory.setContextPath(contextPath);
        return factory;
    }
}
