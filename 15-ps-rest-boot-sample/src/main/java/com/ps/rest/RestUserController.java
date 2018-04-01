package com.ps.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriTemplate;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by iuliana.cosmina on 10/18/16.
 */
@RestController
public class RestUserController {

    private Map<String, User> userService = new HashMap<>();

    @PostConstruct
    public void init() {
        User user = new User("johncusack", "John.Cusack@pet.com", 5d, true);
        userService.put(user.getUsername(), user);
        user = new User("jessicajones","Jessica.Jones@pet.com", 4d, true);
        userService.put(user.getUsername(), user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> all() {
        return new ArrayList<>(userService.values());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public void create(@RequestBody @Valid User newUser, @Value("#{request.requestURL}")
            StringBuffer originalUrl, HttpServletResponse response) throws UserException {
        if (userService.containsKey(newUser.getUsername())) {
            throw new UserException("User found with username " + newUser.getUsername() + ". Cannot create!");
        }
        userService.put(newUser.getUsername(), newUser);
        response.setHeader("Location", getLocationForUser(originalUrl, newUser.getUsername()));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/users/{$username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getByUsername(@PathVariable("$username") String username) throws UserException {
        User user = userService.get(username);
        if (user == null) {
            throw new UserException("User not found with username " + username);
        }
        return user;
    }

    /**
     * Determines URL of user resource based on the full URL of the given request,
     * appending the path info with the given childIdentifier using a UriTemplate.
     */
    protected static String getLocationForUser(StringBuffer url, Object childIdentifier) {
        UriTemplate template = new UriTemplate(url.toString() + "/{$username}");
        return template.expand(childIdentifier).toASCIIString();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/users/{$username}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public User update(@PathVariable("$username") String username, @RequestBody User newUser) throws UserException {
        User user = userService.get(username);
        if (user == null) {
            throw new UserException("User not found with username " + username);
        }
        user.setEmail(newUser.getEmail());
        user.setRating(newUser.getRating());
        userService.put(user.getUsername(), newUser);
        return user;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/users/{$username}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("$username") String username) throws UserException {
        User user = userService.get(username);
        if (user == null ) {
            throw new UserException("No user found for username " + username);
        }
        userService.remove(username);
    }

}
