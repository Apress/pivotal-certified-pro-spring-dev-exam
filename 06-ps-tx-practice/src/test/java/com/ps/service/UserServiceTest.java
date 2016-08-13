package com.ps.services;

import com.ps.config.AppConfig;
import com.ps.config.PersistenceConfig;
import com.ps.config.db.TestDataConfig;
import com.ps.ents.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 7/15/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, AppConfig.class})
@ActiveProfiles("dev")
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Before

    @Test
    public void testFindById() {
        User user = userService.findById(1L);
        assertNotNull(user);
    }
}
