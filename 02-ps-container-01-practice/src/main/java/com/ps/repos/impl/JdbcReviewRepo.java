package com.ps.repos.impl;

import com.ps.ents.Review;

import javax.sql.DataSource;

/**
 * Created by iuliana.cosmina on 3/21/16.
 */
public class JdbcReviewRepo extends JdbcAbstractRepo<Review> {
    public JdbcReviewRepo(DataSource dataSource) {
        super(dataSource);
    }
}
