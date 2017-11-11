package com.ps.services.impl;

import com.ps.base.UserType;
import com.ps.ents.User;
import com.ps.exceptions.MailSendingException;
import com.ps.repos.UserRepo;
import com.ps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.ps.util.RecordBuilder.buildUser;

/**
 * Created by iuliana.cosmina on 7/15/16.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User findById(Long id) {
        return userRepo.findOne(id);
    }


    @Override
    public void create(String email, String password, UserType userType) {
        User user = buildUser(email);
        user.setPassword(password);
        userRepo.save(user);
    }

}
