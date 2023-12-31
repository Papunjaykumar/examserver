package com.exam.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.config.JwtUtil;
import com.exam.entity.JwtRequest;
import com.exam.entity.JwtResponse;
import com.exam.entity.User;
import com.exam.service.impl.userDetailsServiceImpl;

@RestController
@CrossOrigin("*")
public class AuthenticateController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private userDetailsServiceImpl userDetailsService;
	@Autowired
	private JwtUtil jwtUtil;
	
//	generate token
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		try {
			
			this.authenticate(jwtRequest.getUserName(), jwtRequest.getPassword());
			
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User not foun"
					+ "d");
		}
//		user authenticatted 
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUserName());
		System.out.println(userDetails);
		String generateToken = this.jwtUtil.generateToken(userDetails);		
		return  ResponseEntity.ok(new JwtResponse(generateToken));
	}
	
	private void authenticate(String username,String password) throws Exception {
		
		try {
			System.out.println(username);
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
		}catch(DisabledException e) {
			throw new Exception("USER DISABLED "+e.getMessage());
		}catch(BadCredentialsException e) {
			throw new Exception("Incalid Credentials "+e.getMessage());
		}
	}
//	return the details of current user
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		
		return (User) this.userDetailsService.loadUserByUsername(principal.getName());
	}

}
