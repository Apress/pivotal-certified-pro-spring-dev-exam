package com.ps.pet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

import java.io.IOException;

/**
 * Created by iuliana.cosmina on 10/26/16.
 */
@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(PetServiceConfig.class)
public class PetServer {

	private static Logger logger = LoggerFactory.getLogger(PetServer.class);

	public static void main(String[] args) throws IOException {
		// Tell server to look for pet-server.properties or pet-server.yml
		System.setProperty("spring.config.name", "pet-server");
		ConfigurableApplicationContext ctx = SpringApplication.run(PetServer.class, args);
		assert (ctx != null);
		logger.info("Started ...");
		System.in.read();
		ctx.close();
	}
}
