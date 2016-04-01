package com.ps.repo.stub;

import com.ps.base.PetType;
import com.ps.ents.Pet;
import com.ps.ents.User;
import com.ps.repos.PetRepo;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Set;

/**
 * Created by iuliana.cosmina on 3/4/16.
 */
public class StubPetRepo extends StubAbstractRepo<Pet> implements PetRepo {

    @Override
    public Pet findByOwner(User owner, String name) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public Set<Pet> findAllByOwner(User owner) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public Set<Pet> findAllByType(PetType type) {
        throw new NotImplementedException("Not needed for this stub.");
    }
}
