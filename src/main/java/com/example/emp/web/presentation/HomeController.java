package com.example.emp.web.presentation;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.emp.core.security.entities.CustomUser;


@Controller
public class HomeController {
	
	
	@RequestMapping(value="/helloworld", method = RequestMethod.GET)
	public String helloWorld(ModelMap model) {
		
		/*Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user=null;
		if (principal instanceof User) {
		user = ((User)principal);
		}
	 */
	//String name = user.getUsername();
	//model.addAttribute("username", name);
	model.addAttribute("message", "Welcome to the Hello World page");
	return "helloworld";
	 
	}
	
	@RequestMapping(value="/secured/home", method = RequestMethod.GET)
	public String securedHome(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CustomUser user=null;
		if (principal instanceof CustomUser) {
		user = ((CustomUser)principal);
		}
	 
	String name = user.getUsername();
	model.addAttribute("username", name);
	model.addAttribute("message", "Welcome to the secured page");
	return "home";
	 
	}
	 
	 

}