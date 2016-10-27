package com.ps.user;

import com.ps.pet.PetServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by iuliana.cosmina on 10/26/16.
 */
@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(UserServiceConfig.class)
public class UserServer {

	private static Logger logger = LoggerFactory.getLogger(UserServer.class);

	public static void main(String[] args) throws IOException {
		// Tell server to look for user-server.properties or user-server.yml
		System.setProperty("spring.config.name", "user-server");
		ConfigurableApplicationContext ctx = SpringApplication.run(UserServer.class, args);
		assert (ctx != null);
		logger.info("Started ...");
		System.in.read();
		ctx.close();
	}

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
