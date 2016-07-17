package com.ps.repos;

import com.ps.ents.Pet;
import com.ps.ents.User;

import java.util.Set;

/**
 * Created by iuliana.cosmina on 7/16/16.
 */
public interface PetRepo {

    Set<Pet> findByOwner(User owner);
}
