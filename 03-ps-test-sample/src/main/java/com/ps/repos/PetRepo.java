package com.ps.repos;

import com.ps.base.PetType;
import com.ps.ents.Pet;
import com.ps.ents.User;

import java.util.Set;

/**
 * Created by iuliana.cosmina on 3/4/16.
 */
public interface PetRepo extends  AbstractRepo<Pet> {

    Pet findByOwner(User owner, String name);

    Set<Pet> findAllByOwner(User owner);

    Set<Pet> findAllByType(PetType type);
}
