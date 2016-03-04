package com.ps.services;

import com.ps.base.ResponseStatus;
import com.ps.ents.Request;
import com.ps.ents.Response;
import com.ps.ents.User;
import com.ps.repos.RequestRepo;
import com.ps.repos.ResponseRepo;
import com.ps.repos.UserRepo;

/**
 * Created by iuliana.cosmina on 2/22/16.
 */
public class SimpleResponseService implements ResponseService {

    private ResponseRepo responseRepo;

    public Response createResponse(User user, Request request, String details) {
        Response response = new Response();
        response.setUser(user);
        response.setRequest(request);
        response.setDetails(details);
        response.setResponseStatus(ResponseStatus.PROPOSED);
        request.addResponse(response);
        responseRepo.save(response);
        return response;
    }

    @Override
    public void acceptResponse(Request request, Response response) {
        for (Response r: request.getResponses()) {
            if(r.equals(response)) {
                r.setResponseStatus(ResponseStatus.ACCEPTED);
            } else {
                r.setResponseStatus(ResponseStatus.REJECTED);
            }
            responseRepo.save(r);
        }
    }

    @Override
    public Response save(Response response) {
        responseRepo.save(response);
        return response;
    }

    //                setters
    public void setResponseRepo(ResponseRepo responseRepo) {
        this.responseRepo = responseRepo;
    }
}
