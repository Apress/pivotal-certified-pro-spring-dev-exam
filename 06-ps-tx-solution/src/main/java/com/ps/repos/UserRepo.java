package com.ps.repos;

import com.ps.base.UserType;
import com.ps.ents.User;
import com.ps.util.Pair;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by iuliana.cosmina on 2/23/16.
 */
public interface UserRepo  {

    Set<User> findAll();

    void htmlAllByName(String name);

    User findById(Long id);

    Set<User> findAllByUserName(String username, boolean exactMatch);

    String findUsernameById(Long id);

    Map<String, Object> findByIdAsMap(Long id);

    List<Map<String, Object>> findAllAsMaps();

    int countUsers();

    Pair extractPair();

    int createUser(Long userId, String username, String password, String email, UserType userType);

    int updatePassword(Long userId, String newPass);

    int updateUsername(Long userId, String username);

    int deleteById(Long userId);

    int createTable(String name);

}