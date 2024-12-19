package com.example.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.entity.User;
import com.example.repository.UserRepository;

@Component
	public class AuthUtil {
		
		private final UserRepository userRepository;
		@Autowired
		public AuthUtil(UserRepository userRepository) {
			this.userRepository = userRepository;
		}
		
		public Authentication getAuthentication() {
			return SecurityContextHolder.getContext().getAuthentication(); // to access the authentication object we are using SecurityContextHolder class.         
			// getContext -> static method.
		}
		
		public String getCurrentUsername() {
			return getAuthentication().getName();
		}
		
		public User getCurrentUser() {
			return userRepository.findByEmail(getCurrentUsername())
					.orElseThrow(() -> new UsernameNotFoundException("Failed to get current User"));

		}

	}

