package com.ps.services;

import com.ps.ents.Request;
import com.ps.ents.Response;
import com.ps.ents.User;

/**
 * Created by iuliana.cosmina on 2/22/16.
 */
public interface ResponseService {
    Response createResponse(User user, Request request, String details);

    void acceptResponse(Request request, Response response);

    Response save(Response response);
}
