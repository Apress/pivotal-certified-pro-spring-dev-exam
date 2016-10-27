package com.ps.user;

import com.ps.ex.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by iuliana.cosmina on 10/24/16.
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserRepo userRepository;
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping("/")
	public List<User> all() {
		List<User> users = userRepository.findAll();
		logger.info("Users extracted");
		if (users.isEmpty())
			throw new UserNotFoundException("all");
		else {
			return users;
		}
	}

	@RequestMapping("/id/{id}")
	public User byUsername(@PathVariable("id") Long id) {
		User user = userRepository.findOne(id);
		if (user == null)
			throw new UserNotFoundException(id);
		else {
			return user;
		}
	}


	@RequestMapping("/{username}")
	public User byUsername(@PathVariable("username") String username) {
		User user = userRepository.findOneByUsername(username);
		if (user == null)
			throw new UserNotFoundException(username);
		else {
			return user;
		}
	}
}
