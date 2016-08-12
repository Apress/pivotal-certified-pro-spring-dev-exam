package com.ps.repos.impl;

import com.ps.base.PetType;
import com.ps.ents.Pet;
import com.ps.ents.User;
import com.ps.repos.PetRepo;

import javax.sql.DataSource;
import java.util.Set;

/**
 * Created by iuliana.cosmina on 3/21/16.
 */
public class JdbcPetRepo  extends  JdbcAbstractRepo<Pet> implements PetRepo {

    public JdbcPetRepo(){
    }

    public JdbcPetRepo(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Pet findByOwner(User owner, String name) {
        return null;
    }

    @Override
    public Set<Pet> findAllByOwner(User owner) {
        return null;
    }

    @Override
    public Set<Pet> finAllByType(PetType type) {
        return null;
    }
}
