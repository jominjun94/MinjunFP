package com.cos.Mproject.domain.like;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cos.Mproject.domain.image.Image;
import com.cos.Mproject.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
		uniqueConstraints = {
				@UniqueConstraint(
						name = "like_uk",
						columnNames = {"imageId","userId"}
					)
				}
	)
public class Likes {

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	

	@JsonIgnoreProperties({"user"})
	@JoinColumn(name = "imageId")
	@ManyToOne
	private Image imge;

	
	@JsonIgnoreProperties({"images"})
	@JoinColumn(name = "userId")
	@ManyToOne
	private User user;
	
	
	
	private LocalDateTime createDate;
	
	
	@PrePersist
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
