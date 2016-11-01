package com.ps.invoker.config;

import com.ps.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

/**
 * Created by iuliana.cosmina on 10/7/16.
 */
@Configuration
public class HessianClientConfig {

    @Bean
    public UserService userServiceHessian() {
        HessianProxyFactoryBean factoryBean = new HessianProxyFactoryBean();
        factoryBean.setServiceInterface(UserService.class);
        factoryBean.setServiceUrl("http://localhost:8080/hessian/hessianInvoker/userService");
        factoryBean.afterPropertiesSet();
        return (UserService) factoryBean.getObject();
    }

}
