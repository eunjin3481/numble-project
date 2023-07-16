package com.numble.persistence;

import java.util.List;

import com.numble.domain.AccountVO;
import com.numble.domain.TransactionVO;

public interface AccountDAO {
	public AccountVO read(int accountId) throws Exception;
	public List<AccountVO> readList() throws Exception;
	public void add(AccountVO newAccount) throws Exception;
	public void update(AccountVO accountVO) throws Exception;
	public void delete(int accountId) throws Exception;
	public void addTransaction(TransactionVO transactionVO) throws Exception;
	public List<TransactionVO> readTransactionList() throws Exception;
}
