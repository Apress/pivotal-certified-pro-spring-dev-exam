package com.ps.repo;

import com.ps.base.PetType;
import com.ps.base.UserType;
import com.ps.config.AppConfig;
import com.ps.config.TestDataConfig;
import com.ps.ents.Pet;
import com.ps.ents.User;
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

import java.util.List;

import static com.ps.util.RecordBuilder.buildUser;
import static org.junit.Assert.*;

/**
 * Created by iuliana.cosmina on 6/4/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataConfig.class, AppConfig.class})
@ActiveProfiles("dev")
public class TestHibernateUserRepo {

    @Autowired
    UserRepo userRepo;

    @Before
    public void setUp() {
        assertNotNull(userRepo);
        create();
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

    private void create() {
        User john = buildUser("john.cusack@pet.com");
        john.setPassword("test");
        john.setUserType(UserType.OWNER);

        Pet max = new Pet();
        max.setName("Max");
        max.setAge(10);
        max.setPetType(PetType.DOG);
        max.setRfid("1122334455");
        john.addPet(max);

        Pet mona = new Pet();
        mona.setName("Mona");
        mona.setAge(2);
        mona.setPetType(PetType.CAT);
        mona.setRfid("1100223344");
        john.addPet(mona);
        userRepo.save(john);

        User gigi = buildUser("gigi.pedala@pet.com");
        gigi.setPassword("test");
        gigi.setUserType(UserType.SITTER);
        userRepo.save(gigi);
    }
}
