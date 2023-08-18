package com.numble.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.numble.domain.Account;
import com.numble.domain.Transaction;

@Repository
public class AccountRepository {

	@Autowired
	private SqlSession sqlSession;
	
	private static final String namespace = "com.numble.AccountMapper";
	
	public Account read(int accountId) throws Exception{
		Account accountVO = sqlSession.selectOne(namespace + ".selectById", accountId);
		return accountVO;
	}
	
	public List<Account> readList() throws Exception{
		List<Account> accountList = sqlSession.selectList(namespace + ".selectAll");
		return accountList;
	}
	
	public void add(Account newAccount) throws Exception{
		sqlSession.insert(namespace + ".insert", newAccount);
	}
	
	public void update(Account accountVO) throws Exception{
		sqlSession.update(namespace + ".update", accountVO);
	}
	
	public void delete(int accountId) throws Exception{
		sqlSession.delete(namespace + ".delete", accountId);
	}
	
	public void addTransaction(Transaction transactionVO) throws Exception{
		sqlSession.insert(namespace + ".insertTransaction", transactionVO);
	}
	
	public List<Transaction> readTransactionList() throws Exception{
		List<Transaction> TransactionList = sqlSession.selectList(namespace + ".selectTransactionALL");
		return TransactionList;
	}
}
