package com.book.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;

/**
 * Created by iuliana.cosmina on 9/27/15.
 */
@ConfigurationProperties(prefix="app")
public class AppSettings {

    private static Logger logger = LoggerFactory.getLogger(AppSettings.class);

    @NotNull
    private Integer port;

    @NotNull
    private String context;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public AppSettings() {
    }

    @PostConstruct
    public void check() {
        logger.info("Initialized [{}] [{}]", port, context);
    }
}