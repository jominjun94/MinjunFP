package com.cos.Mproject.photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PhotoRepository extends JpaRepository<Photo, Integer>{

	
	@Query(value = "select * from photo where userId = :id", nativeQuery = true)
	Photo mPhoto(int id);
	
	@Modifying
	@Query(value = "delete from photo where userId = :id", nativeQuery = true)
	void mPhotodelete(int id);

}
