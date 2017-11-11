package com.ps.init;

import com.ps.ents.User;
import com.ps.repos.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.ps.util.RecordBuilder.buildUser;

/**
 * Created by iuliana.cosmina on 7/22/16.
 */
@Service
public class DBInitializer {
    private Logger logger = LoggerFactory.getLogger(DBInitializer.class);

    @Autowired
    UserRepo userRepo;

    public void initDb() {
        userRepo.deleteAll();
        logger.info("Starting database initialization...");
        Set<User> users = new HashSet<>();
        User john = buildUser("john.cusack@pet.com");
        john.setPassword("test");
        users.add(john);

        User mary = buildUser("Mary.Poppins@pet.com");
        mary.setPassword("test");
        users.add(mary);

        User jessica = buildUser("Jessica.Jones@pet.com");
        jessica.setPassword("test");
        users.add(jessica);


        User johnny = buildUser("johnny.big@pet.com");
        johnny.setPassword("test");
        users.add(johnny);

        User gigi = buildUser("gigi.pedala@pet.com");
        gigi.setPassword("test");
        users.add(gigi);

        userRepo.save(users);
        logger.info("Database initialization finished.");
    }
}
