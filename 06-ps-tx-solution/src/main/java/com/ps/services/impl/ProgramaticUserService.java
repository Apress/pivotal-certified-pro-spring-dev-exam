package com.ps.services.impl;

import com.ps.ents.User;
import com.ps.exceptions.MailSendingException;
import com.ps.repos.UserRepo;
import com.ps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * Created by iuliana.cosmina on 7/17/16.
 */
@Service("programaticUserService")
public class ProgramaticUserService implements UserService {

    private UserRepo userRepo;

    private TransactionTemplate txTemplate;

    @Autowired
    public ProgramaticUserService(UserRepo userRepo, PlatformTransactionManager txManager) {
        this.userRepo = userRepo;
        this.txTemplate = new TransactionTemplate(txManager);
    }

    @Override
    public User findById(Long id) {
        return txTemplate.execute(status -> {
            User user = null;
            try {
                user = userRepo.findById(id);
            } catch (Exception e) {
                status.setRollbackOnly();
            }
            return user;
        });
    }

    @Override
    public void htmlAllByNameAll(String name) {
        userRepo.htmlAllByName(name);
    }

    @Override
    public int countUsers() {
        return userRepo.countUsers();
    }


    @Override
    public int updatePassword(Long userId, String newPass) throws MailSendingException {
        return txTemplate.execute(status -> {
            try {
                User user = userRepo.findById(userId);
                String email = user.getEmail();
                sendEmail(email);
                return userRepo.updatePassword(userId, newPass);
            } catch (MailSendingException e) {
                status.setRollbackOnly();
            }
            return 0;
        });
    }

    private void sendEmail(String email) throws MailSendingException {
        if (true) {
            throw new MailSendingException("Configrmation email for password could not be sent. Password was not send.");
        }
    }
}
