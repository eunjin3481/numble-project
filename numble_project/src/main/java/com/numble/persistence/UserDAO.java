package com.numble.persistence;

import com.numble.domain.UserVO;

public interface UserDAO {
	public void add(UserVO newUser) throws Exception; 
	public void delete() throws Exception;
}
