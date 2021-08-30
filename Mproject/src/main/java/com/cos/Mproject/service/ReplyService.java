package com.cos.Mproject.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.Mproject.domain.board.Board;
import com.cos.Mproject.domain.reply.Reply;
import com.cos.Mproject.domain.reply.ReplyRepository;
import com.cos.Mproject.domain.user.User;
import com.cos.Mproject.handler.CustomApiException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyService {

	
	public final ReplyRepository replyRepository;	
	
	@Transactional
	public void 글작성(String content, String title, User adminuser, User user) {
		// TODO Auto-generated method stub
		
		Reply reply= new Reply();
		reply.setContent(content);
		reply.setTitle(title);
		reply.setUser(user);
		reply.setAdminuser(adminuser);
		
		replyRepository.save(reply);
		
	}
	
	
	
	@Transactional(readOnly = true)
	public Page<Reply> 글목록(Pageable pageable) {
		
		return replyRepository.findAll(pageable);
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	public Reply 상세보기(int id) {
		return  replyRepository.findById(id)
				.orElseThrow(()->{
			
			return new CustomApiException("목록 조회 실패");
		
		});
		
		
	}



	public void 글삭제(int id) {
	
		replyRepository.deleteById(id);
		
	}


	
	@Transactional(readOnly = true)
	public Page<Reply> 답변확인회원(int id,Pageable pageable) {
	
		 Page<Reply> replys = replyRepository.replyALL(id,pageable);
		
		 return replys;
	}

	
	
	
	
	
	
	

}
