package com.ps.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by iuliana.cosmina on 10/27/16.
 */
@Controller
public class AllWebController {

	private static Logger logger = LoggerFactory.getLogger(AllWebController.class);

	@Autowired
	private AllWebService allWebService;

	public AllWebController(AllWebService allWebService) {
		this.allWebService = allWebService;
	}

	@RequestMapping("/all/{ownerId}")
	public String byOwner(Model model,
			@PathVariable("ownerId") Long ownerId) {
		UserSkeleton owner = allWebService.findUserById(ownerId);
		if (owner != null) {
			owner.setPets(allWebService.findByOwnerId(ownerId));
		}
		model.addAttribute("owner", owner);
		return "all";
	}
}
