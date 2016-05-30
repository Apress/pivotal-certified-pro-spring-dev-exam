package com.ps.services;

import com.ps.base.UserType;
import com.ps.ents.User;

import java.util.Set;

/**
 * Created by iuliana.cosmina on 2/23/16.
 */
public interface UserService extends AbstractService<User> {

    User createUser(String email, String password, UserType userType);

    /**
     * Method used to search an User by his name
     * @param name name of the user searchign for
     * @param exact if the search should be exact (name= :name), or not ( name like '%name%')
     * @return
     */
    Set<User> findByName(String name, boolean exact);

}
