package com.cos.Mproject.web.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cos.Mproject.config.auth.PrincipalDetails;
import com.cos.Mproject.domain.board.Board;
import com.cos.Mproject.domain.user.User;
import com.cos.Mproject.dto.board.BoardDto;
import com.cos.Mproject.handler.CMRespDTO;
import com.cos.Mproject.handler.CustomValidationApiException;
import com.cos.Mproject.service.BoardService;
import com.cos.Mproject.service.ReplyService;
import com.cos.Mproject.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AdminApiController {
	private final BoardService boardService;
	private final UserService userService;
	private final ReplyService replyService;
	
	
	// reply 에서  write button 누를시 
	@PostMapping("/admin/api/board/save/{userId}")
	public ResponseEntity<?> boardWrite(@PathVariable String userId,@Valid @RequestBody BoardDto boardDto,BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails details){
		
		User user = userService.회원찾기(userId);

		/* aop 가 대신 하게 됩니다
		 * 
		 * if(bindingResult.hasErrors()) { //에러가 나타나면~ Map<String, String> errorMap =
		 * new HashMap<String, String>(); // map을 만들어서~
		 * 
		 * for(FieldError error : bindingResult.getFieldErrors()) { //발생 에러를 에러에 담아~
		 * errorMap.put(error.getField(), error.getDefaultMessage()); }
		 * 
		 * 
		 * throw new CustomValidationApiException("유효성실패",errorMap); }
		 */
		 
		
		
		 replyService.글작성(boardDto.getContent(),boardDto.getTitle(),details.getUser(),user);
		
		return new ResponseEntity<>(new CMRespDTO<>(1,"글 작성 성공",null), HttpStatus.OK);
	}
	

	//글삭제 user가 달아 놓은 글을 삭제
	@DeleteMapping("/admin/api/board/{id}")
	public ResponseEntity<?> boardDelete(@PathVariable int id){
		
		boardService.글삭제(id);
		return new ResponseEntity<>(new CMRespDTO<>(1,"글 삭제 성공",null), HttpStatus.OK);
	}
	
	
	//reply 작성하는곳으로 이동 방법 여기서 > ajax 로 회원 정보를 담아서 -> 다시 controller 에서 값 id로 값을 model에 담아서 view로 이동 
	// 1. 이동 클릭 -> ajax -> rest controller -> ajax -> controller -> view
	
		@PostMapping("/admin/api/board/reply/{id}")
		public ResponseEntity<?>  reply(@PathVariable String id){
			
			User user = userService.회원찾기(id);
	
		
			
			return new ResponseEntity<>(new CMRespDTO<>(1,"reply성공",user), HttpStatus.OK);
			
		}
	
	
	
		//reply admin이 들아놓은  삭제
		@DeleteMapping("/admin/api/board/reply/{id}")
		public ResponseEntity<?> replyDelete(@PathVariable int id){
			
			replyService.글삭제(id);
			return new ResponseEntity<>(new CMRespDTO<>(1,"reply 삭제 성공",null), HttpStatus.OK);
		}
		
	
	
	
}
