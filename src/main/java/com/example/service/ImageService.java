package com.example.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Image;
import com.example.entity.User;
import com.example.exception.FailedToUploadException;
import com.example.exception.ImageIsNotFoundById;
import com.example.repository.ImageRepository;
import com.example.repository.UserRepository;



@Service
public class ImageService {
	private final ImageRepository imageRepository;
	private final UserRepository userRepository;
	public ImageService(ImageRepository imageRepository, UserRepository userRepository) {
		super();
		this.imageRepository = imageRepository;
		this.userRepository = userRepository;
	}




	public void uploadUserProfilePicture(int userId, MultipartFile file) {
	Optional<User> optional=userRepository.findById(userId);
	if(optional.isPresent())
	{
	Image img=imageRepository.save(this.getImage(file));
	User user=optional.get();
	user.setProfilePicture(img);
	userRepository.save(user);
	}
	else
	{
		throw new FailedToUploadException("failed to upload ");
	}
		
	}

	public Image getImage(MultipartFile file) {
		try {
			Image img=new Image();
			img.setContentType(file.getContentType());
			img.setImageBytes(file.getBytes());
			return img;
		}
		catch(IOException ex)
		{
			throw new FailedToUploadException("failed to get ");
		}
	}




	public Image fetching(int imageId) {
	Optional<Image> optional=imageRepository.findById(imageId);
	if(optional.isPresent())
	{
	Image image	=optional.get();
	return image;
		
	}
	else
	{
		throw new ImageIsNotFoundById("image is not there in database");
	}
	




	



	}

}