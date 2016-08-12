package com.ps;

import com.ps.config.UserRepoDSConfig;
import com.ps.repos.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 5/2/16.
 * Description: Bootstrap Configuration class using context class
 */
public class XmlToCfgTest {

    private ApplicationContext ctx;

    @Before
    public void setup() {
        ctx = new AnnotationConfigApplicationContext(UserRepoDSConfig.class);
        assertNotNull(ctx);
    }

    /**
     * Bootstrap Configuration class using context class
     */
    @Test
    public void testStart3() {
        DataSource dataSource = ctx.getBean("dataSource", DataSource.class);
        assertNotNull(dataSource);

        UserRepo userRepo = ctx.getBean("userRepo", UserRepo.class);
        assertNotNull(userRepo);
    }
}
