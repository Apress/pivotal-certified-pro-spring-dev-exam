package com.ps.repos;

import com.ps.ents.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by iuliana.cosmina on 2/23/16.
 */
//TODO 44. Complete the definition of this interface to make the tests in TestUserRepo.java pass.
public interface UserRepo extends JpaRepository<User, Long> {


    List<User> findAllByUserName(String username);

    User findOneByUsername(@Param("un") String username);

    String findUsernameById(Long id);

    long countUsers();

}