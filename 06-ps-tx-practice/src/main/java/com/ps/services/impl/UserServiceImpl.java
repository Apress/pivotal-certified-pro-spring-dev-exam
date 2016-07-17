package com.ps.services.impl;

import com.ps.ents.User;
import com.ps.repos.UserRepo;
import com.ps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by iuliana.cosmina on 7/15/16.
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public User findById(Long id) {
        return userRepo.findById(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void htmlAllByNameAll(String name){
        userRepo.htmlAllByName(name);
    }

    @Override
    public int countUsers() {
        return userRepo.countUsers();
    }
}
