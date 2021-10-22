package com.poc.AuthService.advice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.poc.AuthService.exception.ResourceNotFoundException;
import com.poc.AuthService.exception.UsernameFoundException;

@ControllerAdvice 
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception){
		System.out.println("ResourceNotFoundException");
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UsernameFoundException.class)
	public ResponseEntity<?> handleUsernameFoundException(UsernameFoundException exception){
		System.out.println("UsernameFoundException");
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), HttpStatus.FOUND);
		return new ResponseEntity<>(errorDetails, HttpStatus.FOUND);
	}


	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		 
		 Map<String, String> errors = new HashMap<>();
		 ex.getBindingResult().getAllErrors().forEach(error -> {
			 errors.put( ( (FieldError)error).getField() , error.getDefaultMessage());
		 });
		 ErrorDetails errorDetails = new ErrorDetails(new Date(),ErrorMessage.VALIDATION_ERROR,HttpStatus.BAD_REQUEST,errors);
		 return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
		        
	}
	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<?> handleException(Exception exception){
//		
//		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//	
	
	
}
