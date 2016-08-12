package com.ps.services.impl;

import com.ps.ents.User;
import com.ps.exceptions.MailSendingException;
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
//TODO 33. Make all methods required to be executed in a read only transaction.
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
    public void htmlAllByNameAll(String name) {
        userRepo.htmlAllByName(name);
    }

    @Override
    public int countUsers() {
        return userRepo.countUsers();
    }

    // TODO 34. Make this method execute in a read-write transaction and declare the
    // transaction to rollback in case a MailSendingException exception is used
    @Override
    public int updatePassword(Long userId, String newPass) throws MailSendingException {
        User u = userRepo.findById(userId);
        String email = u.getEmail();
        sendEmail(email);
        return userRepo.updatePassword(userId, newPass);
    }

    private void sendEmail(String email) throws MailSendingException {
        if (true) {
            throw new MailSendingException("Configrmation email for password could not be sent. Password was not send.");
        }
    }
}
