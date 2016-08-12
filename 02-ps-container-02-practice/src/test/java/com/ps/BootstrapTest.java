package com.ps;

import com.ps.config.*;
import com.ps.repos.PetRepo;
import com.ps.repos.RequestRepo;
import com.ps.repos.UserRepo;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 3/31/16.
 */
public class BootstrapTest {

    /**
     * Bootstrap Configuration class using context class
     */
    @Test
    public void testStart1() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(DataSourceConfig.class);

        DataSource dataSource = ctx.getBean("two", DataSource.class);
        assertNotNull(dataSource);
    }

    /**
     * Bootstrap using XML context class
     */
    @Test
    public void testStart2() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/test-config-04.xml");

        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);
    }


    @Test
    public void testStart4() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(DataSourceConfig.class, PetRepoConfig.class);

        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);

        PetRepo petRepo = ctx.getBean("petRepo", PetRepo.class);
        assertNotNull(petRepo);
    }

    @Test
    public void testStart5() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(DataSourceConfig.class, RequestRepoConfig.class);

        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);

        RequestRepo requestRepo = ctx.getBean("requestRepo", RequestRepo.class);
        assertNotNull(requestRepo);
    }

    @Test
    public void testStart6() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(DataSourceConfig1.class);

        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);
    }

}
