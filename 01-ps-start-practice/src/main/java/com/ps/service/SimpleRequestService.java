package com.ps.service;

import com.ps.base.RequestStatus;
import com.ps.base.ResponseStatus;
import com.ps.ents.*;
import com.ps.util.Pair;
import org.joda.time.DateTime;

/**
 * Created by iuliana.cosmina on 2/22/16.
 */
public class SimpleRequestService implements RequestService {

    public Request createRequest(User user, Pet pet, Pair<DateTime, DateTime> interval, String details) {
        Request request = new Request();
        request.setUser(user);
        request.addPet(pet);
        request.setStartAt(interval.x().toDate());
        request.setEndAt(interval.y().toDate());
        request.setDetails(details);
        request.setRequestStatus(RequestStatus.NEW);
        return request;
    }

    @Override
    public User closeRequest(Request request, Review review) {
        request.setRequestStatus(RequestStatus.SOLVED);
        for (Response r : request.getResponses()) {
            if (r.getResponseStatus() == ResponseStatus.ACCEPTED) {
                review.setResponse(r);
                User sitter = r.getUser();
                // compute new rating for user
                double newRating = (sitter.getRating() + review.getGrade().getGrade()) / 2;
                sitter.setRating(newRating);
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
        return owner;
    }
}
