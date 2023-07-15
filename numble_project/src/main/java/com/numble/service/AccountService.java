package com.numble.service;

import java.util.List;

import com.numble.domain.AccountVO;

public interface AccountService {
	public AccountVO readAccount(int accountId) throws Exception;
	public List<AccountVO> readAccountList() throws Exception;
	public void addAccount(AccountVO newAccount) throws Exception; 
	public void deleteAccount(int accountId) throws Exception;
	
}
