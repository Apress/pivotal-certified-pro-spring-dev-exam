package com.ps.repos.impl;

import com.ps.ents.Pet;

import javax.sql.DataSource;

/**
 * Created by iuliana.cosmina on 3/21/16.
 */
public class JdbcPetRepo  extends  JdbcAbstractRepo<Pet> {
    public JdbcPetRepo(DataSource dataSource) {
        super(dataSource);
    }
}
