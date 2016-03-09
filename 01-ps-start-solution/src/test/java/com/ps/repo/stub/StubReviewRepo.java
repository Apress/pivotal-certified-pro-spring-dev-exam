package com.ps.repo.stub;

import com.ps.ents.Request;
import com.ps.ents.Response;
import com.ps.ents.Review;
import com.ps.ents.User;
import com.ps.repos.ReviewRepo;
import org.apache.commons.lang3.NotImplementedException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by iuliana.cosmina on 2/22/16.
 */
public class StubReviewRepo extends StubAbstractRepo<Review> implements ReviewRepo {

    @Override
    public Set<Review> findAllForUser(User user) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public Set<Review> findAllForRequest(Request request) {
        throw new NotImplementedException("Not needed for this stub.");
    }

    @Override
    public Set<Review> findAllForResponse(Response response) {
        throw new NotImplementedException("Not needed for this stub.");
    }
}
