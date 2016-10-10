package com.ps.jms.client;

import com.ps.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by iuliana.cosmina on 10/9/16.
 */
@ContextConfiguration(locations = {/*"classpath:spring/client-config.xml",
        "classpath:spring/jms-user-config.xml",
        "classpath:spring/jms-infrastructure-config.xml",*/
        "classpath:spring/app-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserBatchProcessorTest {

    @Autowired
    @Qualifier("userServiceImpl")
    UserService userService;



    @Test
    public void test1() {
        Assert.assertNotNull(userService);
    }

}
