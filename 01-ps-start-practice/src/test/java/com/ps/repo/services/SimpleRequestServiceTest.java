package com.ps.repo.services;

import com.ps.base.*;
import com.ps.ents.*;
import com.ps.repo.stub.StubPetRepo;
import com.ps.repo.stub.StubRequestRepo;
import com.ps.repo.stub.StubResponseRepo;
import com.ps.repo.stub.StubUserRepo;
import com.ps.repos.PetRepo;
import com.ps.repos.RequestRepo;
import com.ps.repos.ResponseRepo;
import com.ps.repos.UserRepo;
import com.ps.services.SimplePetService;
import com.ps.services.SimpleRequestService;
import com.ps.services.SimpleResponseService;
import com.ps.services.SimpleUserService;
import com.ps.util.Pair;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by iuliana.cosmina on 3/1/16.
 */
public class SimpleRequestServiceTest extends  SimpleRequestServiceTestBase {

    public static final Long OWNER_ID=1L;
    public static final Long SITTER_ID=2L;

    public static final Long REQUEST_ID=1L;

    @Before
    public void setup() throws  Exception {
       init();
        //create an owner
        User owner = userService.createUser("iuliana.cosmina@gmail.com", "test", UserType.OWNER);
        owner.setId(OWNER_ID);
        userService.save(owner);
        assertNotNull(owner);

        //create an sitter
        User sitter = userService.createUser("vlad.bertrand@gmail.com", "test", UserType.SITTER);
        sitter.setId(SITTER_ID);
        userService.save(sitter);
        assertNotNull(sitter);

        //create pet
        Pet pet = petService.createPet(owner, "Max", 5, PetType.DOG, "1002344490");
        pet.setId(1L);
        petService.save(pet);
        assertNotNull(pet);

        //create a request
        Request request = requestService
                .createRequest(owner, pet,
                        Pair.of(DateTime.parse("2016-03-05"),DateTime.parse("2016-03-06")), "");
        request.setId(REQUEST_ID);
        requestService.save(request);
        assertNotNull(request);
        assertTrue(request.getStartAt().before(request.getEndAt()));
    }

    @Test
    public void testCreateResponse(){
        // get sitter
        User sitter = userService.findById(SITTER_ID);
        Request request = requestService.findById(REQUEST_ID);

        //create a response
        Response response = responseService
                .createResponse(sitter,request,"Max is a very active dog");
        response.setId(1L);
        responseService.save(response);
        
        // accept response
        responseService.acceptResponse(request,response);
        assertTrue(response.getResponseStatus() == ResponseStatus.ACCEPTED);

        //close request
        Review review = reviewService.createReview(ReviewGrade.GOOD,
                "Max was well taken care of.");
        requestService.closeRequest(request, review);
        assertTrue(request.getRequestStatus() == RequestStatus.SOLVED);
    }

}
