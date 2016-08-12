package com.ps.repos.impl;

import com.ps.ents.Request;
import com.ps.ents.Response;
import com.ps.ents.Review;
import com.ps.ents.User;
import com.ps.repos.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Set;

/**
 * Created by iuliana.cosmina on 3/21/16.
 */
@Repository("reviewRepo")
public class JdbcReviewRepo extends JdbcAbstractRepo<Review> implements ReviewRepo {

    @Autowired
    public JdbcReviewRepo(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Set<Review> findAllForUser(User user) {
        return null;
    }

    @Override
    public Set<Review> findAllForRequest(Request request) {
        return null;
    }

    @Override
    public Set<Review> findAllForResponse(Response response) {
        return null;
    }
}
