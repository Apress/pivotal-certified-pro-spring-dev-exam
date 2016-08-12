package com.ps.repo.stub;

import com.ps.base.ResponseStatus;
import com.ps.ents.Response;
import com.ps.ents.User;
import com.ps.repos.ResponseRepo;
import org.apache.commons.lang3.NotImplementedException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by iuliana.cosmina on 2/22/16.
 */
public class StubResponseRepo extends StubAbstractRepo<Response> implements ResponseRepo {

    @Override
    public Set<Response> findAllForUser(User user) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public Set<Response> findAllByStatus(ResponseStatus status) {
        throw new NotImplementedException("Not needed for this stub.");
    }
}
