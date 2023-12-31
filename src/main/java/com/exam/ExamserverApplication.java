package com.exam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.exam.entity.Role;
import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.service.UserService;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Starting Code");

		/*
		 * User user = new User(); user.setFirstName("Papunjay");
		 * user.setLastName("Kumar"); user.setUserName("popu");
		 * user.setPassword(this.bcryptPasswordEncoder.encode("popu"));
		 * user.setEmail("papunjay@gmail.com"); user.setProfile("default.png");
		 * user.setPhone("9798762735");
		 * 
		 * Role role1 = new Role(); role1.setRoleId(44L); role1.setRoleName("ADMIN");
		 * 
		 * Set<UserRole> userRoleSet = new HashSet<>(); UserRole userRole = new
		 * UserRole(); userRole.setRole(role1); userRole.setUser(user);
		 * 
		 * userRoleSet.add(userRole);
		 * 
		 * User user1 = this.userService.createUser(user, userRoleSet);
		 * 
		 * System.out.println(user1);
		 */

	}

}
