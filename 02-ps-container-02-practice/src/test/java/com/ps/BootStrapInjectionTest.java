package com.ps;

import com.ps.config.DataSourceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 3/31/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourceConfig.class})
public class BootStrapInjectionTest {

    @Autowired
    DataSource dataSource;

    @Test
    public void testBoot() {
        assertNotNull(dataSource);
    }
}
