package com.poc.AuthService.advice;

import java.util.Date;
import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  @NoArgsConstructor @AllArgsConstructor
public class ErrorDetails {

	private Date timeStamp;
	private String errorMessage;
	private HttpStatus errorCode;
	private Map<String, String> errors;
	
	public ErrorDetails(Date timeStamp, String errorMessage, HttpStatus errorCode) {
		this.timeStamp = timeStamp;
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}
	
	
	
}
