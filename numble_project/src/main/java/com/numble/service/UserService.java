package com.numble.service;

import com.numble.domain.UserVO;

public interface UserService {
	public void addUser (UserVO newUser) throws Exception;
	public void deleteUser () throws Exception;
	
}
