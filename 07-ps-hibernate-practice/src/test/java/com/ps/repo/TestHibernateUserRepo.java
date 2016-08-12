package com.ps.repo;

import com.ps.base.UserType;
import com.ps.config.AppConfig;
import com.ps.config.TestDataConfig;
import com.ps.ents.User;
import com.ps.repos.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by iuliana.cosmina on 6/4/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "")
@ActiveProfiles("dev")
public class TestHibernateUserRepo {

    @Autowired
    @Qualifier("userTemplateRepo")
    UserRepo userRepo;

    @Before
    public void setUp() {
        assertNotNull(userRepo);
    }

    @Test
    public void testFindById() {
        User user = userRepo.findById(1L);
        assertEquals("John", user.getUsername());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testNoFindById() {
        User user = userRepo.findById(99L);
        assertEquals("Darius", user.getUsername());
    }

    @Test
    public void testCreate() {
        /*int result = userRepo.createUser(5L, "Diana", "mypass", "diana@opympus.com", UserType.BOTH);
        assertEquals(1, result);
        Set<User> dianas = userRepo.findAllByUserName("Diana", true);
        assertTrue(dianas.size() == 1);*/
    }

    @Test
    public void testUpdate() {
        userRepo.updatePassword(1L, "newpass");

        //TODO
    }

    @Test
    public void testDelete() {
        userRepo.deleteById(4L);
        //TODO
    }
}
