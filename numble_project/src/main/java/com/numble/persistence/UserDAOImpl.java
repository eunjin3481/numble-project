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
