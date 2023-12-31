package com.exam.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.entity.Role;
import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
//	create user
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		
		user.setProfile("default.png");
//		encoding the password with bcryptPasswordEncoder
		user.setPassword(this.bcryptPasswordEncoder.encode(user.getPassword()));
		
		Set<UserRole>userRoles=new HashSet<>();
		
		Role role=new Role();
		role.setRoleId(45L);
		role.setRoleName("NORMAL");
		
		UserRole userRole=new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		
		userRoles.add(userRole);
		return this.userService.createUser(user, userRoles);
	}
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username")String username) {
		
		return this.userService.getUser(username);
	}
	
//	delete user by userid
	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId")Long userId) {
		
		this.userService.deleteUser(userId);
	}
	
//	update user
	/*
	 * @PostMapping("/updateuser") public User updateUser(@RequestBody User user) {
	 * // retriving the User by username User local =
	 * this.userService.getUser(user.getUserName()); local.setEma return
	 * this.userService.updateUser(user); }
	 */
}
