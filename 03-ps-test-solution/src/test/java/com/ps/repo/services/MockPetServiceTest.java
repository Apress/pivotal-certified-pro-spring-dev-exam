package com.ps.repo.services;

import com.ps.base.UserType;
import com.ps.ents.Pet;
import com.ps.ents.User;
import com.ps.repos.PetRepo;
import com.ps.services.impl.SimplePetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static com.ps.util.TestObjectsBuilder.buildUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Created by iuliana.cosmina on 4/17/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class MockPetServiceTest {

    public static final Long PET_ID = 1L;
    public static final User owner = buildUser("test@gmail.com", "a!2#tre", UserType.OWNER);

    @InjectMocks     // Instantiates testing object instance and tries to inject fields annotated with @Mock or @Spy into private
            // fields of testing object
    SimplePetService simplePetService;

    @Mock //Creates mock instance of the field it annotates
    PetRepo petRepo;

    @Before
    public void initMocks() {
       // MockitoAnnotations.initMocks(this);
    }

    //positive test, we know that a Pet with ID=1 exists
    @Test
    public void findByIdPositive() {
        //Mockito.when(petRepo.findById(PET_ID)).thenReturn(new Pet());
        Mockito.when(petRepo.findById(anyLong())).thenReturn(new Pet());
        Pet pet = simplePetService.findById(PET_ID);
        assertNotNull(pet);
    }

    //positive test, we know that pets for this owner exist and how many
    @Test
    public void findByOwnerPositive() {
        Set<Pet> sample = new HashSet<>();
        sample.add(new Pet());
        //Mockito.when(petRepo.findAllByOwner(owner)).thenReturn(sample);
        Mockito.when(petRepo.findAllByOwner(any(User.class))).thenReturn(sample);
        Set<Pet> result = simplePetService.findAllByOwner(owner);
        assertEquals(result.size(), 1);
    }
}
