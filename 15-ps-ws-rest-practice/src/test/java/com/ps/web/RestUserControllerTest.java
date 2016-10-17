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
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by iuliana.cosmina on 10/17/16.
 */
public class RestUserControllerTest {

    private Logger logger = LoggerFactory.getLogger(RestUserControllerTest.class);

    /**
     * Rest Services endpoints
     */
    private static final String GET_ALL_URL = "http://localhost:8080/service/users";
    private static final String GET_POST_URL = "http://localhost:8080/service/user/{$email}";
    private static final String PUT_URL = "http://localhost:8080/manager/service/update/{$email}";
    private static final String GET_BY_TYPE_URL = "http://localhost:8080/service/types/{$type}";
    private static final String GET_IDS_BY_TYPE_URL = "http://localhost:8080/service/types/emails/{$type}";

    private RestTemplate restTemplate = null;


    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
    }


    @Test
    public void getAll() {
        User[] users = restTemplate.getForObject(GET_ALL_URL, User[].class, "all");

        assertTrue(users.length > 0);
        for (User user : users) {
            logger.info(user.getId() + ", " + user.getEmail());
        }
    }

    @Test
    public void getByEmail() {
        User user = restTemplate.getForObject(GET_POST_URL, User.class, "john.cusack@pet.com");

        assertNotNull(user);
        assertEquals(UserType.OWNER, user.getUserType());
    }

    /**
     * negative test - search for non existent user
     *
     * @throws Exception
     */
    @Test(expected = HttpClientErrorException.class)
    public void tryByEmail() throws Exception {
        restTemplate.getForObject(GET_POST_URL, User.class, "john.cusack@pet.com");
    }


    @Test
    public void getByType() {
        User[] users = restTemplate.getForObject(GET_BY_TYPE_URL, User[].class, "BOTH");

        assertNotNull(users);
        assertEquals(2, users.length);
    }

    /**
     * negative test - search for non existent type
     */
    @Test(expected = HttpClientErrorException.class)
    public void tryByType() {
        restTemplate.getForObject(GET_BY_TYPE_URL, User[].class, "SITTER");
    }


    @Test
    public void getEmailsByType() {
        String[] users = restTemplate.getForObject(GET_IDS_BY_TYPE_URL, String[].class, "OWNER");

        assertNotNull(users);
        assertEquals(2, users.length);
    }


    /**
     * Test PUT
     */
    @Test
    public void editUser() {
        //TODO Fix this
        User user = mockUser("doctor.who@tardis.com", UserType.ADMIN, "what");
        user.setId(2L);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<User> trRequest = new HttpEntity<>(user, headers);
        ResponseEntity<User> responseEntity = restTemplate.exchange(PUT_URL, HttpMethod.PUT, trRequest, User.class, "Jessica.Jones@pet.com");

        User editedTr = responseEntity.getBody();
        assertNotNull(editedTr);
        //assertTrue(111D == editedTr.getAmount());
        //assertEquals("test", editedTr.getType());
    }

    //Test POST
    @Test
    public void createUser() {
        User user = mockUser("doctor.who@tardis.com", UserType.ADMIN, "what");

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final HttpEntity<User> crRequest = new HttpEntity<>(user, headers);
        URI uri = this.restTemplate.postForLocation(GET_POST_URL, crRequest, User.class);
        logger.info(">> Location for new user: " + uri);

        // test insertion
        User[] users = restTemplate.getForObject(GET_BY_TYPE_URL, User[].class, "ADMIN");

        assertNotNull(users);
        assertTrue(users.length > 0);

    }

    private User mockUser(String  email, UserType type, String password){
        User user = new User();
        user.setEmail(email);
        user.setUserType(type);
        user.setPassword(password);
        return user;
    }

}
