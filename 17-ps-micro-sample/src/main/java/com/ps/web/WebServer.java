package com.ps.web;

import com.ps.user.UserServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by iuliana.cosmina on 10/27/16.
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters = false) // Disable component scanner,
// needed because of the microservices we need access to
public class WebServer {

	private static Logger logger = LoggerFactory.getLogger(WebServer.class);

	public static final String USERS_SERVICE_URL = "http://USERS-SERVICE";

	public static final String PETS_SERVICE_URL = "http://PETS-SERVICE";


	public static void main(String[] args) throws IOException {
		// Tell server to look for web-server.properties or web-server.yml
		System.setProperty("spring.config.name", "web-server");
		ConfigurableApplicationContext ctx = SpringApplication.run(WebServer.class, args);
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


	@Bean
	public AllWebService allWebService() {
		return new AllWebService(USERS_SERVICE_URL, PETS_SERVICE_URL);
	}


	@Bean AllWebController allController() {
		return new AllWebController(allWebService());
	}

	@Bean
	public HomeController homeController() {
		return new HomeController();
	}

}
