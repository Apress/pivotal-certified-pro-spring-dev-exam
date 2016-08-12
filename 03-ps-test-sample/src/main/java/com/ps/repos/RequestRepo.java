package com.ps.repos;

import com.ps.base.RequestStatus;
import com.ps.ents.Request;
import com.ps.ents.User;

import java.util.Set;

/**
 * Created by iuliana.cosmina on 2/22/16.
 */
public interface RequestRepo extends AbstractRepo<Request>{

    Set<Request> findAllForUser(User user);

    Set<Request> findByStatus(RequestStatus status);
}