package com.numble.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.numble.domain.UserVO;
import com.numble.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDAO;
	
	public void addUser (UserVO newUser) throws Exception{
		userDAO.add(newUser);
	}
	
	
	
}
