package com.ps.config;

import com.ps.aspects.UserRepoMonitor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by iuliana.cosmina on 6/3/16.
 */
@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {

    @Bean
    public UserRepoMonitor userRepoMonitor(){
        return new UserRepoMonitor();
    }
}
