package com.poc.AuthService.exception;

public class UsernameFoundException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;
    private String errorMessage;
    
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public UsernameFoundException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	
	public UsernameFoundException() {
		
	}
	
}
