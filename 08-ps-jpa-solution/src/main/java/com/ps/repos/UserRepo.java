package com.ps.repos;

import com.ps.ents.User;

import java.util.List;
import java.util.Set;

/**
 * Created by iuliana.cosmina on 2/23/16.
 */
public interface UserRepo {

    List<User> findAll();

    User findById(Long id);

    List<User> findAllByUserName(String username, boolean exactMatch);

    String findUsernameById(Long id);

    long countUsers();

    void save(User user);

    void updatePassword(Long userId, String newPass);

    void updateUsername(Long userId, String username);

    void deleteById(Long userId);

    void save(Set<User> users);

}