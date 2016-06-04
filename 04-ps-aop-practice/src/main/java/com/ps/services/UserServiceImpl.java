package com.ps.services;

import com.ps.ents.User;
import com.ps.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
/*
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import sun.plugin.liveconnect.SecurityContextHelper;
*/

import java.security.Principal;

/**
 * Created by iuliana.cosmina on 6/3/16.
 */
public class UserServiceImpl implements UserService {

   /* @Autowired
    UserRepo userRepo;

    public User getUserForEditing(Long id){
        Principal principal = (Principal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!hasPermission(p)) {
            throw new AccessDeniedException("You do not have the right to edit an user!");
        }

        return userRepo.findById(id);
    }

    public User deleteUser(Long id){
        Principal principal = (Principal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!hasDeletionRights(p)) {
            throw new AccessDeniedException("You have to be ADMIN to perform this operation");
        }

        return userRepo.deletebyID(id);
    }
*/
}
