package com.numble.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {

	private int id;
	private String bank;
	private String account_number;
	private int balance;
	@JsonProperty("userId")
	private int user_id;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return user_id;
	}
	public void setUserId(int user_id) {
		this.user_id = user_id;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "AccountVO [id=" + id + ", bank=" + bank + ", account_number=" + account_number + ", balance=" + balance
				+ "]";
	}
}
