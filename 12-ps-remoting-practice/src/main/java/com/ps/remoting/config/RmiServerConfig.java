package com.ps.remoting.config;

import com.ps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 * Created by iuliana.cosmina on 10/2/16.
 */
@Configuration
public class RmiServerConfig {

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;

    @Bean
    public RmiServiceExporter userService() {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setRegistryPort(1099);
        exporter.setAlwaysCreateRegistry(true);
        exporter.setServiceName("userService");
        exporter.setServiceInterface(UserService.class);
        exporter.setService(userService);
        return exporter;
    }

}
