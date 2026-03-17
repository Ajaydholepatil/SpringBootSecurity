package com.example.bookapplication.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Override
	public UserDetails loadUserByUsername(String username) {
		return new User("admin", "$2a$10$Dow1zHn7yRzWnFh3Yy6f7eJ8z0f9Hk1", // bcrypt password
				List.of());
	}

}
