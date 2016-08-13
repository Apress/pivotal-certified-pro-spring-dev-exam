package com.ps.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by iuliana.cosmina on 4/17/16.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.ps.repos", "com.ps.services.impl", "com.ps.init", "com.ps.config.db"})
public class AppConfig {

}
