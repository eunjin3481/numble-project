package com.numble.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
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
import com.numble.service.JwtService;
import com.numble.service.UserService;

@RestController
@RequestMapping(value="/users")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	private JwtService jwtService = new JwtService();
	
	@Autowired(required=true)
	private UserService userService;
	
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody Map<String, String> map) throws Exception{
		
		UserVO newUser = new UserVO();
		
		
		String userId = Integer.toString(4);
		
		// Access Token 생성
        String accessToken = jwtService.generateAccessToken(userId);

        // Refresh Token 생성
        String refreshToken = jwtService.generateRefreshToken(userId);
        
        // 사용자 정보 DB에 저장
        newUser.setName(map.get("name"));
		newUser.setBirthDay(map.get("birthDay"));
		newUser.setToken(refreshToken);
		
		userService.addUser(newUser);
        
        // 클라이언트에게 응답
        Map<String, String> response = new HashMap<>();
        response.put("id", userId);
        response.put("name", map.get("name"));
        response.put("access_token", accessToken);
        response.put("refresh_token", refreshToken);
		
		return ResponseEntity.ok(response);
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public String deleteUser() throws Exception{
		
		userService.deleteUser();
		
		
		return "200 OK";
	}

	
	
	
	
	
	
	
	
}
