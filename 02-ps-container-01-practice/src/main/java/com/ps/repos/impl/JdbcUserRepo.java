package com.ps.repos.impl;

import com.ps.ents.User;

import javax.sql.DataSource;

/**
 * Created by iuliana.cosmina on 3/21/16.
 */
public class JdbcUserRepo extends JdbcAbstractRepo<User> {

    public JdbcUserRepo(DataSource dataSource) {
        super(dataSource);
    }
}
