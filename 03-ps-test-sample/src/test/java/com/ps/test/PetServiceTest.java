package com.ps.test;

import com.ps.base.UserType;
import com.ps.config.PetConfigClass;
import com.ps.ents.Pet;
import com.ps.ents.User;
import com.ps.services.PetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 5/6/16.
 */
//Iuliana - complete this implementation at some point
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {PetConfigClass.class})
@ActiveProfiles("dev")
public class PetServiceTest {

    public static final Long PET_ID = 1L;

    @Autowired
    PetService simplePetService;

    //positive test, we know that a Pet with ID=1 exists
    @Test
    public void findByIdPositive() {
        Pet pet = simplePetService.findById(PET_ID);
        assertNotNull(pet);
    }

}
