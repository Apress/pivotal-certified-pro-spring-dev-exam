package com.ps.repos.impl;

import com.ps.ents.Response;

import javax.sql.DataSource;

/**
 * Created by iuliana.cosmina on 3/21/16.
 */
public class JdbcResponseRepo extends JdbcAbstractRepo<Response> {
    public JdbcResponseRepo(DataSource dataSource) {
        super(dataSource);
    }
}
