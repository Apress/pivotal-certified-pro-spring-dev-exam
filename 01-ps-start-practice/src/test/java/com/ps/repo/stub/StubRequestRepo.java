package com.ps.repo.stub;

import com.ps.ents.Request;
import com.ps.repos.RequestRepo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by iuliana.cosmina on 2/22/16.
 */
public class StubRequestRepo implements RequestRepo {

    private Map<Long, Request> requestHolder = new HashMap<>();

    @Override
    public void save(Request request) {
        if (request.getId() != null) {
            requestHolder.put(request.getId(), request);
        }
    }

    @Override
    public void delete(Request request) {
        requestHolder.remove(request.getId());
    }

    @Override
    public void deleteById(Long requestId) {
        requestHolder.remove(requestId);
    }


    @Override
    public Request findById(Long requestId) {
        return requestHolder.get(requestId);
    }

}
