package com.ps.config;

import com.ps.ents.User;
import com.ps.repos.UserRepo;
import com.ps.repos.impl.JdbcUserRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 6/1/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataConfig.class, AppConfig.class, AspectConfig.class})
@ActiveProfiles("dev")
public class TestUserRepo {

    @Autowired
    UserRepo userRepo;

    @Before
    public void setUp(){
        assertNotNull(userRepo);
    }


    @Test
    public void testOne(){
        Set<User> result =  userRepo.findAllByUserName("John", true);
        assertEquals(1, result.size());
    }

    @Test
    public void testFindById(){
        User user =  userRepo.findById(1L);
        assertEquals("John", user.getUsername());
    }

    @Test
    public void testUpdatePass(){
        userRepo.updatePassword(1L, "new_pass");
        User user =  userRepo.findById(1L);
        assertEquals("new_pass", user.getPassword());
    }


    @Test
    public void testMore(){
        Set<User> result =  userRepo.findAllByUserName("John", false);
        assertEquals(2, result.size());
    }

    @Test
    public void testAll(){
        Set<User> result =  userRepo.findAll();
        assertEquals(3, result.size());
    }
}
