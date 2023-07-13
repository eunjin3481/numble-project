package com.numble.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.numble.domain.UserVO;
import com.numble.service.UserService;

@RestController
@RequestMapping(value="/users")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired(required=true)
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.POST)
	public UserVO createUser(@RequestBody Map<String, String> map) throws Exception{
		
		UserVO newUser = new UserVO();
		newUser.setName(map.get("name"));
		newUser.setBirthDay(map.get("birthDay"));
		
		userService.addUser(newUser);
		
		return newUser;
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public String deleteUser() throws Exception{
		
		
		
		return "200 OK";
	}

	
	
	
	
	
	
	
	
}
