package com.ps.jms.client;

import com.ps.ents.User;

import java.util.List;

/**
 * Created by iuliana.cosmina on 10/9/16.
 */
public interface UserBatchProducer {

    void processUsers(List<User> batch);
}
