package com.ps.repo;

import com.ps.config.AppConfig;
import com.ps.ents.User;
import com.ps.init.DBInitializer;
import com.ps.repos.UserRepo;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by iuliana.cosmina on 6/4/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class})
public class TestUserRepo {

    private Logger logger = LoggerFactory.getLogger(TestUserRepo.class);

    @Autowired
    UserRepo userRepo;

    @Autowired
    DBInitializer initializer;

    @Before
    public void setUp() {
        assertNotNull(userRepo);
        userRepo.deleteAll();
        initializer.initDb();
    }

    @Test
    public void testFindById() {
        List<User> johns = userRepo.findAllByUserName("john");
        assertTrue(johns.size() == 2);
        logger.info(johns.toString());
    }

    @Test
    public void testFindAll() {
        List<User> users = userRepo.findAll();
        assertTrue(users.size() == 5);
    }

    @Test
    public void testNoFindById() {
        User user = userRepo.findOne(99L);
        assertNull(user);
    }

    @Test
    public void testCreate() {
        User diana = DBInitializer.buildUser("diana.ross@pet.com");
        diana.setPassword("test");
        diana = userRepo.save(diana);
        assertNotNull(diana.getId());
    }

    @Test
    public void testUpdate() {
        List<User> johns = userRepo.findAllByUserName("john.cusack");
        User john = johns.get(0);
        john.setPassword("newpass");
        userRepo.save(john);
        assertEquals("newpass", john.getPassword());
    }

    @Test
    public void testDelete() {
        List<User> gigis = userRepo.findAllByUserName("gigi.pedala");
        User gigi =  gigis.get(0);
        userRepo.delete(gigi.getId().longValue());
    }
}
