package com.numble.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.numble.repository.UserRepository;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Value("${jwt.secret}")
    private String SECRET_KEY;
    private static final long ACCESS_TOKEN_EXPIRATION = 120 * 60 * 1000; // 120분
    private static final long REFRESH_TOKEN_EXPIRATION = 7 * 24 * 60 * 60 * 1000; // 7일   
    
    // Access Token 발급
    public String generateAccessToken(String userId) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + ACCESS_TOKEN_EXPIRATION);

        
        return Jwts.builder()
                .setSubject(userId) 
                .setExpiration(expiration)  // 만료시간
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  // 암호 알고리즘
                .compact();
    }
    
    // Refresh Token 발급
    public String generateRefreshToken(String userId) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + REFRESH_TOKEN_EXPIRATION);

        return Jwts.builder()
                .setSubject(userId)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
    
    // Token 파싱
    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
    
    // Token 유효성 검사
    public boolean isTokenExpired(Claims claims) {
        Date expirationDate = claims.getExpiration();
        return expirationDate.before(new Date());
    }
    
    // Token에서 user id 추출
    public String extractUserId(Claims claims) {
        return claims.getSubject();
    }
    
    
    // Token 재발급
    public String reissueToken(String refreshToken) throws NumberFormatException, Exception {
    	
    	if (refreshToken != null) {
    		try {
                Claims claims = parseToken(refreshToken);

                // 토큰의 만료 여부 검사
                if (isTokenExpired(claims)) {
                	return null;
                }

                // 토큰에서 ID 추출
                String userId = extractUserId(claims);

                System.out.println(userId);
                
                // refresh 토큰이 DB에 저장되어 있는 회원인지 확인
                if(userRepository.findById(Integer.parseInt(userId))) {
                	// access Token 재발급
                    String newAccessToken = generateAccessToken(userId);


                    return newAccessToken; 
                }
                else {
                	return null;
                }
                
                

            } catch (ExpiredJwtException e) {
            	System.out.println("Token 만료");
            	return null;
            }
    	}
    	return null;
    	
    }

}
