package com.ps.start;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;

/**
 * Created by iuliana.cosmina on 9/23/15.
 * Description: Class that depicts how port and contextPath can be set for a Spring Boot application using a property file
 * In order to see this class in action, comment other customization classes annotations, decomment this and run the application.
 */

@Component
public class PropertiesConfBean implements EmbeddedServletContainerCustomizer {

    @Value("${app.port}")
    private Integer value;

    @Value("${app.context}")
    private String contextPath;


    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(value);
        container.setContextPath(contextPath);
    }
}


