package com.ps;

import com.ps.config.RequestRepoConfig;
import com.ps.repos.RequestRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 3/31/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RequestRepoConfig.class})
public class AutowiringTest {

    @Qualifier("requestRepo")
    @Autowired
    RequestRepo reqRepo;

    @Test
    public void testAutowiredReq() {
        assertNotNull(reqRepo);
    }
}
