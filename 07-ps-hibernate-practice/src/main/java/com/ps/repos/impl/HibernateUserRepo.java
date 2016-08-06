package com.ps.repos.impl;

import com.ps.ents.User;
import com.ps.repos.UserRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by iuliana.cosmina on 6/4/16.
 */
@Repository
@SuppressWarnings("unchecked")
public class HibernateUserRepo implements UserRepo {

    @Autowired
    private SessionFactory sessionFactory;

    public HibernateUserRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Returns the session associated with the ongoing reward transaction.
     *
     * @return the transactional session
     */
    protected Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<User> findAll() {
        return session().createQuery("from User u").list();
    }

    @Override
    public User findById(Long id) {
        return session().get(User.class, id);
    }

    @Override
    public void save(User user) {
        session().persist(user);
    }

    @Override
    public List<User> findAllByUserName(String username, boolean exactMatch) {
        if (exactMatch) {
            return new ArrayList<>();  // TODO 36. Add Hibernate query to extract wll users with username = :username
        } else {
            return session().createQuery("from User u where username like ?")
                    .setParameter(0, "%" + username + "%").list();
        }
    }

    @Override
    public String findUsernameById(Long id) {
        return (String) session().createQuery("select u.username from User u where u.id= :id").
                setParameter("id", id).uniqueResult();
    }

    @Override
    public long countUsers() {
        return 0L; // TODO 37. Add query to count all users
    }

    @Override
    public void updatePassword(Long userId, String newPass) {
        User user = (User) session().createQuery("from User u where u.id= :id").
                setParameter("id", userId).uniqueResult();
        user.setPassword(newPass);
        session().update(user);
    }

    @Override
    public void updateUsername(Long userId, String username) {
        User user = (User) session().createQuery("from User u where u.id= :id").
                setParameter("id", userId).uniqueResult();
        user.setUsername(username);
        session().update(user);
    }

    @Override
    public void deleteById(Long userId) {
        // TODO 38. Add code to delete an user by its id.
    }

    @Override
    public void save(Set<User> users) {
        for (User user : users) {
            session().saveOrUpdate(user);
        }
    }
}
