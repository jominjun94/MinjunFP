package com.cos.Mproject.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.cos.Mproject.handler.CustomValidationApiException;





@Component
@Aspect
public class ValidationAdvice {

	
	
	// before 함수 전, after 함수 후, around 함수 전후 
	@Around("execution(* com.cos.Mproject.web.api.*Controller.*(..))")
	public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		// 함수보다 먼저 실행되고 return proceedingJoinPoint.proceed(); 끝나면 함수가 실행됩니다
		
		Object[] args = proceedingJoinPoint.getArgs();
			for(Object arg : args) { // 함수의 매개변수를 확인한다
				
				
					
				
				
				
				if(arg instanceof BindingResult) { //함수의 매개변수중 BindingResult 있다면~
					BindingResult bindingResult = (BindingResult)arg;
					
					if(bindingResult.hasErrors()) { //에러가 나타나면~
						Map<String, String> errorMap = new HashMap<String, String>(); 
						
						for(FieldError error : bindingResult.getFieldErrors()) { 
							errorMap.put(error.getField(), error.getDefaultMessage());
					}

					
						throw new CustomValidationApiException("유효성실패",errorMap);
					}
				}
				
			}
		
		
			
			return proceedingJoinPoint.proceed();
	}
	
}
