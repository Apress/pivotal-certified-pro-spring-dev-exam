package com.ps.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by iuliana.cosmina on 3/21/16.
 */
@Configuration
public class DataSourceConfig1 {

    //TODO 13. Replace where possible all Spring annotations with JSR

    @Bean
    public Properties dbProps(){
        Properties p = new Properties();
        p.setProperty("driverClassName", "org.h2.Driver");
        p.setProperty("url", "jdbc:h2:~/sample");
        p.setProperty("username", "sample");
        p.setProperty("password", "sample");
        return p;
    }

    @Bean
    public DataSource dataSource(@Value("#{dbProps.driverClassName}")String driverClassName,
                                 @Value("#{dbProps.url}")String url,
                                 @Value("#{dbProps.username}")String username,
                                 @Value("#{dbProps.password}")String password) throws SQLException {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driverClassName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }
}
