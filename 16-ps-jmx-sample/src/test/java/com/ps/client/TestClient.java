package com.ps.client;

/**
 * Created by iuliana.cosmina on 10/19/16.
 */


import com.ps.start.JmxCounter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class TestClient {
    private static Logger logger = LoggerFactory.getLogger(TestClient.class);


    @Autowired
    JmxCounter counter;

    @Test
    public void test() {
        assertNotNull(counter);
        counter.add();
        assertEquals(1, counter.getCount());
    }

}
