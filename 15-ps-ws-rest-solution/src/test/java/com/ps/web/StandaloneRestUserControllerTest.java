package com.ps.web;

import com.ps.base.UserType;
import com.ps.config.WebConfig;
import com.ps.ents.User;
import com.ps.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;

import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Created by iuliana.cosmina on 11/9/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, StandaloneRestUserControllerTest.TestConfig.class})
@WebAppConfiguration
public class StandaloneRestUserControllerTest {

    @Configuration
    static class TestConfig {
        @Bean
        UserService userService() {
            return Mockito.mock(UserService.class);
        }
    }

    private RestTemplate restTemplate = null;

    private MockRestServiceServer server = null;

    @Before
    public void setup() {
        restTemplate = new RestTemplate();
        server = MockRestServiceServer.bindTo(restTemplate).build();
    }


    //Test GET all
    @Test
    public void getAll() {
        server.expect(once(), requestTo("/users")).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("[ {\n" +
                        "  \"id\" : 1571,\n" +
                        "  \"username\" : \"johnnybig\",\n" +
                        "  \"firstName\" : \"Johnny\",\n" +
                        "  \"lastName\" : \"Big\",\n" +
                        "  \"password\" : \"test\",\n" +
                        "  \"userType\" : \"SITTER\",\n" +
                        "  \"email\" : \"Johnny.Big@pet.com\",\n" +
                        "  \"rating\" : 0.0,\n" +
                        "  \"active\" : true\n" +
                        "}]", MediaType.APPLICATION_JSON_UTF8));

        User[] users = restTemplate.getForObject("/users", User[].class);

        assertTrue(users.length > 0);
    }

    //Test GET by username
    @Test
    public void findByUsername() {
        server.expect(once(), requestTo("/users/johncusack"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("{\n" +
                        "  \"id\" : 1575,\n" +
                        "  \"username\" : \"johncusack\",\n" +
                        "  \"firstName\" : \"John\",\n" +
                        "  \"lastName\" : \"Cusack\",\n" +
                        "  \"password\" : \"test\",\n" +
                        "  \"userType\" : \"OWNER\",\n" +
                        "  \"email\" : \"John.Cusack@pet.com\",\n" +
                        "  \"rating\" : 0.0,\n" +
                        "  \"active\" : true\n" +
                        "}", MediaType.APPLICATION_JSON_UTF8));

        User user = restTemplate.getForObject("/users/{$username}", User.class, "johncusack");
        assertNotNull(user);
        assertEquals("John.Cusack@pet.com", user.getEmail());
        assertEquals(UserType.OWNER, user.getUserType());
    }

}
