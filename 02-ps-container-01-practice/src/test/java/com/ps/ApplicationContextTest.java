package com.ps;

import com.ps.services.UserService;
import com.ps.services.impl.SimpleUserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 3/23/16.
 */
public class ApplicationContextTest {

    @Test
    public void testConfig() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/application-config.xml");
        // everything wires up across configuration classes...
        UserService simpleUserService = (UserService) ctx.getBean("simpleUserService");
        assertNotNull(simpleUserService);
    }
}
