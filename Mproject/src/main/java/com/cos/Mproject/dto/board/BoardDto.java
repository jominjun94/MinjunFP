package com.cos.Mproject.dto.board;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class BoardDto {

	
	@NotBlank
	private String title;
	@NotBlank
	private String content;
	
}
