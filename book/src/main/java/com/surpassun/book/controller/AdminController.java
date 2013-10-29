package com.surpassun.book.controller;

import java.security.Principal;
import java.util.logging.Logger;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.surpassun.book.util.ViewName;

public class AdminController {
	
	private Logger log = Logger.getLogger(AdminController.class.getName());

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String view(ModelMap model, Principal principal) {

		String username = principal.getName();
		model.addAttribute("username", username);
		log.info(username + " tried to access to the admin page");
		
		return ViewName.ADMIN;

	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(ModelMap model, Principal principal) {
		String username = principal.getName();
		log.warning(username + " tried to access to the admin page, but he does not have the authorization! Redirect to home page.");
		
		return ViewName.HOME;
	}
}
