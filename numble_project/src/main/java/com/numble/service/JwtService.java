package com.numble.service;

import io.jsonwebtoken.Claims;

public interface JwtService {
	public String generateAccessToken(String userId);
	public String generateRefreshToken(String userId);
	public Claims parseToken(String token);
	public boolean isTokenExpired(Claims claims);
	public String extractUserId(Claims claims);
	public String reissueToken(String refreshToken) throws NumberFormatException, Exception;
}
