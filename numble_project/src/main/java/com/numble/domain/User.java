package com.numble.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
	private int id;
	private String name;
	@JsonIgnore
	private String birthDay;
	@JsonIgnore
	private String token;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String toString() {
		return "";
	}
}
