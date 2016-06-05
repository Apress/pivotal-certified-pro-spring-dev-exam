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

    // test Before advice
    @Test(expected = IllegalArgumentException.class)
    public void testBeforeUpdatePass() {
        userService.updatePassword(1L, "new%pass");
        User user = userService.findById(1L);
        assertEquals("new%pass", user.getPassword());
    }

    //test AfterReturning advice
    @Test
    public void testAfterUpdatePass() {
        int result = userService.updatePassword(1L, "new_pass");
        assertEquals(1, result);
    }

    //test AfterThrowing advice
    @Test(expected = DuplicateKeyException.class)
    public void testAfterUpdateUsernameBad() {
        int result = userService.updateUsername(1L, "Johnny");
        assertEquals(1, result);
    }

    //test AfterThrowing advice
    @Test
    public void testAfterUpdateUsernameGood() {
        int result = userService.updateUsername(1L, "Darren");
        assertEquals(1, result);
    }

    //test Around advice
    @Test
    public void testFindById() {
        User user = userService.findById(2L);
        assertEquals("Mary", user.getUsername());
    }

    //test Around advice
    @Test
    public void testAll() {
        Set<User> result = userService.findAll();
        assertEquals(4, result.size());
    }
}
