package com.skilldistillery.jobtracker.services;

import java.util.List;

import com.skilldistillery.jobtracker.entities.User;

public interface UserService {
	User findUserById(int id);
	List<User> findAllUsers();
}
