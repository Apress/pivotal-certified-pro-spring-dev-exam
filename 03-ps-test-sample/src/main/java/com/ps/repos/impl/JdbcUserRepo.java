package com.ps.repos.impl;

import com.ps.ents.User;
import com.ps.repos.UserRepo;

import javax.sql.DataSource;
import java.util.Set;

/**
 * Created by iuliana.cosmina on 3/21/16.
 */
public class JdbcUserRepo extends JdbcAbstractRepo<User> implements UserRepo {

    public JdbcUserRepo(){
    }

    public JdbcUserRepo(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Set<User> findAllByUserName(String username, boolean exactMatch) {
        return null;
    }

    @Override
    public Set<User> findByRating(double startRating, double endRating) {
        return null;
    }
}
