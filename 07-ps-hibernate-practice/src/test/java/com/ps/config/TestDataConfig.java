package com.ps.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Driver;
import java.util.Properties;

/**
 * Created by iuliana.cosmina on 7/5/16.
 */
@Configuration
@Profile("dev")
@PropertySource({"classpath:db/db.properties", "classpath:db/test-hibernate.properties"})
public class TestDataConfig {

    @Value("${driverClassName}")
    private String driverClassName;
    @Value("${url}")
    private String url;
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Lazy
    @Bean
    public DataSource dataSource() {
        try {
            SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
            Class<? extends Driver> driver = (Class<? extends Driver>) Class.forName(driverClassName);
            dataSource.setDriverClass(driver);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            return dataSource;
        } catch (Exception e) {
            return null;
        }
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        return initializer;
    }


    @Bean
    public JdbcTemplate userJdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }


    @Bean
    public Properties hibernateProperties(
            @Value("${hibernate.dialect}") String dialect,
            @Value("${hibernate.hbm2ddl.auto}") String strategy,
            @Value("${hibernate.format_sql}") Boolean formatSql,
            @Value("${hibernate.use_sql_comments}") Boolean sqlComments,
            @Value("${hibernate.show_sql}") Boolean showSql,
            @Value("${hibernate.transaction.factory_class}") String factoryClass,
            @Value("${hibernate.connection.autocommit}") Boolean autocommit

    ) {
        Properties hibernateProp = new Properties();
        hibernateProp.put("hibernate.dialect", dialect);
        hibernateProp.put("hibernate.hbm2ddl.auto", strategy);
        hibernateProp.put("hibernate.format_sql", formatSql);
        hibernateProp.put("hibernate.use_sql_comments", sqlComments);
        hibernateProp.put("hibernate.show_sql", showSql);
        hibernateProp.put("hibernate.transaction.factory_class", factoryClass);
        hibernateProp.put("hibernate.connection.autocommit", autocommit);
        return hibernateProp;
    }

    @Autowired
    @Qualifier("hibernateProperties")
    Properties hibernateProperties;

    @Bean
    public SessionFactory sessionFactory() throws IOException{
        LocalSessionFactoryBean factBean = new LocalSessionFactoryBean();
        factBean.setDataSource(dataSource());
        factBean.setPackagesToScan("com.ps.ents");
        factBean.setHibernateProperties(hibernateProperties);
        factBean.afterPropertiesSet();
        return factBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager()  throws IOException{
        return new HibernateTransactionManager(sessionFactory());
    }
}
