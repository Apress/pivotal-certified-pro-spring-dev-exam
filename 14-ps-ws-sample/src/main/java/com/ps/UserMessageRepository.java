package com.ps;

import com.ps.ws.UserMessage;
import com.ps.ws.UserType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by iuliana.cosmina on 10/16/16.
 */
@Component
public class UserMessageRepository {

    protected static final Map<String, UserMessage> USER_MESSAGES = new HashMap<>();

    @PostConstruct
    public void init() {
        UserMessage um = new UserMessage();
        um.setEmail("John.Cusack@pet.com");
        um.setActive(true);
        um.setUserType(UserType.BOTH);
        um.setRating(5d);
        USER_MESSAGES.put("John.Cusack@pet.com", um);

        um = new UserMessage();
        um.setEmail("Mary.Poppins@pet.com");
        um.setActive(true);
        um.setUserType(UserType.SITTER);
        um.setRating(4d);
        USER_MESSAGES.put("Mary.Poppins@pet.com", um);

        um = new UserMessage();
        um.setEmail("Jessica.Jones@pet.com");
        um.setActive(true);
        um.setUserType(UserType.OWNER);
        um.setRating(3d);
        USER_MESSAGES.put("Jessica.Jones@pet.com", um);
    }


    /**
     * returns the user type of user with this email
     * @param email
     * @return
     */
    public UserType findUserType(String email) {
        if(USER_MESSAGES.containsKey(email))  {
            return USER_MESSAGES.get(email).getUserType();
        }
        return null;
    }

}
