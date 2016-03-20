package com.ps.services;

import com.ps.base.UserType;
import com.ps.ents.User;

/**
 * Created by iuliana.cosmina on 2/23/16.
 */
public interface UserService {

    User createUser(String email, String password, UserType userType);

}
