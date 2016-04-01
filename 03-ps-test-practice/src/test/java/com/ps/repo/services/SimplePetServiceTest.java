package com.ps.repo.services;

import com.ps.base.UserType;
import com.ps.ents.Pet;
import com.ps.ents.User;
import com.ps.repo.stub.StubPetRepo;
import com.ps.repos.NotFoundException;
import com.ps.services.impl.SimplePetService;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static com.ps.util.TestObjectsBuilder.buildUser;
import static org.junit.Assert.*;

/**
 * Created by iuliana.cosmina on 4/15/16.
 */
public class SimplePetServiceTest {
    public static final Long PET_ID = 1L;
    public static final User owner = buildUser("test@gmail.com", "a!2#tre", UserType.OWNER);

    private StubPetRepo stubPetRepo = new StubPetRepo();

    private SimplePetService simplePetService;

    @Before
    public void setUp() {
        stubPetRepo.init();

        // create object to be tested
        simplePetService = new SimplePetService();
        simplePetService.setRepo(stubPetRepo);
    }

    //positive test, we know that a Pet with ID=1 exists
    @Test
    public void findByIdPositive() {
        Pet pet = simplePetService.findById(PET_ID);
        assertNotNull(pet);
    }

    //negative test, we know that a Pet with ID=99 does not exist
    @Test
    public void findByIdNegative() {
        Pet pet = simplePetService.findById(99L);
        assertNull(pet);
    }

    @Test
    public void deleteByIdPositive() {
        simplePetService.deleteById(PET_ID);

        // we do a find to test the deletion succeeded
        Pet pet = simplePetService.findById(PET_ID);
        assertNull(pet);
    }
    @Test
    public void deleteByIdNegative() {
        //TODO 14. Analyse the stub implementation and add a test for  simplePetService.deleteById(99L)
        simplePetService.deleteById(99L);
    }

    //positive test, we know that pets for this owner exist and how many
    @Test
    public void findByOwnerPositive() {
        //TODO 15. Analyse the stub implementation and add a test for simplePetService.findAllByOwner(owner)
    }

    //negative test, we know that pets for this owner do not exist
    @Test
    public void findByOwnerNegative() {
        User newOwner = buildUser("gigi@gmail.com", "1!2#tre", UserType.OWNER);
        Set<Pet> result =  simplePetService.findAllByOwner(newOwner);
        assertEquals(result.size(), 0);
    }
}
