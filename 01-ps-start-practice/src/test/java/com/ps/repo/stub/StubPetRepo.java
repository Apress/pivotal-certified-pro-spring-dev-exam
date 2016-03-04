package com.ps.repo.stub;

import com.ps.ents.Pet;
import com.ps.repos.PetRepo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by iuliana.cosmina on 3/4/16.
 */
public class StubPetRepo implements PetRepo {
    private Map<Long, Pet> petHolder = new HashMap<>();

    @Override
    public void save(Pet pet) {
        if (pet.getId() != null) {
            petHolder.put(pet.getId(), pet);
        }
    }

    @Override
    public void delete(Pet pet) {
        petHolder.remove(pet.getId());
    }

    @Override
    public void deleteById(Long petId) {
        petHolder.remove(petId);
    }


    @Override
    public Pet findById(Long petId) {
        return petHolder.get(petId);
    }
}
