package com.ps.services.impl;

import com.ps.base.RequestStatus;
import com.ps.ents.Pet;
import com.ps.ents.Request;
import com.ps.ents.User;
import com.ps.repos.RequestRepo;
import com.ps.services.RequestService;
import com.ps.util.Pair;
import org.joda.time.DateTime;

import java.util.Set;

/**
 * Created by iuliana.cosmina on 2/22/16.
 */
public class SimpleRequestService extends SimpleAbstractService<Request> implements RequestService {

    private RequestRepo repo;

    public Request createRequest(User user, Pet pet, Pair<DateTime, DateTime> interval, String details) {
        Request request = new Request();
        request.setUser(user);
        request.addPet(pet);
        request.setStartAt(interval.x().toDate());
        request.setEndAt(interval.y().toDate());
        request.setDetails(details);
        request.setRequestStatus(RequestStatus.NEW);
        repo.save(request);
        return request;
    }

    @Override
    public Set<Request> findAllByUser(User user) {
        return repo.findAllForUser(user);
    }

    //                setters & getters
    public void setRepo(RequestRepo requestRepo) {
        this.repo = requestRepo;
    }

    @Override
    public RequestRepo getRepo() {
        return repo;
    }
}
