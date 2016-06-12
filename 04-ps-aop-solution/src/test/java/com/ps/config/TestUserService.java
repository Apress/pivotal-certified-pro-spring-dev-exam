package com.ps.config;

import com.ps.ents.User;
import com.ps.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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

    @Test(expected = IllegalArgumentException.class)
    public void testBeforeUpdatePass() {
        userService.updatePassword(1L, "new%pass");
        User user = userService.findById(1L);
        assertEquals("new%pass", user.getPassword());
    }

    @Test
    public void testAfterUpdatePass() {
        int result = userService.updatePassword(1L, "new_pass");
        assertEquals(1, result);
    }

    @Test(expected = DuplicateKeyException.class)
    public void testAfterUpdateUsernameBad() {
        int result = userService.updateUsername(1L, "Johnny");
        assertEquals(1, result);
    }

    @Test
    public void testAfterUpdateUsernameGood() {
        int result = userService.updateUsername(1L, "Darren");
        assertEquals(1, result);
    }

    @Test
    public void testFindById() {
        User user = userService.findById(2L);
        assertEquals("Mary", user.getUsername());
    }

    @Test
    public void testAll() {
        Set<User> result = userService.findAll();
        assertEquals(4, result.size());
    }


    @Test
    public void testProxyBubu() {
        int result = userService.updateUsername(3L, "Iuliana");
        assertEquals(1, result);
    }

    @Test
    public void testProxyBubuDeps() {
        int result = userService.updateDependencies(3L);
        assertEquals(0, result);
    }
}
