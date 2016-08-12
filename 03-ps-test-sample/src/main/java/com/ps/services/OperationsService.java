package com.ps.services;

import com.ps.ents.Response;
import com.ps.ents.User;
import com.ps.repos.RequestRepo;
import com.ps.repos.ResponseRepo;
import com.ps.repos.ReviewRepo;
import com.ps.repos.UserRepo;

/**
 * Created by iuliana.cosmina on 3/7/16.
 */
public interface OperationsService {

    Response createResponse(Long sitterId, Long RequestId);

    void acceptResponse(Long requestId, Long responseId);

    User closeRequest(Long requestId, Long reviewId);

    User rateOwner(Long requestId, Long reviewId);

    //           setter skeletons
    void setRequestRepo(RequestRepo requestRepo);

    void setUserRepo(UserRepo userRepo);

    void setResponseRepo(ResponseRepo responseRepo);

    void setReviewRepo(ReviewRepo reviewRepo);
}
