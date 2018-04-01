package com.ps.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import java.io.IOException;

/**
 * Created by iuliana.cosmina on 10/25/16.
 */
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServer {

	private static Logger logger = LoggerFactory.getLogger(DiscoveryServer.class);

	public static void main(String[] args) throws IOException {
		// Tell server to look for discovery.properties or discovery.yml
		System.setProperty("spring.config.name", "discovery");

		ConfigurableApplicationContext ctx = SpringApplication.run(DiscoveryServer.class, args);
		assert (ctx != null);
		logger.info("Started ...");
		System.in.read();
		ctx.close();
	}

}
