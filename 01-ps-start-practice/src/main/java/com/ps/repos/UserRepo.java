package com.ps.repos;

import com.ps.base.UserType;
import com.ps.ents.Request;
import com.ps.ents.User;

import java.util.Set;

/**
 * Created by iuliana.cosmina on 2/23/16.
 */
public interface UserRepo  extends AbstractRepo<User> {

    Set<User> findAllByUserName(String username, boolean exactMatch);

    Set<User> findByRating(double startRating, double endRating);
}