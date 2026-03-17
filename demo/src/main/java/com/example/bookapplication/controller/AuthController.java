package com.example.bookapplication.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	        String token = jwtUtil.generateToken(request.getUsername());

	        return ResponseEntity.ok(Map.of(
	            "token", token,
	            "username", request.getUsername()
	        ));
	    }

	    return ResponseEntity.status(401).body("Invalid credentials");
	}

}
