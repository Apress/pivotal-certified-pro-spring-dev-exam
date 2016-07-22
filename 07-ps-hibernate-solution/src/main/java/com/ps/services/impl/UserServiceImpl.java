package com.ps.services.impl;

import com.ps.base.UserType;
import com.ps.ents.User;
import com.ps.exceptions.MailSendingException;
import com.ps.repos.UserRepo;
import com.ps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static com.ps.util.RecordBuilder.buildUser;

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

    @Override
    public long countUsers() {
        return userRepo.countUsers();
    }

    @Override
    @Transactional
    public void create(String email, String password, UserType userType) {
        User user = buildUser(email);
        user.setPassword(password);
        user.setUserType(userType);
        userRepo.save(user);
    }

    private void sendEmail(String email) throws MailSendingException {
        if (true) {
            throw new MailSendingException("Configrmation email for password could not be sent. Password was not send.");
        }
    }
}
