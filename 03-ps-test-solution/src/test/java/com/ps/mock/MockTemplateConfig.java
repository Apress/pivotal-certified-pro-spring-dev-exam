package com.ps.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.Mockito.mock;

/**
 * Created by iuliana.cosmina on 4/17/16.
 */
@Configuration
public class MockTemplateConfig {

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return mock(JdbcTemplate.class);
    }
}
