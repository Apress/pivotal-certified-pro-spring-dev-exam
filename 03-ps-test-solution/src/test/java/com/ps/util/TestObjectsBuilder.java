package com.ps.util;

import com.ps.base.PetType;
import com.ps.base.UserType;
import com.ps.ents.Pet;
import com.ps.ents.User;

/**
 * Created by iuliana.cosmina on 4/17/16.
 */
public class TestObjectsBuilder {
    public static User buildUser(String email, String password, UserType userType){
        User user = RecordBuilder.buildUser(email);
        user.setPassword(password);
        user.setUserType(userType);
        return user;
    }

    public static Pet buildPet(User owner, PetType petType, String name, Integer age, String rfid) {
        Pet pet = new Pet();
        pet.setPetType(petType);
        pet.setName(name);
        pet.setAge(age);
        pet.setRfid(rfid);
        pet.setOwner(owner);
        return pet;
    }
}
