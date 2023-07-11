package com.numble.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.numble.domain.UserVO;

public class UserDAOImpl implements UserDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public void add(UserVO newUser) throws Exception{
		
	}
	
}
