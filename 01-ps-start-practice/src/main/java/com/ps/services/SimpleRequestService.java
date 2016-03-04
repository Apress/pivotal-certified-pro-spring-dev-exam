package com.ps.services;

import com.ps.base.RequestStatus;
import com.ps.base.ResponseStatus;
import com.ps.ents.*;
import com.ps.repos.RequestRepo;
import com.ps.repos.UserRepo;
import com.ps.util.Pair;
import org.joda.time.DateTime;

/**
 * Created by iuliana.cosmina on 2/22/16.
 */
public class SimpleRequestService implements RequestService {

    private RequestRepo requestRepo;
    private UserRepo userRepo;

    public Request createRequest(User user, Pet pet, Pair<DateTime, DateTime> interval, String details) {
        Request request = new Request();
        request.setUser(user);
        request.addPet(pet);
        request.setStartAt(interval.x().toDate());
        request.setEndAt(interval.y().toDate());
        request.setDetails(details);
        request.setRequestStatus(RequestStatus.NEW);
        requestRepo.save(request);
        return request;
    }

    @Override
    public User closeRequest(Request request, Review review) {
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
    public User rateOwner(Request request, Review review) {
        User owner = request.getUser();
        review.setRequest(request);
        // compute new rating for user
        double newRating = (owner.getRating() + review.getGrade().getGrade()) / 2;
        owner.setRating(newRating);
        userRepo.save(owner);
        return owner;
    }

    @Override
    public Request findById(Long requestId) {
        return requestRepo.findById(requestId);
    }

    @Override
    public Request save(Request request) {
        requestRepo.save(request);
        return request;
    }

    //                setters
    public void setRequestRepo(RequestRepo requestRepo) {
        this.requestRepo = requestRepo;
    }

    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
}
