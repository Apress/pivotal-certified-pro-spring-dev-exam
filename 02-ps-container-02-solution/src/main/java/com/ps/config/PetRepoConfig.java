package com.ps.config;

import com.ps.repos.PetRepo;
import com.ps.repos.impl.JdbcPetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by iuliana.cosmina on 3/21/16.
 * Sample class to depict how @ImportResource can be used
 */
@Configuration
public class PetRepoConfig {


    @Autowired
    DataSource dataSource;

    @Bean
    public PetRepo petRepo() {
        return new JdbcPetRepo(dataSource);
    }
}
