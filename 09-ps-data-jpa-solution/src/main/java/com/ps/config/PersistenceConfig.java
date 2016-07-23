package com.ps.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by iuliana.cosmina on 7/23/16.
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.ps.repos"})
@Import(DataSourceConfig.class)
public class PersistenceConfig {

    @Autowired
    DataSource dataSource;

    @Autowired
    Properties hibernateProperties;

    @Bean
    public EntityManagerFactory entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitManager(persistenceUnitManager());
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(hibernateProperties);
        factoryBean.afterPropertiesSet();
        return factoryBean.getNativeEntityManagerFactory();
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws IOException {
        return new JpaTransactionManager(entityManagerFactory());
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public PersistenceUnitManager persistenceUnitManager(){
        DefaultPersistenceUnitManager persistenceUnitManager = new DefaultPersistenceUnitManager();
        persistenceUnitManager.setPackagesToScan("com.ps.ents");
        persistenceUnitManager.setDefaultDataSource(dataSource);
        return persistenceUnitManager;
    }
}
