package com.numble.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.numble.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor{
	private static final String HEADER_AUTHORIZATION = "Authorization";
    
    @Autowired
    private JwtService jwtService;
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	
    	String token = request.getHeader(HEADER_AUTHORIZATION);
    	System.out.println(token);
        if (token != null) {
            try {
                Claims claims = jwtService.parseToken(token);

                // 토큰의 만료 여부 검사
                if (jwtService.isTokenExpired(claims)) {
                    // 토큰이 만료된 경우
                    // 처리할 로직 추가
                	
                	return false;
                }

                // 토큰에서 ID 추출
                String userId = jwtService.extractUserId(claims);
                // 추출된 ID를 HttpServletRequest에 추가
                request.setAttribute("userId", Integer.parseInt(userId));
                
                System.out.println(userId);

                return true; // 유효한 토큰이며 ID 추출이 성공한 경우 요청 처리를 진행합니다.

            } catch (ExpiredJwtException e) {
            	System.out.println("close");
                // 토큰이 만료된 경우
                // 처리할 로직 추가
            	return false;
            }
        }
        
        // 유효한 토큰이 없는 경우 또는 토큰 검증에 실패한 경우
        // 처리할 로직 추가
        return false;
    }
    


}
