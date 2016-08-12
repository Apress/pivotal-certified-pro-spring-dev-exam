package com.ps.services.impl;

import com.ps.base.RequestStatus;
import com.ps.base.ResponseStatus;
import com.ps.ents.Request;
import com.ps.ents.Response;
import com.ps.ents.Review;
import com.ps.ents.User;
import com.ps.repos.RequestRepo;
import com.ps.repos.ResponseRepo;
import com.ps.repos.ReviewRepo;
import com.ps.repos.UserRepo;
import com.ps.services.OperationsService;

/**
 * Created by iuliana.cosmina on 3/7/16.
 */
public class SimpleOperationsService implements OperationsService {

    private RequestRepo requestRepo;
    private UserRepo userRepo;
    private ResponseRepo responseRepo;
    private ReviewRepo reviewRepo;

    @Override
    public Response createResponse(Long sitterId, Long requestId) {
        // get sitter
        User sitter = userRepo.findById(sitterId);
        Request request = requestRepo.findById(requestId);

        //create a response
        Response response = new Response();
        response.setUser(sitter);
        response.setResponseStatus(ResponseStatus.PROPOSED);
        request.addResponse(response);
        response.setDetails("Max is a very active dog. Take him out for a run twice a day.");
        responseRepo.save(response);
        return response;
    }


    @Override
    public void acceptResponse(Long requestId, Long responseId) {
        Request request = requestRepo.findById(requestId);
        Response response = responseRepo.findById(responseId);

        request.getResponses().forEach(r -> {
            if (r.equals(response)) {
                r.setResponseStatus(ResponseStatus.ACCEPTED);
            } else {
                r.setResponseStatus(ResponseStatus.REJECTED);
            }
            responseRepo.save(r);
        });
    }

    @Override
    public User closeRequest(Long requestId, Long reviewId) {
        Request request = requestRepo.findById(requestId);
        Review review = reviewRepo.findById(reviewId);
        request.setRequestStatus(RequestStatus.SOLVED);
        requestRepo.save(request);
        for (Response r : request.getResponses()) {
            if (r.getResponseStatus() == ResponseStatus.ACCEPTED) {
                review.setResponse(r);
                User sitter = r.getUser();
                // compute new rating for user
                double newRating = (sitter.getRating() + review.getGrade().getGrade()) / 2;
                sitter.setRating(newRating);
                userRepo.save(sitter);
                return sitter;
            }
        }
        return null;
    }

    @Override
    public User rateOwner(Long requestId, Long reviewId) {
        Request request = requestRepo.findById(requestId);
        Review review = reviewRepo.findById(reviewId);
        User owner = request.getUser();
        review.setRequest(request);
        // compute new rating for user
        double newRating = (owner.getRating() + review.getGrade().getGrade()) / 2;
        owner.setRating(newRating);
        userRepo.save(owner);
        return owner;
    }

    //                setters & getters
    public void setRequestRepo(RequestRepo requestRepo) {
        this.requestRepo = requestRepo;
    }

    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void setResponseRepo(ResponseRepo responseRepo) {
        this.responseRepo = responseRepo;
    }

    public void setReviewRepo(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }
}
