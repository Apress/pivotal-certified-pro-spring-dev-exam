package com.ps.service;

import com.ps.base.ResponseStatus;
import com.ps.ents.Request;
import com.ps.ents.Response;
import com.ps.ents.User;

/**
 * Created by iuliana.cosmina on 2/22/16.
 */
public class SimpleResponseService implements ResponseService {

    public Response createResponse(User user, Request request, String details) {
        Response response = new Response();
        response.setUser(user);
        response.setRequest(request);
        response.setDetails(details);
        response.setResponseStatus(ResponseStatus.PROPOSED);
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
        }
    }
}
