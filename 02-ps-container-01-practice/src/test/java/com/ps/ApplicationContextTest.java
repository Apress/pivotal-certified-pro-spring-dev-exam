package com.ps;

import com.ps.config.ApplicationConfig;
import com.ps.services.impl.SimpleUserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 3/23/16.
 */
public class ApplicationContextTest {

    @Test
    public void testConfig() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        // everything wires up across configuration classes...
        SimpleUserService simpleUserService = (SimpleUserService) ctx.getBean("simpleUserService");
        assertNotNull(simpleUserService);
    }
}
