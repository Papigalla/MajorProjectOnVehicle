package com.example.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.security.UserDetailsImpl;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	private final UserRepository userRepository;

	public UserDetailServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username)
	            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
		return new UserDetailsImpl(user);
	}

}
