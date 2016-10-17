package com.ps.services.impl;

import com.ps.base.UserType;
import com.ps.ents.User;
import com.ps.exceptions.MailSendingException;
import com.ps.repos.UserRepo;
import com.ps.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.ps.util.RecordBuilder.buildUser;

/**
 * Created by iuliana.cosmina on 7/15/16.
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findOneByUsername(username);
    }

    @Override
    public long countUsers() {
        return userRepo.countUsers();
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public List<User> findByType(UserType userType) {
        return userRepo.findByType(userType);
    }

    @Override
    public List<String> getEmailsByType(UserType userType) {
        return userRepo.getEmailsByType(userType);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(Long id) {
        logger.info("Sensitive operation -> Deleting user with id:" + id);
        userRepo.delete(id);
    }

    @Override
    @Transactional
    public void create(String email, String password, UserType userType) {
        User user = buildUser(email);
        user.setPassword(password);
        user.setUserType(userType);
        userRepo.save(user);
    }

    @Override
    public User create(User user) {
        userRepo.save(user);
        return user;
    }

    private void sendEmail(String email) throws MailSendingException {
        if (true) {
            throw new MailSendingException("Configrmation email for password could not be sent. Password was not send.");
        }
    }

    @Override
    public void update(User user) {
        userRepo.saveAndFlush(user);
    }

    @Override
    public void deleteByEmail(String email) {
        User user = userRepo.findByEmail(email);
        userRepo.delete(user);
    }
}
