package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.User;
import com.example.requestDto.UserRequest;
import com.example.responseDto.UserResponse;
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
	
	
	@PostMapping("/save-user")
public ResponseEntity<ResponseStructure<UserResponse>> saveUser(@RequestBody UserRequest userRequest){
		
	UserResponse userResponse = userService.savesUser(userRequest);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ResponseStructure.create(HttpStatus.CREATED.value(), "User Created", userResponse));
		
	}
	@GetMapping("fetch-user")
	public ResponseEntity<ResponseStructure<UserResponse>> fetchUser(@RequestParam("user_id") int userId)
	{
	UserResponse userResponse=userService.findUser(userId);
	return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseStructure.create(HttpStatus.OK.value(),"Image is Found", userResponse));
		
	}
	
	
	

}
