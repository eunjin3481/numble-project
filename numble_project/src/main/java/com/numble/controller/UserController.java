package com.numble.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.numble.domain.User;
import com.numble.service.JwtService;
import com.numble.service.UserService;

@RestController
@RequestMapping(value="/users")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired(required=true)
	private UserService userService;
	
	// 회원가입 api
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody Map<String, String> map) throws Exception{
		
		User newUser = new User();
		
		
		String userId = Integer.toString(5);
		
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
	
	// 회원 탈퇴 api
	@RequestMapping(method = RequestMethod.DELETE)
	public String deleteUser(HttpServletRequest request) throws Exception{
		int userId = (int) request.getAttribute("userId");
		userService.deleteUser(userId);
		
		
		return "200 OK";
	}
	
	// 토큰 재발급 api
	@RequestMapping(value="/reissue",method = RequestMethod.POST)
	public ResponseEntity<?> deleteUser(@RequestBody Map<String, String> map) throws Exception{
		System.out.println(map.get("refreshToken"));
		String newAccessToken = jwtService.reissueToken(map.get("refreshToken"));
		
		// 클라이언트에게 응답
        Map<String, String> response = new HashMap<>();
        response.put("accessToken", newAccessToken);
		return ResponseEntity.ok(response);
	}

	
	
	
	
	
	
	
	
}
