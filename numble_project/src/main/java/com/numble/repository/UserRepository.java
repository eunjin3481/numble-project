package com.numble.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.numble.domain.User;

@Repository
public class UserRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "com.numble.UserMapper";
	
	
	public void add(User newUser) throws Exception{
		sqlSession.insert(namespace + ".insert", newUser);
	}
	
	public void delete(int userId) throws Exception{
		sqlSession.delete(namespace + ".delete", userId);
	}
	
	public boolean findById(int userId) throws Exception{
		int count = sqlSession.selectOne(namespace + ".findById", userId);
		if(count > 0 ) 
			return true;
		else
			return false;
		
	}
}
