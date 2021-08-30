package com.cos.Mproject.dto;

import org.springframework.web.multipart.MultipartFile;


import com.cos.Mproject.domain.user.User;
import com.cos.Mproject.photo.Photo;

import lombok.Data;

@Data
public class PhotoDto {
	
	private MultipartFile file;
	
	public Photo toEntity(String postImageUrl,User user) {
		return Photo.builder()
				.postImageUrl(postImageUrl)
				.user(user)
				.build();
	}

}
