package com.springBlog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springBlog.config.CustomUserDetails;
import com.springBlog.entities.User;

//run this class when oauth login

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userService.getUser(username);
		
		if(user != null) {
			return new CustomUserDetails(user);
		}
		throw new UsernameNotFoundException(username);
	}

}
