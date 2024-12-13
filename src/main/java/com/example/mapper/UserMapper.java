package com.example.mapper;

import org.springframework.stereotype.Component;

import com.example.entity.User;

import com.example.requestDto.UserRequest;
import com.example.responseDto.UserResponse;
@Component
public class UserMapper {
	/*private final UserRequest userRequest;
	private final UserResponse userResponse;
	public UserMapper(UserRequest userRequest, UserResponse userResponse) {
		super();
		this.userRequest = userRequest;
		this.userResponse = userResponse;
	}*/
	public User mapToUser(UserRequest request)
	{
		User user=new User();
		user.setUsername(request.getUsername());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		user.setPhoneNumber(request.getPhoneNumber());
		user.setRole(request.getRole());
		return user;
	}
	public UserResponse mapToUserResponse(User user)
	{
		UserResponse response=new UserResponse();
		response.setUserId(user.getUserId());
		response.setUsername(user.getUsername());
		response.setEmail(user.getEmail());
		response.setRole(user.getRole());
        return response;
		
	}
	

}
