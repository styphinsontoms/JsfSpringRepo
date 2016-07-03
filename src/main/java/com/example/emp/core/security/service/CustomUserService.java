package com.example.emp.core.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.emp.core.security.dao.UserDAOImpl;
import com.example.emp.core.security.entities.CustomUser;

@Service
public class CustomUserService implements UserDetailsService {

	 @Autowired
	 private UserDAOImpl userDao;
	 
	 
	public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
		return userDao.loadUserByUsername(username);
	}

}
