package com.ps.web;

import com.ps.ents.User;
import com.ps.problem.NotFoundException;
import com.ps.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by iuliana.cosmina on 8/13/16.
 */
@Controller
@RequestMapping("/users")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	/**
	 * Handles requests to list all persons.
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		logger.info("Populating model with list...");
		model.addAttribute("users", userService.findAll());
		return "users/list";
	}

	/**
	 * Handles requests to show detail about one person.
	 */
	@RequestMapping(value = "/{id:[\\d]*}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model model) throws NotFoundException {
		User user = userService.findById(id);
		//Decomment this to test the DataIntegrityViolationException exception handler
	  /*  if (true) {
            throw new DataIntegrityViolationException("Throw test exception in order to test the global excetion handler");
        }*/
		if (user == null) {
			throw new NotFoundException(User.class, id);
		}
		model.addAttribute("user", user);
		return "users/show";
	}
}
