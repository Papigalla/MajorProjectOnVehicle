package com.example.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Image;
import com.example.entity.User;
import com.example.entity.Vehicle;
import com.example.exception.FailedToUploadException;
import com.example.exception.ImageIsNotFoundById;
import com.example.exception.UserNotFoundException;
import com.example.repository.ImageRepository;
import com.example.repository.UserRepository;
import com.example.repository.VehicleRepository;





@Service
public class ImageService {
	private final ImageRepository imageRepository;
	private final UserRepository userRepository;
	private final VehicleRepository vehicleRepository;
	
	
	public ImageService(ImageRepository imageRepository, UserRepository userRepository,
			VehicleRepository vehicleRepository) {
		super();
		this.imageRepository = imageRepository;
		this.userRepository = userRepository;
		this.vehicleRepository = vehicleRepository;
	}

	public void uploadUserProfilePicture(int userId, MultipartFile file) {
	Optional<User> optional=userRepository.findById(userId);
	if(optional.isPresent())
	{
	User user=optional.get();
	if(user.getProfilePicture()!=null)
	{
	Image image=user.getProfilePicture();
	this.uploadUserProfile(file, user);
	imageRepository.delete(image);
	}
	this.uploadUserProfile(file,user);
	}
	else
	{
		throw new FailedToUploadException("failed to upload ");
	}
	
		
	}

	private void uploadUserProfile(MultipartFile file, User user) {
		Image image=imageRepository.save(this.getImage(file));
		user.setProfilePicture(image);
		userRepository.save(user);
		
	}




	private Image getImage(MultipartFile file) {
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
	public void uploadVehicleImages(int vehicleId, List<MultipartFile> files) {
		Vehicle vehicle = vehicleRepository.findById(vehicleId)
				.orElseThrow(() -> new UserNotFoundException("Vehicle not found with ID: " + vehicleId));

		List<Image> images = new ArrayList<>();

		for (MultipartFile file : files) {
			images.add(this.getImage(file));
		}

		images = imageRepository.saveAll(images);
		vehicle.setImages(images);
		vehicleRepository.save(vehicle);
	}	
	
	
	}


