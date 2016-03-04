package com.ps.services;

import com.ps.base.UserType;
import com.ps.ents.User;
import com.ps.repos.UserRepo;
import com.ps.util.RecordBuilder;

/**
 * Created by iuliana.cosmina on 2/23/16.
 */
public class SimpleUserService implements UserService {

    private UserRepo userRepo;

    @Override
    public User createUser(String email, String password, UserType userType) {
        User user = RecordBuilder.buildUser(email);
        user.setPassword(password);
        user.setUserType(userType);
        userRepo.save(user);
        return user;
    }

    @Override
    public User findById(Long userId) {
        return userRepo.findById(userId);
    }

    @Override
    public User save(User user) {
        userRepo.save(user);
        return user;
    }

    //                setters
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
}
