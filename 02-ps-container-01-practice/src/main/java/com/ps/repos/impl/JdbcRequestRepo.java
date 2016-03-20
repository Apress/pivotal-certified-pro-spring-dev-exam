package com.ps.repos.impl;

import com.ps.ents.Request;

import javax.sql.DataSource;

/**
 * Created by iuliana.cosmina on 3/21/16.
 */
public class JdbcRequestRepo  extends JdbcAbstractRepo<Request> {
    public JdbcRequestRepo(DataSource dataSource) {
        super(dataSource);
    }
}
