package com.ps.client;

import com.ps.start.JmxCounter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.access.MBeanProxyFactoryBean;
import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;

import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;

/**
 * Created by iuliana.cosmina on 10/19/16.
 */
@Configuration
public class TestConfig {

    @Bean
    MBeanServerConnection connection() {
        MBeanServerConnectionFactoryBean conn = new MBeanServerConnectionFactoryBean();
        try {
            conn.setServiceUrl("service:jmx:rmi://localhost/jndi/rmi://localhost:1099/jmxrmi");
            conn.afterPropertiesSet();
            return conn.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    MBeanProxyFactoryBean proxy() throws MalformedObjectNameException {
        MBeanProxyFactoryBean proxy = new MBeanProxyFactoryBean();
        proxy.setObjectName("bean:name=jmxCounter");
        proxy.setProxyInterface(JmxCounter.class);
        proxy.setServer(connection());
        return proxy;
    }
}
