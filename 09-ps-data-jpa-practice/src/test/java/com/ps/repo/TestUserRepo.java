package com.ps.repo;

import com.ps.base.UserType;
import com.ps.config.AppConfig;
import com.ps.config.db.DataSourceConfig;
import com.ps.config.PersistenceConfig;
import com.ps.config.db.TestDataConfig;
import com.ps.ents.User;
import com.ps.repos.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.ps.util.RecordBuilder.buildUser;
import static org.junit.Assert.*;

/**
 * Created by iuliana.cosmina on 6/4/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PersistenceConfig.class, AppConfig.class})
@ActiveProfiles("dev")
public class TestUserRepo {

    @Autowired
    UserRepo userRepo;

    @Before
    public void setUp() {
        assertNotNull(userRepo);
    }

    @Test
    public void testFindById() {
        List<User> johns = userRepo.findAllByUserName("john");
        assertTrue(johns.size() == 2);
    }

    @Test//(expected = EntityNotFoundException.class)
    public void testNoFindById() {
        User user = userRepo.getOne(99L);
        assertNull(user);
    }

    @Test
    public void testCreate() {
        User diana = buildUser("diana.ross@pet.com");
        diana.setPassword("test");
        diana.setUserType(UserType.SITTER);
        diana = userRepo.save(diana);
        assertNotNull(diana.getId());
    }

    @Test
    public void testUpdate() {
        User john = userRepo.findOneByUsername("john.cusack");
        john.setPassword("newpass");
        userRepo.saveAndFlush(john);
        assertEquals("newpass", john.getPassword());
        //check the version field
        System.out.println(john);
    }

    @Test
    public void testDelete() {
        User gigi =  userRepo.findOneByUsername("gigi.pedala");
        userRepo.delete(gigi.getId());
    }
}
