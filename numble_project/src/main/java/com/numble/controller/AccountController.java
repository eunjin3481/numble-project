package com.numble.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.numble.domain.AccountVO;
import com.numble.service.AccountService;

@RestController
@RequestMapping(value="/accounts")
public class AccountController {
	
	
	@Autowired(required=true)
	private AccountService accountService;
	
	// 계좌 등록 api
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> registrationAccount(@RequestBody Map<String, Object> requestBody, HttpServletRequest request) throws Exception{
		
		AccountVO newAccount = new AccountVO();
		
		// 계좌 정보
		int userId = (int) request.getAttribute("userId");
		String bank = (String)requestBody.get("bank");
		String bankNumber = (String)requestBody.get("bankNumber");
		Long balance = Long.valueOf((int) requestBody.get("balance"));
		
		// 계좌 DB에 저장
		newAccount.setUserId(userId);
		newAccount.setBank(bank);
		newAccount.setAccount_number(bankNumber);
		newAccount.setBalance(balance);
		
		accountService.addAccount(newAccount);
		
		// 응답
		Map<String, Object> response = new HashMap<>();
		response.put("bank", bank);
		response.put("accountNumber", bankNumber);
		response.put("balance", balance);
		
		return ResponseEntity.ok(response);
		
	}
	
	// 계좌 삭제 api
	@RequestMapping(method=RequestMethod.DELETE)
	public String deleteAccount(@RequestParam("accountId") int accountId,  HttpServletRequest request) throws Exception{
		
		// 토큰에서 추출한 user id
		int tokenUserId = (int) request.getAttribute("userId");
		
		// accountId의 소유자 id
		int accountUserId = (accountService.readAccount(accountId)).getUserId();
		
		// 토큰의 user와 account의 소유자가 같을 경우 계좌 삭제
		if(tokenUserId == accountUserId) {
			System.out.println("true");
			accountService.deleteAccount(accountId);
		}
		
		return "200 OK";
	}
	
	
	// 계좌 목록 조회
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ResponseEntity<?> listAccount() throws Exception{
		List<AccountVO> accountList = accountService.readAccountList();
		
		// 응답
		Map<String, Object> response = new HashMap<>();
		response.put("accounts", accountList);
		
		return ResponseEntity.ok(response);
	}
	
	/*
	// 계좌 상세 조회
	@RequestMapping(value="/{accountId}", method=RequestMethod.GET)
	public ResponseEntity<?> detailsAccount(){
		
	}
	*/
}
