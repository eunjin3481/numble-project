package com.numble.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.numble.domain.UserVO;
import com.numble.persistence.UserDAO;

public class UserServiceImpl {

	@Autowired
	private UserDAO userDAO;
	
	public void addUser (UserVO newUser) throws Exception{
		userDAO.add(newUser);
	}
	
	
	
}
