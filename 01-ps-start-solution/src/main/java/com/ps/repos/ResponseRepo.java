package com.ps.repos;

import com.ps.base.ResponseStatus;
import com.ps.ents.Request;
import com.ps.ents.Response;
import com.ps.ents.User;

import java.util.Set;

/**
 * Created by iuliana.cosmina on 2/22/16.
 */
public interface ResponseRepo extends AbstractRepo<Response>{

    Set<Response> findAllForUser(User user);

    Set<Response> findAllByStatus(ResponseStatus status);
}