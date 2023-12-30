package com.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.entity.User;
@Service
public class userDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserServiceImpl userService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=this.userService.getUser(username);
		if(user==null) {
			
			System.out.println("User not found");
			throw new UsernameNotFoundException("No user found");
		}
		
		return user;
	}

}
