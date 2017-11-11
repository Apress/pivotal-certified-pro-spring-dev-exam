package com.ps.services.impl;

import com.ps.ents.User;
import com.ps.exceptions.MailSendingException;
import com.ps.repos.util.UserRowMapper;
import com.ps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

/**
 * Created by iuliana.cosmina on 7/17/16.
 */
@Service("programaticUserService2")
public class ProgramaticUserService2 implements UserService {

    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;

    @Autowired
    public ProgramaticUserService2(DataSource dataSource, PlatformTransactionManager txManager) {
        this.transactionManager = txManager;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User findById(Long id) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        String sql = "select u.ID as ID, u.USERNAME as USERNAME, u.USER_TYPE as USER_TYPE," +
                " u.EMAIL as EMAIL, u.PASSWORD as PASSWORD from P_USER u where u.ID = ?";
        User user;
        try {
            user = jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserRowMapper());
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
        return user;
    }

    @Override
    public void htmlAllByNameAll(String name) {
        //  not implemented
    }

    @Override
    public int countUsers() {
        return 0;
    }


    @Override
    public int updatePassword(Long userId, String newPass) throws MailSendingException {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        String sql = "update P_USER set PASSWORD=? where ID = ?";
        int result;
        try {
            result  = jdbcTemplate.update(sql, new Object[]{newPass, userId});

            sql = "select u.ID as ID, u.USERNAME as USERNAME, u.USER_TYPE as USER_TYPE," +
                    " u.EMAIL as EMAIL, u.PASSWORD as PASSWORD from P_USER u where u.ID = ?";
            User user = jdbcTemplate.queryForObject(sql, new Object[]{userId}, new UserRowMapper());
            transactionManager.commit(status);
            String email = user.getEmail();
            sendEmail(email);
            return result;
        } catch (MailSendingException e) {
            status.setRollbackOnly();
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
        return 0;
    }

    private void sendEmail(String email) throws MailSendingException {
        if (true) {
            throw new MailSendingException("Confirmation email for password could not be sent. Password was not send.");
        }
    }

}
