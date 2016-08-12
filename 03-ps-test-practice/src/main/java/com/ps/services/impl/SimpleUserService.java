package com.ps.services.impl;

import com.ps.base.UserType;
import com.ps.ents.User;
import com.ps.repos.UserRepo;
import com.ps.services.UserService;
import com.ps.util.RecordBuilder;

import java.util.Set;

/**
 * Created by iuliana.cosmina on 2/23/16.
 */
public class SimpleUserService extends SimpleAbstractService<User>  implements UserService {

    private UserRepo repo;

    @Override
    public User createUser(String email, String password, UserType userType) {
        User user = RecordBuilder.buildUser(email);
        user.setPassword(password);
        user.setUserType(userType);
        repo.save(user);
        return user;
    }

    @Override
    public Set<User> findByName(String name, boolean exact) {
        return repo.findAllByUserName(name, exact);
    }

    // setters & getters
    public void setRepo(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserRepo getRepo() {
        return repo;
    }
}
