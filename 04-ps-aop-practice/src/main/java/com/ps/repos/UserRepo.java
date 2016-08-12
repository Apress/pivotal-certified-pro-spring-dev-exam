package com.ps.repos;

import com.ps.ents.User;

import java.util.Set;

/**
 * Created by iuliana.cosmina on 2/23/16.
 */
public interface UserRepo  {

    Set<User> findAll();

    User findById(Long id);

    Set<User> findAllByUserName(String username, boolean exactMatch);

    int updatePassword(Long userId, String newPass);

    int updateUsername(Long userId, String username);

}