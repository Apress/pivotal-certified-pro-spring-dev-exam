package com.ps.repo.stub;

import com.ps.base.PetType;
import com.ps.base.UserType;
import com.ps.ents.Pet;
import com.ps.ents.User;
import com.ps.repos.PetRepo;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.ps.util.TestObjectsBuilder.buildPet;
import static com.ps.util.TestObjectsBuilder.buildUser;

/**
 * Created by iuliana.cosmina on 3/4/16.
 */
@Component
public class StubPetRepo extends StubAbstractRepo<Pet> implements PetRepo {

    protected Map<User, Set<Pet>> records2 = new HashMap<>();

    @PostConstruct
    public void init(){
        // create a few entries to play with
        final User owner = buildUser("test@gmail.com", "a!2#tre", UserType.OWNER);
        this.save(buildPet(owner, PetType.CAT, "John", 3, "0122345645"));
        this.save(buildPet(owner, PetType.DOG, "Max", 5, "0222335645"));
    }

    @Override
    public void save(Pet pet) {
        super.save(pet);
        addWithOwner(pet);
    }

    private void addWithOwner(Pet pet){
        if (pet.getOwner()!= null) {
            User owner = pet.getOwner();
            if (records2.containsKey(owner)) {
                records2.get(owner).add(pet);
            } else {
                Set<Pet> newPetSet = new HashSet<>();
                newPetSet.add(pet);
                records2.put(owner, newPetSet);
            }
        }
    }

    @Override
    public Pet findByOwner(User owner, String name) {
        Set<Pet> petSet = records2.get(owner);
        for (Pet pet: petSet) {
            if (pet.getName().equalsIgnoreCase(name)) {
                return pet;
            }
        }
        return null;
    }

    @Override
    public Set<Pet> findAllByOwner(User owner) {
        Set<Pet> petSet = records2.get(owner);
        // we never return null when returning collections to avoid NullPointerException
        return petSet != null? petSet : new HashSet<>();
    }

    @Override
    public Set<Pet> findAllByType(PetType type) {
        throw new NotImplementedException("Not needed for this stub.");
    }
}
