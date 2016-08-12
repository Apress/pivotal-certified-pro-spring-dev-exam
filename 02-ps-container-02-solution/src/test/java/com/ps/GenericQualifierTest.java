package com.ps;

import com.ps.config.AllRepoConfig;
import com.ps.config.DataSourceConfig;
import com.ps.ents.Response;
import com.ps.ents.Review;
import com.ps.repos.impl.JdbcAbstractRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 3/31/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AllRepoConfig.class})
public class GenericQualifierTest {


    @Autowired
    JdbcAbstractRepo<Review> reviewRepo;

    @Autowired
    JdbcAbstractRepo<Response> responseRepo;

    @Test
    public void testReviewRepo() {
        assertNotNull(reviewRepo);
    }

    @Test
    public void testResponseRepo() {
        assertNotNull(reviewRepo);
    }
}
