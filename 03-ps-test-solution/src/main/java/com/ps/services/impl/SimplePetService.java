package com.ps.services.impl;

import com.ps.base.PetType;
import com.ps.ents.Pet;
import com.ps.ents.User;
import com.ps.repos.PetRepo;
import com.ps.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by iuliana.cosmina on 2/23/16.
 */
@Component
public class SimplePetService extends SimpleAbstractService<Pet> implements PetService {

    private PetRepo repo;

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

    @Override
    public Set<Pet> findAllByOwner(User user) {
        return repo.findAllByOwner(user);
    }

    /**
     * @param user owner of the pet
     * @param name name of the pet
     * @return
     */
    @Override
    public Pet findByOwner(User user, String name) {
        return repo.findByOwner(user, name);
    }

    //                setters & getters
    @Autowired
    public void setRepo(PetRepo petRepo) {
        this.repo = petRepo;
    }

    public PetRepo getRepo() {
        return repo;
    }
}
