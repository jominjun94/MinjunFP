package com.cos.Mproject.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.Mproject.config.auth.PrincipalDetails;
import com.cos.Mproject.dto.PhotoDto;
import com.cos.Mproject.photo.Photo;
import com.cos.Mproject.photo.PhotoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhotoService {

	private final PhotoRepository PhotoRepository;
	
	@Value("${file.path}")
	private String uploadFolder = "C:/Users/82107/Desktop/spring/upload2/";
	
	private final PhotoRepository photoRepository;

	
	@Transactional
	public void 사진업로드(PhotoDto photoDto, PrincipalDetails principalDetails) {
		
		photoRepository.mPhotodelete(principalDetails.getUser().getId());
		
		UUID uuid = UUID.randomUUID();
		String imageFileName = uuid+"-"+photoDto.getFile().getOriginalFilename();
		
		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		
		
		
		
		// 통신 예외
		try {
			Files.write(imageFilePath, photoDto.getFile().getBytes());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		photoDto.toEntity(imageFileName, principalDetails.getUser());
		
		
		
		photoRepository.save(photoDto.toEntity(imageFileName, principalDetails.getUser()));
		
	
	}

	public Photo 회원사진가져오기(int id) {
		PhotoRepository.mPhoto(id);
		return PhotoRepository.mPhoto(id);
	}
	
}
