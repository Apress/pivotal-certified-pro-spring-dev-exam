package com.ps.services;

import com.ps.base.PetType;
import com.ps.base.UserType;
import com.ps.ents.Pet;
import com.ps.ents.User;
import com.ps.repos.PetRepo;
import com.ps.repos.UserRepo;
import com.ps.util.RecordBuilder;

/**
 * Created by iuliana.cosmina on 2/23/16.
 */
public class SimplePetService implements PetService {

    private PetRepo petRepo;

    @Override
    public Pet createPet(User user, String name, int age, PetType petType, String rfid) {
        Pet pet = new Pet();
        pet.setOwner(user);
        pet.setName(name);
        pet.setAge(age);
        pet.setRfid(rfid);
        user.addPet(pet);
        petRepo.save(pet);
        return pet;
    }

    @Override
    public Pet save(Pet pet) {
        petRepo.save(pet);
        return pet;
    }

    //                setters
    public void setPetRepo(PetRepo petRepo) {
        this.petRepo = petRepo;
    }
}
