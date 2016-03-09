package com.ps.repo.stub;

import com.ps.base.RequestStatus;
import com.ps.ents.Pet;
import com.ps.ents.Request;
import com.ps.ents.User;
import com.ps.repos.PetRepo;
import com.ps.repos.RequestRepo;
import org.apache.commons.lang3.NotImplementedException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by iuliana.cosmina on 2/22/16.
 */
public class StubRequestRepo extends StubAbstractRepo<Request> implements RequestRepo {
    @Override
    public Set<Request> findAllForUser(User user) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public Set<Request> findByStatus(RequestStatus status) {
        throw new NotImplementedException("Not needed for this stub.");
    }
}
