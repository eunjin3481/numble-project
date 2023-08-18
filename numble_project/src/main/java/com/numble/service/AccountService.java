package com.numble.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.numble.domain.Account;
import com.numble.domain.Transaction;
import com.numble.repository.AccountRepository;

@Service
public class AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	public Account readAccount(int accountId) throws Exception{
		return accountRepository.read(accountId);
	}
	
	public List<Account> readAccountList() throws Exception{
		return accountRepository.readList();
	}
	
	public void addAccount(Account newAccount) throws Exception{
		accountRepository.add(newAccount);
	}
	
	public void updateAccount(Account accountVO) throws Exception{
		accountRepository.update(accountVO);
	}
	
	public void deleteAccount(int accountId) throws Exception{
		accountRepository.delete(accountId);
	}
	
	public void insertTransaction(Transaction transactionVO) throws Exception{
		accountRepository.addTransaction(transactionVO);
	}
	
	public List<Transaction> readTransactionList() throws Exception{
		return accountRepository.readTransactionList();
	}
}
