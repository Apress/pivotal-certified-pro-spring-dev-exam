package com.ps.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.support.ConnectorServerFactoryBean;
import org.springframework.jmx.support.MBeanServerFactoryBean;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;

import javax.annotation.ManagedBean;
import javax.management.MalformedObjectNameException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by iuliana.cosmina on 10/18/16.
 */
@SpringBootApplication
@EnableMBeanExport
public class Application {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    //TODO IULIANA: fix this so programatic clients works properly too sometime...
    /*@Bean
    @DependsOn("rmiRegistry")
    ConnectorServerFactoryBean connector() {
        ConnectorServerFactoryBean cf = new ConnectorServerFactoryBean();
        try {
            cf.setObjectName("connector:name=rmi");
        } catch (MalformedObjectNameException e) {
            return null;
        }
        cf.setServiceUrl("service:jmx:rmi://localhost/jndi/rmi://localhost:1099/jmxrmi");
        return cf;
    }

    @Bean
    RmiRegistryFactoryBean rmiRegistry() {
        RmiRegistryFactoryBean rmiRegistryFactoryBean = new RmiRegistryFactoryBean();
        rmiRegistryFactoryBean.setAlwaysCreate(true);
        rmiRegistryFactoryBean.setPort(1099);
        return rmiRegistryFactoryBean;
    }*/

    /*@Bean
    MBeanServerFactoryBean mbeanServer() {
        return new MBeanServerFactoryBean();
    }
*/
   /* @Bean
    MBeanExporter exporter() {
        MBeanExporter exporter = new MBeanExporter();
        exporter.setAutodetect(true);
        return exporter;
    }*/

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        assert (ctx != null);
        logger.info("Started ...");
        System.in.read();
        ctx.close();
    }
}
