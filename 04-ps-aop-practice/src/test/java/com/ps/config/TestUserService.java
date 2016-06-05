package com.ps.config;

import com.ps.ents.User;
import com.ps.repos.UserRepo;
import com.ps.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 6/4/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataConfig.class, AppConfig.class})
@ActiveProfiles("dev")
public class TestUserService {

    @Autowired
    UserService userService;

    @Before
    public void setUp() {
        assertNotNull(userService);
    }

    @Test
    public void testUpdatePass() {
        userService.updatePassword(1L, "new_pass");
        User user = userService.findById(1L);
        assertEquals("new_pass", user.getPassword());
    }

    @Test
    public void testAll() {
        Set<User> result = userService.findAll();
        assertEquals(4, result.size());
    }
}
