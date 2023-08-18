package com.numble.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.numble.domain.User;
import com.numble.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public void addUser (User newUser) throws Exception{
		userRepository.add(newUser);
	}
	
	public void deleteUser (int userId) throws Exception{
		userRepository.delete(userId);
	}
	
	
}
