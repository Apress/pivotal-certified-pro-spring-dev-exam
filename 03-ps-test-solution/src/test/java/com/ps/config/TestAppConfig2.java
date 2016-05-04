package com.ps.config;

import com.ps.repo.stub.StubPetRepo;
import com.ps.repos.PetRepo;
import com.ps.services.PetService;
import com.ps.services.impl.SimplePetService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by iuliana.cosmina on 4/17/16.
 */
@Configuration
public class TestAppConfig2 {

    @Bean(initMethod = "init")
    public PetRepo petRepo(){
        return new StubPetRepo();
    }
}
