package com.ps.services;

import com.ps.ents.User;
import com.ps.exceptions.MailSendingException;

/**
 * Created by iuliana.cosmina on 7/15/16.
 */
public interface UserService {

    User findById(Long id);

    void htmlAllByNameAll(String name);

    int countUsers();

    int updatePassword(Long userId, String newPass) throws MailSendingException;
}
