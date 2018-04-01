package com.ps.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by iuliana.cosmina on 10/24/16.
 */
@Transactional(readOnly = true)
public interface UserRepo extends JpaRepository<User, Long> {

	@Query("select u from User u where u.username like %?1%")
	List<User> findAllByUserName(String username);

	@Query("select u from User u where u.username= :un")
	User findOneByUsername(@Param("un") String username);

	@Query("select u from User u where u.email= :email")
	User findByEmail(@Param("email") String email);

	@Query("select u.username from User u where u.id= :id")
	String findUsernameById(@Param("id")Long id);

	@Query("select count(u) from User u")
	long countUsers();

	@Query("select u from User u")
	List<User> findAll();

}