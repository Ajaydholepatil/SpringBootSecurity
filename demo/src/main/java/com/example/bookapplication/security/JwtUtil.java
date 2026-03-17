package com.example.bookapplication.security;

import java.util.Date;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
@Component
public class JwtUtil {
	
	private static final long JWT_EXPIRATION = 1000 * 60 * 1; // 1 minute

	private final Key key = Keys.hmacShaKeyFor(
		    "mysecretkeymysecretkeymysecretkey123".getBytes()
		);
	public String generateToken(String username) {
	    return Jwts.builder()
	            .setSubject(username)
	            .setIssuer("book-app")
	            .setIssuedAt(new Date())
	            .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
	            .signWith(key)   
	            .compact();
	}

	public String extractUsername(String token) {
		return getClaims(token).getSubject();
	}

	public boolean validateToken(String token) {
		return getClaims(token).getExpiration().after(new Date());
	}

	private Claims getClaims(String token) {
	    return Jwts.parserBuilder()
	            .setSigningKey(key)
	            .build()
	            .parseClaimsJws(token)
	            .getBody();
	}

}
