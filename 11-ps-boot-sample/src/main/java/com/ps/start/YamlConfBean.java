package com.ps.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;

/**
 * Created by iuliana.cosmina on 9/27/15.
 */
@Component
public class YamlConfBean implements EmbeddedServletContainerCustomizer {

    @Autowired
    private AppSettings appSettings;


    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(appSettings.getPort());
        container.setContextPath(appSettings.getContext());
    }
}

