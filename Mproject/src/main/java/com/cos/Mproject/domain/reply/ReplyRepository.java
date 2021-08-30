package com.cos.Mproject.domain.reply;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{

	
	@Query(value = "select * from reply where userId = :principalId",nativeQuery = true)
	Page<Reply> replyALL(int principalId,Pageable pageable);
}
