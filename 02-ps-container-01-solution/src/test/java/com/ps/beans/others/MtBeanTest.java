package com.ps.beans.others;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 3/26/16.
 */
public class MtBeanTest {

    private Logger logger = LoggerFactory.getLogger(MtBeanTest.class);

    @Test
    public void testConfig() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/others/sample-config-01.xml");

        MultipleTypesBean mtBean = (MultipleTypesBean) ctx.getBean("mtBean");
        assertNotNull(mtBean);

        //expected no of beans in the context
        int beanCount1 = ctx.getBeanDefinitionNames().length;

        ApplicationContext ctx2 = new ClassPathXmlApplicationContext(
                "classpath:spring/others/*-cfg.xml"
        );

        int beanCount2 = ctx2.getBeanDefinitionNames().length;

        assertEquals(beanCount1, beanCount2);

    }
}
