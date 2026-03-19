package com.example.bookapplication.security;

import java.util.Date;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.springframework.beans.factory.annotation.Value;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.access.expiration}")
	private long accessExpiration;

	@Value("${jwt.refresh.expiration}")
	private long refreshExpiration;

	private Key getSigningKey() {
		return Keys.hmacShaKeyFor(secret.getBytes());
	}

	public String generateAccessToken(String username) {
	    return Jwts.builder()
	            .setSubject(username)
	            .setIssuedAt(new Date())
	            .setExpiration(new Date(System.currentTimeMillis() + accessExpiration))
	            .signWith(getSigningKey())
	            .compact();
	}

	public String generateRefreshToken(String username) {
	    return Jwts.builder()
	            .setSubject(username)
	            .setIssuedAt(new Date())
	            .setExpiration(new Date(System.currentTimeMillis() + refreshExpiration))
	            .signWith(getSigningKey())
	            .compact();
	}

	public String extractUsername(String token) {
		return getClaims(token).getSubject();
	}

	public boolean validateToken(String token) {
		return getClaims(token).getExpiration().after(new Date());
	}

	private Claims getClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
	}

}
