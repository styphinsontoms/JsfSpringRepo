package com.example.emp.core.security.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.emp.core.security.entities.CustomUser;
import com.example.emp.core.security.entities.Role;

@Repository
public class UserDAOImpl {
	
	 public CustomUser loadUserByUsername(final String username) {
		 //Write your DB call code to get the user details from DB,But I am just hard coding the user
		 	CustomUser user = new CustomUser();
	        user.setFirstName("kb");
	        user.setLastName("gc");
	        user.setUsername("kb");
	        user.setPassword("1234");
	        Role r = new Role();
	        r.setName("ROLE_USER");
	        List<Role> roles = new ArrayList<Role>();
	        roles.add(r);
	        user.setAuthorities(roles);
	        return user;
	    }
	
	
}
