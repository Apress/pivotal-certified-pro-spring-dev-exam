package com.ps.pet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by iuliana.cosmina on 10/26/16.
 */
@Configuration
@ComponentScan
@EntityScan("com.ps.pet")
@EnableJpaRepositories("com.ps.pet")
@PropertySource("classpath:db/pet/db.properties")
public class PetServiceConfig {

	private static Logger logger = LoggerFactory.getLogger(PetServiceConfig.class);

	@Bean
	public DataSource dataSource() {
		logger.debug("creating database");
		DataSource dataSource = (new EmbeddedDatabaseBuilder())
				.setType(EmbeddedDatabaseType.H2)
				.addScript("classpath:db/pet/schema.sql")
				.addScript("classpath:db/pet/data.sql").build();


		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Map<String, Object>> pets = jdbcTemplate.queryForList("SELECT id FROM P_PET");
		logger.info("System has " + pets.size() + " pets");

		return dataSource;
	}
}
