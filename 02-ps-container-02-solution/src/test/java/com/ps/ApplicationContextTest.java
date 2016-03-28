package com.ps;

import com.ps.config.DataSourceConfig;
import com.ps.sample.ComplexBean;
import com.ps.services.UserService;
import com.ps.services.impl.SimpleUserService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

import java.util.Properties;

import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 3/23/16.
 */
public class ApplicationContextTest {

    private Logger logger = LoggerFactory.getLogger(ApplicationContextTest.class);

    @Test
    public void testDataSource1() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/test-db01-config.xml");
        logger.info(" >> init done.");
        DataSource dataSource1 = ctx.getBean("dataSource1", DataSource.class);
        assertNotNull(dataSource1);
        logger.info(" >> usage done.");
        ctx.registerShutdownHook();
    }

    @Test
    public void testDataSource2() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/test-db02-config.xml");

        DataSource dataSource2 = ctx.getBean("dataSource2", DataSource.class);
        assertNotNull(dataSource2);
    }

    @Test
    public void testDataSource3() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/test-db03-config.xml");

        DataSource dataSource3 = ctx.getBean("dataSource3", DataSource.class);
        assertNotNull(dataSource3);

        Properties dbProp = ctx.getBean("dbProp", Properties.class);
        assertNotNull(dbProp);
    }

    @Test
    public void testDataSource4() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(DataSourceConfig.class);

        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);
    }


    @Test
    public void testBeanCreation() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/test-db04-config.xml");

        ComplexBean complexBean = ctx.getBean(ComplexBean.class);
        assertNotNull(complexBean);
        ctx.registerShutdownHook();
    }

}
