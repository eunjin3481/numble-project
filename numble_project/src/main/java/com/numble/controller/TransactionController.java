package com.numble.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.numble.domain.AccountVO;
import com.numble.domain.TransactionVO;
import com.numble.service.AccountService;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

	@Autowired(required = true)
	private AccountService accountService;

	// 송금
	@RequestMapping(value = "/remittance", method = RequestMethod.POST)
	public ResponseEntity<?> remittance(@RequestBody Map<String, Object> requestBody, HttpServletRequest request)
			throws Exception {
		// 토큰에서 추출한 user id
		int tokenUserId = (int) request.getAttribute("userId");
		int accountId = (int) requestBody.get("accountId");
		int amount = (int) requestBody.get("amount");
		String receiverAccountNumber = (String) requestBody.get("receiverAccountNumber");

		// 사용자 계좌 확인
		if (!checkAccount(accountId, tokenUserId)) {
			System.out.println("계좌 회원 일치하지 않음");
			return null;
		}

		// 계좌 잔고 확인 후 송금
		int balance = getAccountBalance(accountId);
		int newBalance = checkAccountBalance(balance, amount);
		if (newBalance != -1) {

			accountUpdate(accountId, newBalance);
			transactionAdd(accountId, "송금", amount, newBalance);

			// 응답
			Map<String, Object> response = new HashMap<>();
			response.put("balance", newBalance);

			return ResponseEntity.ok(response);
		} else {
			System.out.println("잔고 부족");
			return null;
		}

	}

	// 계좌 결제
	@RequestMapping(value = "/accountPay", method = RequestMethod.POST)
	public ResponseEntity<?> accountPay(@RequestBody Map<String, Object> requestBody, HttpServletRequest request)
			throws Exception {
		// 토큰에서 추출한 user id
		int tokenUserId = (int) request.getAttribute("userId");
		int accountId = (int) requestBody.get("accountId");
		int price = (int) requestBody.get("price");

		// 사용자 계좌 확인
		if (!checkAccount(accountId, tokenUserId)) {
			System.out.println("계좌 회원 일치하지 않음");
			return null;
		}

		// 계좌 잔고 확인 후 송금
		int balance = getAccountBalance(accountId);
		int newBalance = checkAccountBalance(balance, price);
		if (newBalance != -1) {

			accountUpdate(accountId, newBalance);
			transactionAdd(accountId, "결제", price, newBalance);

			// 응답
			Map<String, Object> response = new HashMap<>();
			response.put("accountId", accountId);
			response.put("balance", newBalance);

			return ResponseEntity.ok(response);
		} else {
			System.out.println("잔고 부족");
			return null;
		}

	}

	// 주식 구매
	@RequestMapping(value = "/stockPurchase", method = RequestMethod.POST)
	public ResponseEntity<?> stockPurchase(@RequestBody Map<String, Object> requestBody, HttpServletRequest request)
			throws Exception {
		// 토큰에서 추출한 user id
		int tokenUserId = (int) request.getAttribute("userId");
		int stockId = (int) requestBody.get("stockId");
		int accountId = (int) requestBody.get("accountId");
		int amount = (int) requestBody.get("amount");
		int price = (int) requestBody.get("price");

		// 사용자 계좌 확인
		if (!checkAccount(accountId, tokenUserId)) {
			System.out.println("계좌 회원 일치하지 않음");
			return null;
		}

		// 계좌 잔고 확인 후 송금
		int balance = getAccountBalance(accountId);
		int sumPrice = price*amount;
		int newBalance = checkAccountBalance(balance, sumPrice);
		if (newBalance != -1) {

			accountUpdate(accountId, newBalance);
			transactionAdd(accountId, "주식 구매", sumPrice, newBalance);

			// 응답
			Map<String, Object> response = new HashMap<>();
			response.put("stockId", stockId);
			response.put("amount", amount);

			return ResponseEntity.ok(response);
		} else {
			System.out.println("잔고 부족");
			return null;
		}
	}
	
	/*
	// 주식 판매
	@RequestMapping(value = "/stockSale", method = RequestMethod.POST)
	public ResponseEntity<?> stockSale(@RequestBody Map<String, Object> requestBody, HttpServletRequest request)
			throws Exception {

	}
	*/

	// 트랜재션 추가
	public void transactionAdd(int accountId, String type, int amount, int newBalance) throws Exception {
		TransactionVO transactionVO = new TransactionVO();
		transactionVO.setAccount_id(accountId);
		transactionVO.setType(type);
		transactionVO.setAmount(amount);
		transactionVO.setBalance(newBalance);

		accountService.insertTransaction(transactionVO);
	}

	// 계좌 업데이트
	public void accountUpdate(int accountId, int newBalance) throws Exception {
		AccountVO accountVO = accountService.readAccount(accountId);
		accountVO.setBalance(newBalance);
		accountService.updateAccount(accountVO);
	}

	// 계좌 잔고 반환
	public int getAccountBalance(int accountId) throws Exception {

		// accountId의 계좌 잔고
		int accountBalance = (accountService.readAccount(accountId)).getBalance();

		return accountBalance;
	}

	// 계좌 잔고 확인
	public int checkAccountBalance(int accountBalance, int amount) throws Exception {

		if (accountBalance >= amount)
			return accountBalance - amount;

		else
			return -1;
	}

	// 계좌 소유자 확인
	public boolean checkAccount(int accountId, int tokenUserId) throws Exception {
		// accountId의 소유자 id
		int accountUserId = (accountService.readAccount(accountId)).getUserId();

		if (accountUserId == tokenUserId) {
			return true;
		} else
			return false;
	}

}
