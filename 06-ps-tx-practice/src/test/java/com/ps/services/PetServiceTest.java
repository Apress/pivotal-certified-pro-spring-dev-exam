package com.ps.services;

import com.ps.config.AppConfig;
import com.ps.config.TestDataConfig;
import com.ps.ents.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by iuliana.cosmina on 7/16/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataConfig.class, AppConfig.class})
@ActiveProfiles("dev")
public class PetServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    PetService petService;

    @Before
    public void setUp() {
        assertNotNull(userService);
        assertNotNull(petService);
    }

    @Test
    public void testFindById() {
        User user = userService.findById(1L);
        String out = petService.getPetsAsHtml(user);
        assertTrue(out.contains("Name"));
        System.out.println(out);
    }

}