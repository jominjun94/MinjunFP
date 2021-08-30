package com.cos.Mproject.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.Mproject.config.auth.PrincipalDetails;
import com.cos.Mproject.dto.PhotoDto;
import com.cos.Mproject.handler.CustomValidationException;
import com.cos.Mproject.photo.PhotoRepository;
import com.cos.Mproject.service.PhotoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PhotoController {

	private final PhotoService photoService;
	
	
	
	@GetMapping("/photo/upload")
	public String photo() {
		return "user/upload";
	}
	
	
	@PostMapping("/photo")
	public String photoUpload(PhotoDto photoDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		if(photoDto.getFile().isEmpty()) {
			throw new CustomValidationException("이미지가 없습니다! 이미지를 꼭 첨부해주세용!", null);
		}
		photoService.사진업로드(photoDto,principalDetails);
		int getId = principalDetails.getUser().getId();
		return "redirect:user/"+ getId;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	

	