package com.ps;

import com.ps.sample.ComplexBean;
import com.ps.sample.SimpleBean;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 3/23/16.
 */
public class ApplicationContextTest {

    private Logger logger = LoggerFactory.getLogger(ApplicationContextTest.class);

    private ConfigurableApplicationContext ctx;

    @Test
    public void testDataSource1() {
        ctx = new ClassPathXmlApplicationContext("classpath:spring/application-config.xml");
        logger.info(" >> init done.");
        DataSource dataSource1 = ctx.getBean("dataSource1", DataSource.class);
        assertNotNull(dataSource1);
        logger.info(" >> usage done.");
        ctx.close();
    }

    @Test
    public void testBeanCreation() {
        ctx = new ClassPathXmlApplicationContext("classpath:spring/test-config-01.xml");

        ComplexBean complexBean = ctx.getBean("complexBean2", ComplexBean.class);
        assertNotNull(complexBean);
        SimpleBean simpleBean1 = complexBean.getSimpleBean1();
        assertEquals(simpleBean1, SimpleBean.DEFAULT_SIMPLE_BEAN);
        ctx.close();
    }

    @Test
    public void testBeanScope() {
        ctx = new ClassPathXmlApplicationContext("classpath:spring/test-config-02.xml");

        SimpleBean sb01 = ctx.getBean("simpleBean3", SimpleBean.class);
        SimpleBean sb02 = ctx.getBean("simpleBean3", SimpleBean.class);
        assertNotEquals(sb01, sb02);
        ctx.close();
    }

}
