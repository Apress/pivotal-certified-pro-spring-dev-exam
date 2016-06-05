package com.ps.services;

import com.ps.ents.User;

import java.util.Set;

/**
 * Created by iuliana.cosmina on 6/3/16.
 */
public interface UserService {

    Set<User> findAll();

    void updateUsername(Long id,String username);

    void updatePassword(Long id,String password);

    User findById(Long id);
}
