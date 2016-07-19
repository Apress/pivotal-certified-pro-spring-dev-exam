package com.ps.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by iuliana.cosmina on 7/5/16.
 */
@Profile("dev")
@PropertySource({"classpath:db/db.properties", "classpath:db/test-hibernate.properties"})
@Configuration
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

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        try {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setDriverClassName(driverClassName);
            hikariConfig.setJdbcUrl(url);
            hikariConfig.setUsername(username);
            hikariConfig.setPassword(password);

            hikariConfig.setMaximumPoolSize(5);
            hikariConfig.setConnectionTestQuery("SELECT 1");
            hikariConfig.setPoolName("springHikariCP");

            hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
            hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
            hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
            hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");

            HikariDataSource dataSource = new HikariDataSource(hikariConfig);

            return dataSource;

        } catch (Exception e) {
            return null;
        }
    }

    @Bean
    public Properties hibernateProperties(
            @Value("${hibernate.dialect}") String dialect,
            @Value("${hibernate.hbm2ddl.auto}") String strategy,
            @Value("${hibernate.format_sql}") Boolean formatSql,
            @Value("${hibernate.use_sql_comments}") Boolean sqlComments,
            @Value("${hibernate.show_sql}") Boolean showSql,
            @Value("${hibernate.transaction.factory_class}") String factoryClass,
            @Value("${hibernate.transaction.jta.platform}") String jtaPlatform,
            @Value("${hibernate.current_session_context_class}") String ctxClass


    ) {
        Properties hibernateProp = new Properties();
        hibernateProp.put("hibernate.dialect", dialect);
        hibernateProp.put("hibernate.hbm2ddl.auto", strategy);
        hibernateProp.put("hibernate.format_sql", formatSql);
        hibernateProp.put("hibernate.use_sql_comments", sqlComments);
        hibernateProp.put("hibernate.show_sql", showSql);
        //hibernateProp.put("hibernate.transaction.factory_class", factoryClass);
        //hibernateProp.put("hibernate.current_session_context_class", ctxClass);
        //hibernateProp.put("hibernate.transaction.jta.platform", jtaPlatform);
        return hibernateProp;
    }

    @Autowired
    @Qualifier("hibernateProperties")
    Properties hibernateProperties;

    @Bean
    public SessionFactory sessionFactory() throws IOException {
        return new LocalSessionFactoryBuilder(dataSource())
                .scanPackages("com.ps.ents")
                .addProperties(hibernateProperties)
                .buildSessionFactory();
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws IOException {
        return new HibernateTransactionManager(sessionFactory());
    }
}
