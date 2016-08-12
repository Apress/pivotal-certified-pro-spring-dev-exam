package com.ps.services.impl;

import com.ps.base.PetType;
import com.ps.ents.Pet;
import com.ps.ents.User;
import com.ps.repos.PetRepo;
import com.ps.services.PetService;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by iuliana.cosmina on 2/23/16.
 */
public class SimplePetService extends SimpleAbstractService<Pet> implements PetService {


    private PetRepo repo;

    public SimplePetService(PetRepo petRepo) {
        this.repo = petRepo;
    }

    @Override
    public Pet createPet(User user, String name, int age, PetType petType, String rfid) {
        Pet pet = new Pet();
        pet.setOwner(user);
        pet.setName(name);
        pet.setAge(age);
        pet.setRfid(rfid);
        user.addPet(pet);
        repo.save(pet);
        return pet;
    }

    //                setters & getters
    public PetRepo getRepo() {
        return repo;
    }
}
