package com.ps.web;

import com.ps.base.UserType;
import com.ps.ents.User;
import com.ps.exs.UserException;
import com.ps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * Created by iuliana.cosmina on 10/16/16.
 */
@RestController
@RequestMapping("/service")
public class RestUserController {

    @Autowired
    UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/user/{$email}", method = RequestMethod.POST)
    public void create(@RequestBody User newUser, @Value("#{request.requestURL}")
            StringBuffer originalUrl, HttpServletResponse response) throws UserException {
        if (newUser.getId() != null) {
            throw new UserException("User found with email " + newUser.getEmail() + ". Cannot create!");
        }
        userService.create(newUser.getEmail(), newUser.getPassword(), newUser.getUserType());
        newUser = userService.findByEmail(newUser.getEmail());
        response.setHeader("Location", getLocationForUser(originalUrl, newUser.getId()));
    }

    /**
     * Determines URL of transaction resource based on the full URL of the given request,
     * appending the path info with the given childIdentifier using a UriTemplate.
     */
    protected static String getLocationForUser(StringBuffer url, Object childIdentifier) {
        UriTemplate template = new UriTemplate(url.toString());
        return template.expand(childIdentifier).toASCIIString();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/user/update/{$email}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateByEmail(@PathVariable("$email") String email, @RequestBody User newUser) throws UserException {
        User transaction = userService.findByEmail(email);
        if (transaction == null) {
            throw new UserException("User not found with email " + email);
        }
        //TODO fix this
        userService.create(newUser.getEmail(), newUser.getPassword(), newUser.getUserType());
        return newUser;
    }


    //GET methods

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<User> getAll() throws UserException {
        return userService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/user/{$email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getByEmail(@PathVariable("$email") String email) throws UserException {
        User user = userService.findByEmail(email);
        if (user == null) {
            throw new UserException("User not found with email " + email);
        }
        return user;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/types/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<User> getByType(@PathVariable UserType type) throws UserException {
        Collection<User> result = userService.findByType(type);
        if (result == null || result.isEmpty()) {
            throw new UserException("No user found for type " + type);
        }
        return result;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/types/emails/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<String> getIdsByType(@PathVariable UserType type) throws UserException {
        Collection<String> result = userService.getEmailsByType(type);
        if (result == null || result.isEmpty()) {
            throw new UserException("No user found for type " + type);
        }
        return result;
    }

}
