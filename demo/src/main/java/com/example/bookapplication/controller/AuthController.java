package com.example.bookapplication.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookapplication.security.JwtUtil;
import com.example.bookapplication.security.LoginRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest request) {

	    if ("admin".equals(request.getUsername()) &&
	        "password".equals(request.getPassword())) {

	        String accessToken = jwtUtil.generateAccessToken(request.getUsername());
	        String refreshToken = jwtUtil.generateRefreshToken(request.getUsername());

	        return ResponseEntity.ok(Map.of(
	            "accessToken", accessToken,
	            "refreshToken", refreshToken
	        ));
	    }

	    return ResponseEntity.status(401).body("Invalid credentials");
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestParam String refreshToken) {

	    try {
	        String username = jwtUtil.extractUsername(refreshToken);

	        if (jwtUtil.validateToken(refreshToken)) {

	            String newAccessToken = jwtUtil.generateAccessToken(username);

	            return ResponseEntity.ok(Map.of(
	                "accessToken", newAccessToken
	            ));
	        }

	    } catch (Exception e) {
	        return ResponseEntity.status(403).body("Invalid refresh token");
	    }

	    return ResponseEntity.status(403).body("Invalid refresh token");
	}

}
