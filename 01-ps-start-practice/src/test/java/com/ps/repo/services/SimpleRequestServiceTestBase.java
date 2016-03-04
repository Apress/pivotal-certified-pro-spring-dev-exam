package com.ps.repo.services;

import com.ps.repo.stub.*;
import com.ps.repos.*;
import com.ps.services.*;

/**
 * Created by iuliana.cosmina on 3/4/16.
 */
public class SimpleRequestServiceTestBase {

    protected SimpleRequestService requestService;
    protected SimpleResponseService responseService;
    protected SimpleUserService userService;
    protected SimplePetService petService;
    protected SimpleReviewService reviewService;

    protected void init(){
        UserRepo userRepo = new StubUserRepo();
        RequestRepo requestRepo = new StubRequestRepo();
        ResponseRepo responseRepo = new StubResponseRepo();
        PetRepo petRepo = new StubPetRepo();
        ReviewRepo reviewRepo = new StubReviewRepo();

        userService = new SimpleUserService();
        userService.setUserRepo(userRepo);

        petService = new SimplePetService();
        petService.setPetRepo(petRepo);

        requestService = new SimpleRequestService();
        requestService.setUserRepo(userRepo);
        requestService.setRequestRepo(requestRepo);

        responseService = new SimpleResponseService();
        responseService.setResponseRepo(responseRepo);

        reviewService = new SimpleReviewService();
        reviewService.setReviewRepo(reviewRepo);
    }
}
