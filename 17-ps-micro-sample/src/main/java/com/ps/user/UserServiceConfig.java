package com.ps.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by iuliana.cosmina on 10/26/16.
 */
@Configuration
@ComponentScan
@EntityScan("com.ps.user")
@EnableJpaRepositories("com.ps.user")
@PropertySource("classpath:db/user/db.properties")
public class UserServiceConfig {

	private static Logger logger = LoggerFactory.getLogger(UserServiceConfig.class);

	@Bean
	public DataSource dataSource() {
		logger.debug("creating database");
		DataSource dataSource = (new EmbeddedDatabaseBuilder())
				.setType(EmbeddedDatabaseType.H2)
				.addScript("classpath:db/user/schema.sql")
				.addScript("classpath:db/user/data.sql").build();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> users = jdbcTemplate.queryForList("SELECT id FROM P_USER");

		logger.info("System has " + users.size() + " users");
		return dataSource;
	}

}
