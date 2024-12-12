package com.example.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Image;
import com.example.entity.User;
import com.example.exception.FailedToUploadException;
import com.example.exception.UserNotFoundException;
import com.example.repository.ImageRepository;
import com.example.repository.UserRepository;


@Service
public class UserService {
	private final UserRepository userRepository;
	private final ImageRepository imageRepository;

	public UserService(UserRepository userRepository,ImageRepository imageRepository) {
		super();
		this.userRepository = userRepository;
		this.imageRepository = imageRepository;
	}

	public User registerUser(User user) {
		
		return userRepository.save(user);
	}

	public void uploadProfile(int userId, MultipartFile file){
		
	Optional<User> optional=userRepository.findById(userId);
	if (optional.isPresent()) {
		Image image=getImage(file);
		image=imageRepository.save(image);
		
		User user=optional.get();
		user.setProfilePicture(image);
		userRepository.save(user);
	}else {
		
		throw new  UserNotFoundException("User not Found");
	}
		
//		try {
//			byte[] imageBytes = file.getBytes();
//			Image image=new Image();
//			image.setContentType(file.getContentType());
//			image.setImageBytes(imageBytes);
//			imageRepository.save(image);
//			
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
	}
	private Image getImage(MultipartFile file) {
		
		Image image=new Image();
		try {
			byte[] imageBytes = file.getBytes();
			image.setContentType(file.getContentType());
			image.setImageBytes(imageBytes);
			imageRepository.save(image);
			
		} catch (Exception e) {
			
			throw new FailedToUploadException("Failed to upload image");
		}
		return image;
	}
	


}
