package com.ps.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by iuliana.cosmina on 5/4/16.
 */
@Configuration
@ComponentScan(basePackages = "com.ps.repos.impl")
public class JdbcRepoConfig {
}
