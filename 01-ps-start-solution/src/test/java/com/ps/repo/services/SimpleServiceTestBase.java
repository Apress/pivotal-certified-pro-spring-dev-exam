package com.ps.repo.services;

import com.ps.repo.stub.*;
import com.ps.repos.*;
import com.ps.services.OperationsService;
import com.ps.services.impl.*;

/**
 * Created by iuliana.cosmina on 3/4/16.
 */
public class SimpleServiceTestBase {

    protected SimpleRequestService requestService;
    protected SimpleResponseService responseService;
    protected SimpleUserService userService;
    protected SimplePetService petService;
    protected SimpleReviewService reviewService;
    protected OperationsService operationsService;

    protected void init(){
        UserRepo userRepo = new StubUserRepo();
        RequestRepo requestRepo = new StubRequestRepo();
        ResponseRepo responseRepo = new StubResponseRepo();
        PetRepo petRepo = new StubPetRepo();
        ReviewRepo reviewRepo = new StubReviewRepo();

        userService = new SimpleUserService();
        userService.setRepo(userRepo);

        petService = new SimplePetService();
        petService.setRepo(petRepo);

        requestService = new SimpleRequestService();
        requestService.setRepo(requestRepo);

        responseService = new SimpleResponseService();
        responseService.setRepo(responseRepo);

        reviewService = new SimpleReviewService();
        reviewService.setRepo(reviewRepo);

        operationsService = new SimpleOperationsService();
        operationsService.setUserRepo(userRepo);
        operationsService.setResponseRepo(responseRepo);
        operationsService.setRequestRepo(requestRepo);
        operationsService.setReviewRepo(reviewRepo);
    }
}
