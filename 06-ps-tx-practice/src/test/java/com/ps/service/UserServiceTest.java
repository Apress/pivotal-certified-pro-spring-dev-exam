package com.ps.service;

import com.ps.ents.User;
import com.ps.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 7/15/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/app-config1.xml",
        "classpath:spring/common-cfg.xml",
        "classpath:spring/template-cfg.xml"})
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Before
    public void setUp() {
        assertNotNull(userService);
    }

    @Test
    public void testFindById(){
        User user = userService.findById(1L);
        assertNotNull(user);
    }
}
