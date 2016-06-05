package com.ps.services;

import com.ps.ents.User;
import com.ps.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.security.Principal;
import java.util.Set;

/**
 * Created by iuliana.cosmina on 6/3/16.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Set<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public void updateUsername(Long id, String username) {
        userRepo.updateUsername(id, username);
    }

    @Override
    public void updatePassword(Long id, String password) {
        userRepo.updatePassword(id, password);
    }

    @Override
    public User findById(Long id) {
        return userRepo.findById(id);
    }
}
