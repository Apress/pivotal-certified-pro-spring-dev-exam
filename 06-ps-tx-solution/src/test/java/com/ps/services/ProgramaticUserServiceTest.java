package com.ps.services;

import com.ps.config.AppConfig;
import com.ps.config.TestDataConfig;
import com.ps.ents.User;
import com.ps.exceptions.MailSendingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.UnexpectedRollbackException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 7/17/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataConfig.class, AppConfig.class})
@ActiveProfiles("dev")
public class ProgramaticUserServiceTest {

    @Autowired
    @Qualifier("programaticUserService")
    UserService userService;

    @Before

    public void setUp() {
        assertNotNull(userService);
    }


    @Test
    public void testFindById() {
        User user = userService.findById(1L);
        assertNotNull(user);
    }

    @Test(expected = UnexpectedRollbackException.class)
    public void testNotFindById() {
        User user = userService.findById(99L);
        assertNotNull(user);
    }

    @Test
    public void updatePassword() throws MailSendingException {
        int res = userService.updatePassword(2L, "test_pass");
        assertEquals(0, res);
    }

}
