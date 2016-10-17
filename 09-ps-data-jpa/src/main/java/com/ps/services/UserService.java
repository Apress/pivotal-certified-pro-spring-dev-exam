package com.ps.services;

import com.ps.base.UserType;
import com.ps.ents.User;

import java.util.List;

/**
 * Created by iuliana.cosmina on 7/15/16.
 */
public interface UserService {

    User findById(Long id);

    User findByEmail(String email);

    User findByUsername(String username);

    long countUsers();

    void create(String email, String password, UserType userType);

    User create(User user);

    void update(User user);

    List<User> findAll();

    List<User> findByType(UserType userType);

    void deleteById(Long id);

    void deleteByEmail(String email);

    List<String> getEmailsByType(UserType userType);

}
