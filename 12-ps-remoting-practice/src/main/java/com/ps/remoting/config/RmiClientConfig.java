package com.ps.remoting.config;

import com.ps.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

/**
 * Created by iuliana.cosmina on 10/4/16.
 */
@Configuration
public class RmiClientConfig {

    @Bean
    public UserService userService() {
        RmiProxyFactoryBean factoryBean = new RmiProxyFactoryBean();
        factoryBean.setServiceInterface(UserService.class);
        factoryBean.setServiceUrl("rmi://localhost:1099/userService");
        factoryBean.afterPropertiesSet();
        return (UserService) factoryBean.getObject();
    }
}
