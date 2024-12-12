package com.example.service;

import org.springframework.stereotype.Service;

import com.example.repository.ImageRepository;



@Service
public class ImageService {
	private final ImageRepository imageRepository;

	public ImageService(ImageRepository imageRepository) {
		super();
		this.imageRepository = imageRepository;
	}
	

}
