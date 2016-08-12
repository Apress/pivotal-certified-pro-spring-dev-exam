package com.ps.services.impl;

import com.ps.ents.Pet;
import com.ps.ents.User;
import com.ps.repos.PetRepo;
import com.ps.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by iuliana.cosmina on 7/16/16.
 */
@Service
public class PetServiceImpl implements PetService {

    private PetRepo petRepo;

    @Autowired
    public PetServiceImpl(PetRepo petRepo) {
        this.petRepo = petRepo;
    }

    @Override
    @Transactional(propagation = Propagation.NESTED, readOnly = true)
    public String getPetsAsHtml(User owner) {
        Set<Pet> pets = petRepo.findByOwner(owner);
        if(pets.isEmpty()) {
            return "<p>User " + owner.getUsername() + " has no pets.</p>\n";
        }
        StringBuilder htmlSb = new StringBuilder("User " + owner.getUsername() + " has:\n");
        for (Pet pet : pets) {
            htmlSb.append("<p>Name: " + pet.getName() + ", type: " + pet.getPetType() + ", Age: " + pet.getAge() +"</p></br>\n");
        }
        return htmlSb.toString();
    }
}
