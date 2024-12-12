package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.User;
import com.example.service.UserService;
import com.example.util.ResponseStructure;
import com.example.util.SimpleResponseStructure;

@RestController
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<User>> registerUser(@RequestBody User user){
		
		user = userService.registerUser(user);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ResponseStructure.create(HttpStatus.CREATED.value(), "User Created", user));
		
	}
	
	@PostMapping("/upload-profile")
	public ResponseEntity<SimpleResponseStructure> uploadProfile(@RequestParam ("userId") int userId,
				@RequestParam("file") MultipartFile file ){
		
		userService.uploadProfile(userId,file);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(SimpleResponseStructure.create(HttpStatus.CREATED.value(), "Profile Picture Updated"));
	}

}
