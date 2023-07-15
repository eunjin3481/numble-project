package com.numble.persistence;

import com.numble.domain.UserVO;

public interface UserDAO {
	public void add(UserVO newUser) throws Exception; 
	public void delete(int userId) throws Exception;
	public boolean findById(int userId) throws Exception;
}
