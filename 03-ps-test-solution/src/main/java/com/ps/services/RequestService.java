package com.ps.services;

import com.ps.ents.Pet;
import com.ps.ents.Request;
import com.ps.ents.Review;
import com.ps.ents.User;
import com.ps.util.Pair;
import org.joda.time.DateTime;

import java.util.Set;

/**
 * Created by iuliana.cosmina on 2/22/16.
 */
public interface RequestService extends AbstractService<Request>{

    Request createRequest(User user, Pet pet, Pair<DateTime, DateTime> interval, String details);

    Set<Request> findAllByUser(User user);

}
