package com.numble.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	public String createUserPost(@RequestBody UserVO newUser) throws Exception{
		userService.addUser(newUser);
		newUser.getName();
		return new UserVO(newUser.getName(), newUser.getName());
	}


	
	
	
	
	
	
	
	
}
