package com.exam.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private RoleRepository roleRepo;
//	creating user
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		// TODO Auto-generated method stub
		User local=this.userRepo.findByUserName(user.getUserName());
		if(local!=null) {
			System.out.println("User is already there !!");
			throw new Exception("User already present !!");
		}else {
//			user create
			for(UserRole ur:userRoles) {
				roleRepo.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			local = this.userRepo.save(user);
			
		}
		return local;
	}
//	get user by username
	@Override
	public User getUser(String username) {
		
		return this.userRepo.findByUserName(username);
	}
	@Override
	public void deleteUser(Long userId) {
		
		
		this.userRepo.deleteById(userId);
		
	}
	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return this.userRepo.save(user);
	}

}
