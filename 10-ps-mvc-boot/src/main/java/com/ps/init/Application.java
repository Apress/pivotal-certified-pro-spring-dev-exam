package com.ps.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by iuliana.cosmina on 8/15/16.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.ps.init", "com.ps.web"})
@EnableConfigurationProperties(AppSettings.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
