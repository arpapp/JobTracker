package com.skilldistillery.jobtracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.jobtracker.entities.User;
import com.skilldistillery.jobtracker.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public User findUserById(int id) {
		User user = null;
		Optional<User> userOpt = userRepo.findById(id);
		if(userOpt.isPresent()) {
			user = userOpt.get();
		}
		return user;
	}

	@Override
	public List<User> findAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public User updateUser(String username, User user) {
		User managedUser = userRepo.findByUsername(username);
		managedUser.setFirstName(user.getFirstName());
		userRepo.saveAndFlush(managedUser);
		return managedUser;
	}

	@Override
	public boolean disableUser(String username) {
		User managedUser = userRepo.findByUsername(username);
		managedUser.setEnabled(false);
		userRepo.saveAndFlush(managedUser);
		//if managed user was succesfully disabled, return true
		if(!managedUser.isEnabled()) {
			return true;
		}
		return false;
	}

}
