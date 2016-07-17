package com.ps.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by iuliana.cosmina on 4/17/16.
 */
@Configuration
@ComponentScan(basePackages = {"com.ps.services.impl", "com.ps.repos.impl"})
public class PetConfigClass {
}
