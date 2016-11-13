package com.ps.rest;

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
            logger.info(user.toString());
        }
    }

    //Test GET by username
    @Test
    public void findByUsername() {
        User user = restTemplate.getForObject(GET_PUT_DEL_URL, User.class, "johncusack");

        assertNotNull(user);
        assertEquals("John.Cusack@pet.com", user.getEmail());
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
        user.setRating(0d);
        user.setActive(true);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<User> crRequest = new HttpEntity<>(user, headers);
        URI uri = this.restTemplate.postForLocation(GET_POST_URL, crRequest, User.class);
        logger.info(">> Location for new user: " + uri);

        // test insertion
        User newUser = restTemplate.getForObject(GET_PUT_DEL_URL, User.class, "doctorwho");

        assertNotNull(newUser);
        assertNotNull(newUser.getUsername());

    }

    // Test PUT
    @Test
    public void editUser() {
        User user = new User();
        user.setEmail("MissJones@pet.com");
        user.setUsername("jessicajones");
        user.setRating(5d);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<User> userRequest = new HttpEntity<>(user, headers);
        ResponseEntity<User> responseEntity = restTemplate.exchange(GET_PUT_DEL_URL, HttpMethod.PUT, userRequest, User.class,
                "jessicajones");

        User editedUser = responseEntity.getBody();
        assertNotNull(editedUser);
        assertEquals("MissJones@pet.com", editedUser.getEmail());
    }

    // Test DELETE
    @Test
    public void deleteUser() {
        restTemplate.delete(GET_PUT_DEL_URL, "doctorwho");
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
