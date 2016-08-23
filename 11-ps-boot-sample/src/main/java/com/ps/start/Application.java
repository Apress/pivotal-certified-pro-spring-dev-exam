package com.ps.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by iuliana.cosmina on 8/22/16.
 */
@SpringBootApplication(scanBasePackages = {"com.ps.web", "com.ps.start"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("Started ...");
    }
}
