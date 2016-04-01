package com.ps;

import com.ps.services.impl.SimplePetService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by iuliana.cosmina on 4/17/16.
 */
@Configuration
@ComponentScan(basePackages = "com.ps.services")
public class PetConfigClass {
}
