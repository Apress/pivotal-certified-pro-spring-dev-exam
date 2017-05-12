package com.ps.repos.impl;

import com.ps.ents.User;
import com.ps.repos.UserRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Set;

import static com.ps.ents.User.*;

/**
 * Created by iuliana.cosmina on 6/4/16.
 */
@Repository("userJpaRepo")
public class JpaUserRepo implements UserRepo {

    private EntityManager entityManager;

    @PersistenceContext
    void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> findAllByUserName(String username, boolean exactMatch) {
        if (exactMatch) {
            return entityManager.createNamedQuery(FIND_BY_USERNAME_EXACT)
                    .setParameter("un", username).getResultList();
        } else {
            return entityManager.createNamedQuery(FIND_BY_USERNAME_LIKE)
                    .setParameter("un", "%" + username + "%").getResultList();
        }
    }

    @Override
    public List<User> findAllByLastName(String username) {
        CriteriaBuilder builder= entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> userRoot = query.from(User.class);
        ParameterExpression<String> value = builder.parameter(String.class);
        query.select(userRoot).where(builder.equal(userRoot.get("lastName"), value));

        TypedQuery<User> tquery = entityManager.createQuery(query);
        tquery.setParameter(value,username);
        return tquery.getResultList();
    }

    @Override
    public List<String> findAllFirstNames() {
        Query query = entityManager.createNativeQuery(
                "select first_name from P_USER"
        );
       return query.getResultList();
    }

    @Override
    public String findUsernameById(Long id) {
        return (String) entityManager.createQuery("select u.username from User u where u.id= :id").
                setParameter("id", id).getSingleResult();
    }

    @Override
    public long countUsers() {
        return (Long) entityManager.createQuery("select count(u) from User u").getSingleResult();
    }

    @Override
    public void updatePassword(Long userId, String newPass) {
        User user = (User) entityManager.createQuery("from User u where u.id= :id").
                setParameter("id", userId).getSingleResult();
        user.setPassword(newPass);
        entityManager.merge(user);
    }

    @Override
    public void updateUsername(Long userId, String username) {
        User user = (User) entityManager.createQuery("from User u where u.id= :id").
                setParameter("id", userId).getSingleResult();
        user.setUsername(username);
        entityManager.merge(user);
    }

    @Override
    public void deleteById(Long userId) {
        User user = (User) entityManager.createQuery("from User u where u.id= :id").
                setParameter("id", userId).getSingleResult();
        entityManager.remove(user);
    }

    @Override
    public void save(Set<User> users) {
        for (User user : users) {
            entityManager.persist(user);
        }
    }

    @Override
    public void deleteAll() {
        List<User> users = findAll();
        for (User user : users) {
            entityManager.remove(user);
        }
    }
}
