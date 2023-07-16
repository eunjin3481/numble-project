package com.numble.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.numble.domain.AccountVO;
import com.numble.domain.TransactionVO;
import com.numble.persistence.AccountDAO;

@Service
public class AccountServieImpl implements AccountService {
	
	@Autowired
	private AccountDAO accountDAO;
	
	public AccountVO readAccount(int accountId) throws Exception{
		return accountDAO.read(accountId);
	}
	
	public List<AccountVO> readAccountList() throws Exception{
		return accountDAO.readList();
	}
	
	public void addAccount(AccountVO newAccount) throws Exception{
		accountDAO.add(newAccount);
	}
	
	public void updateAccount(AccountVO accountVO) throws Exception{
		accountDAO.update(accountVO);
	}
	
	public void deleteAccount(int accountId) throws Exception{
		accountDAO.delete(accountId);
	}
	
	public void insertTransaction(TransactionVO transactionVO) throws Exception{
		accountDAO.addTransaction(transactionVO);
	}
	
	public List<TransactionVO> readTransactionList() throws Exception{
		return accountDAO.readTransactionList();
	}
}
