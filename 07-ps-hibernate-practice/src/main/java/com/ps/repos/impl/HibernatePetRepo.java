package com.ps.repos.impl;

import com.ps.ents.Pet;
import com.ps.ents.User;
import com.ps.repos.PetRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by iuliana.cosmina on 7/16/16.
 */
@Repository
@Transactional
public class HibernatePetRepo implements PetRepo {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Returns the session associated with the ongoing reward transaction.
     *
     * @return the transactional session
     */
    protected Session session() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public List<Pet> findByOwner(User owner) {
        return session().createQuery("select p from Pet p where p.owner = :owner").
                setParameter("owner", owner).list();
    }

}
