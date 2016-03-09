package com.ps.services;

import com.ps.ents.Pet;
import com.ps.ents.Request;
import com.ps.ents.Review;
import com.ps.ents.User;
import com.ps.util.Pair;
import org.joda.time.DateTime;

/**
 * Created by iuliana.cosmina on 2/22/16.
 */
public interface RequestService {

    Request createRequest(User user, Pet pet, Pair<DateTime, DateTime> interval, String details);

}
