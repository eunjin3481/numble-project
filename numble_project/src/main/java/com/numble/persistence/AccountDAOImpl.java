package com.numble.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.numble.domain.AccountVO;
import com.numble.domain.TransactionVO;

@Repository
public class AccountDAOImpl implements AccountDAO{

	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "com.numble.AccountMapper";
	
	public AccountVO read(int accountId) throws Exception{
		AccountVO accountVO = sqlSession.selectOne(namespace + ".selectById", accountId);
		return accountVO;
	}
	
	public List<AccountVO> readList() throws Exception{
		List<AccountVO> accountList = sqlSession.selectList(namespace + ".selectAll");
		return accountList;
	}
	
	public void add(AccountVO newAccount) throws Exception{
		sqlSession.insert(namespace + ".insert", newAccount);
	}
	
	public void update(AccountVO accountVO) throws Exception{
		sqlSession.update(namespace + ".update", accountVO);
	}
	
	public void delete(int accountId) throws Exception{
		sqlSession.delete(namespace + ".delete", accountId);
	}
	
	public void addTransaction(TransactionVO transactionVO) throws Exception{
		sqlSession.insert(namespace + ".insertTransaction", transactionVO);
	}
	
	public List<TransactionVO> readTransactionList() throws Exception{
		List<TransactionVO> TransactionList = sqlSession.selectList(namespace + ".selectTransactionALL");
		return TransactionList;
	}
}
