package com.ps.config;

/**
 * Created by iuliana.cosmina on 3/21/16.
 */

import com.ps.repos.UserRepo;
import com.ps.repos.impl.JdbcUserRepo;
import com.ps.services.UserService;
import com.ps.services.impl.SimpleUserService;
import oracle.jdbc.pool.OracleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@PropertySource("classpath:db/datasource.properties")
public class ApplicationConfig {

    @Value("${url}")
    private String url;
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;


    @Bean
    public UserService simpleUserService() throws SQLException {
        return new SimpleUserService(userRepo());
    }

    @Bean
    public UserRepo userRepo() throws SQLException {
        return new JdbcUserRepo(dataSource());
    }

    @Bean
    public DataSource dataSource() throws SQLException {
        OracleDataSource ds = new OracleDataSource();
        ds.setURL(url);
        ds.setUser(username);
        ds.setPassword(password);
        return ds;
    }
}
