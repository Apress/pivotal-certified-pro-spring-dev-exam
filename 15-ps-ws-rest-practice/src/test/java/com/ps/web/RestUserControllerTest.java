package com.ps.web;

import com.ps.base.UserType;
import com.ps.ents.User;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.junit.Assert.*;

/**
 * Created by iuliana.cosmina on 10/17/16.
 */
public class RestUserControllerTest {

    private Logger logger = LoggerFactory.getLogger(RestUserControllerTest.class);

    /**
     * Rest Services endpoints
     */
    private static final String GET_POST_URL = "http://localhost:8080/users";
    private static final String GET_PUT_DEL_URL = "http://localhost:8080/users/{$username}";
    private static final String GET_EMAILS_BY_TYPE_URL = "http://localhost:8080/emails/{$type}";

    private RestTemplate restTemplate = null;


    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
    }


    //Test GET all
    @Test
    public void getAll() {
        User[] users = restTemplate.getForObject(GET_POST_URL, User[].class);

        assertTrue(users.length > 0);
        for (User user : users) {
            logger.info(user.getId() + ", " + user.getEmail() + ", " + user.getUsername());
        }
    }

    //Test GET by username
    @Test
    public void findByUsername() {
        User user = null;
        //TODO 58. Use the proper RestTemplate method to retrieve a user resource with username="johncusack"

        assertNotNull(user);
        assertEquals("John.Cusack@pet.com", user.getEmail());
        assertEquals(UserType.OWNER, user.getUserType());
    }

    @Test(expected = HttpClientErrorException.class)
    public void findByUsernameNonExistent() {
        User user = restTemplate.getForObject(GET_PUT_DEL_URL, User.class, "iulianacosmina");
        assertNotNull(user);
    }

    //Test POST
    @Test
    public void createUser() {
        User user = new User();
        user.setEmail("Doctor.Who@tardis.com");
        user.setUsername("doctorwho");
        user.setLastName("Doctor");
        user.setFirstName("Who");
        user.setRating(0d);
        user.setActive(true);
        user.setPassword("what");
        user.setUserType(UserType.ADMIN);
       //TODO 59. Use the proper RestTemplate method to save the user resource created previously

        // test insertion
        User newUser = restTemplate.getForObject(GET_PUT_DEL_URL, User.class, "doctorwho");

        assertNotNull(newUser);
        assertNotNull(newUser.getId());

    }

    // Test PUT
    @Test
    public void editUser() {
        User user = new User();
        user.setEmail("MissJones@pet.com");
        user.setUsername("jessicajones");
        user.setRating(5d);
        user.setLastName("Jessica");
        user.setFirstName("Jones");
        user.setPassword("what");
        user.setUserType(UserType.ADMIN);

        User editedUser = null;
       //TODO 60. Use the proper RestTemplate method to update the user resource with username= "jessicajones"
        // and retrieve the result in the userEdit object
        assertNotNull(editedUser);
        assertEquals("MissJones@pet.com", editedUser.getEmail());
    }

    // Test DELETE
    @Test(expected = HttpClientErrorException.class)
    public void deleteUser() {
        //TODO 61. Use the proper RestTemplate method to delete the user resource with username= "doctorwho"
        // test insertion
        User newUser = restTemplate.getForObject(GET_PUT_DEL_URL, User.class, "doctorwho");
        assertNull(newUser);
    }


    @Test
    public void getEmailsByType() {
        String[] users = restTemplate.getForObject(GET_EMAILS_BY_TYPE_URL, String[].class, "OWNER");

        assertNotNull(users);
        assertEquals(2, users.length);
    }

}
