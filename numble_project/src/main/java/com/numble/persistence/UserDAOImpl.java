package com.numble.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.numble.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "com.numble.UserMapper";
	
	
	public void add(UserVO newUser) throws Exception{
		sqlSession.insert(namespace + ".insert", newUser);
	}
	
}
