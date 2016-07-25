package com.ps.repo;

import com.ps.base.UserType;
import com.ps.config.AppConfig;
import com.ps.config.TestDataConfig;
import com.ps.ents.User;
import com.ps.init.DBInitializer;
import com.ps.repos.UserRepo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ps.util.RecordBuilder.buildUser;
import static org.junit.Assert.*;

/**
 * Created by iuliana.cosmina on 6/4/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataConfig.class, AppConfig.class})
@ActiveProfiles("dev")
@Transactional
public class TestJpaUserRepo {

    @Autowired
    @Qualifier("userJpaRepo")
    UserRepo userRepo;

    @Autowired
    DBInitializer initializer;

    @Before
    public void setUp() {
        assertNotNull(userRepo);
        initializer.initDb();
    }

    @Test
    public void testFindById() {
        List<User> johns = userRepo.findAllByUserName("john.cusack", true);
        assertTrue(johns.size() == 1);
    }

    @Test
    public void testNoFindById() {
        User user = userRepo.findById(99L);
        assertNull(user);
    }

    @Test
    public void testCreate() {
        User diana = buildUser("diana.ross@pet.com");
        diana.setPassword("test");
        diana.setUserType(UserType.SITTER);
        userRepo.save(diana);
        List<User> dianas = userRepo.findAllByUserName("diana.ross", true);
        assertTrue(dianas.size() == 1);
    }

    @Test
    public void testUpdate() {
        List<User> johns = userRepo.findAllByUserName("john.cusack", true);
        User john = johns.get(0);
        userRepo.updatePassword(john.getId(), "newpass");
    }

    @Test
    public void testDelete() {
        List<User> gigis = userRepo.findAllByUserName("gigi.pedala", true);
        User gigi = gigis.get(0);

        userRepo.deleteById(gigi.getId());
    }

    @After
    public void cleanUp() {
        List<User> users = userRepo.findAll();
        for (User u : users) {
            userRepo.deleteById(u.getId());
        }
    }

}
