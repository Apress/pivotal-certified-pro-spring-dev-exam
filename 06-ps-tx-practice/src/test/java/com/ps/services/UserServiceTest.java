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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 7/15/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataConfig.class, AppConfig.class})
@ActiveProfiles("dev")
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Before

    public void setUp() {
        assertNotNull(userService);
    }

    @BeforeTransaction
    public void checkDbInit() {
        int count = userService.countUsers();
        assertEquals(4, count);
    }

    @Test
    public void testFindById() {
        User user = userService.findById(1L);
        assertNotNull(user);
    }

    @Test
    public void testHtml() {
        userService.htmlAllByNameAll("John");
    }

    @Test
    @SqlGroup({
            @Sql(value = "classpath:db/extra-data.sql", config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--")),
            @Sql(
                    scripts = "classpath:db/delete-test-data.sql",
                    config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED),
                    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
            )
    })
    public void testCount() {
        int count = userService.countUsers();
        assertEquals(8, count);
    }

    @Test
    public void updatePassword() {
        //TODO 35. Complete definition of this test in order for it to pass
        int res = 0; //userService.updatePassword(2L, "test_pass");
        assertEquals(1, res);
    }
}
