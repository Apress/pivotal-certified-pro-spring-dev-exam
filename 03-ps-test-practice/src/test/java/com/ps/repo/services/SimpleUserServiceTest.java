package com.ps.repo.services;

import com.ps.base.UserType;
import com.ps.ents.User;
import com.ps.repos.UserRepo;
import com.ps.services.impl.SimpleUserService;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;
import static com.ps.util.TestObjectsBuilder.*;

/**
 * Created by iuliana.cosmina on 4/17/16.
 */
public class SimpleUserServiceTest {

    public static final Long USER_ID = 1L;

    private UserRepo userMockRepo;
    private SimpleUserService simpleUserService;

    @Before
    public void setUp() {
        //prepare mock
        userMockRepo = createMock(UserRepo.class);

        //create object to be tested
        simpleUserService = new SimpleUserService();

        // inject the mock as dependency
        simpleUserService.setRepo(userMockRepo);
    }

    @Test
    public void findByIdPositive() {
        //record what we want the mock to do
        User simpleUser = buildUser("gigi@gmail.com", "1!2#tre", UserType.OWNER);
        simpleUser.setId(USER_ID);
        expect(userMockRepo.findById(USER_ID)).andReturn(simpleUser);
        replay(userMockRepo);

        User user = simpleUserService.findById(USER_ID);
        verify(userMockRepo);
        assertNotNull(user);
        assertEquals(user.getId(), simpleUser.getId());
    }

    @Test
    public void findByIdNegative() {
        //record what we want the mock to do
        expect(userMockRepo.findById(USER_ID)).andReturn(null);
        replay(userMockRepo);

        User user = simpleUserService.findById(USER_ID);
        verify(userMockRepo);
        assertNull(user);
    }

    @Test
    public void findByNamePositive() {
        //record what we want the mock to do
        User simpleUser = buildUser("gigi@gmail.com", "1!2#tre", UserType.OWNER);
        Set<User> userSet = new HashSet<>();
        userSet.add(simpleUser);

        expect(userMockRepo.findAllByUserName("gigi", false)).andReturn(userSet);
        replay(userMockRepo);

        Set<User> result = simpleUserService.findByName("gigi", false);
        verify(userMockRepo);
        assertEquals(result.size(), 1);
    }

    @Test
    public void findByNameNegative() {
        //record what we want the mock to do
        expect(userMockRepo.findAllByUserName("gigi", false)).andReturn(new HashSet<>());
        replay(userMockRepo);

        Set<User> result = simpleUserService.findByName("gigi", false);
        verify(userMockRepo);
        assertEquals(result.size(), 0);
    }

}
