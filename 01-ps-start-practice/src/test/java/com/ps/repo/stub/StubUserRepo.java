package com.ps.repo.stub;

import com.ps.ents.User;
import com.ps.repos.UserRepo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by iuliana.cosmina on 2/23/16.
 */
public class StubUserRepo implements UserRepo {

    private Map<Long, User> userMap = new HashMap<>();

    @Override
    public void save(User user) {
        if (user.getId() != null) {
            userMap.put(user.getId(), user);
        }
    }

    @Override
    public void delete(User user) {
        userMap.remove(user.getId());
    }

    @Override
    public void deleteById(Long userId) {
        userMap.remove(userId);
    }

    @Override
    public User findById(Long userId) {
        return userMap.get(userId);
    }
}
