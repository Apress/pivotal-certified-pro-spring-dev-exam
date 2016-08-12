package com.ps.config;

import com.ps.repos.PetRepo;
import com.ps.services.PetService;
import com.ps.services.impl.SimplePetService;
import org.springframework.context.annotation.Bean;

/**
 * Created by iuliana.cosmina on 5/4/16.
 */
public class PetConfigClass2 {

    @Bean
    public PetService simplePetService(){
        return new SimplePetService();
    }
}
