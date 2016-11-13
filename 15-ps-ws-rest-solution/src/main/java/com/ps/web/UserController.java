package com.ps.web;

import com.ps.base.PetType;
import com.ps.base.UserType;
import com.ps.ents.Pet;
import com.ps.ents.User;
import com.ps.exs.UserException;
import com.ps.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriTemplate;

/**
 * Created by iuliana.cosmina on 11/7/16.
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

   /* @Autowired
    PetService petService;

    @RequestMapping(value = "/pets/{$username}", method = RequestMethod.POST)
    public ResponseEntity<String> createPet(@PathVariable("$username") String username,
                                        @RequestBody Pet pet,
                                        @Value("#{request.requestURL}") StringBuffer url) throws UserException {
        User owner = userService.findByUsername(username);
        if (owner == null) {
            throw new UserException("User not found with username " + username);
        }
        petService.save(pet);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", getLocationForPet(url, pet.getRfid()));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }*/

    /**
     * Determines URL of pet resource based on the full URL of the given request,
     * appending the path info with the given childIdentifier using a UriTemplate.
     */
    protected static String getLocationForPet(StringBuffer url, Object petRfid) {
        UriTemplate template = new UriTemplate(url.append("/{$petid}").toString());
        return template.expand(petRfid).toASCIIString();
    }
}
