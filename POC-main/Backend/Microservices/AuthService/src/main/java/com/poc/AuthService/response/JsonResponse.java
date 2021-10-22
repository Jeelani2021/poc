package com.poc.AuthService.response;

import java.util.HashMap;
import java.util.Map;

import com.poc.AuthService.model.User;

public class JsonResponse {

	public static Map<String,Object> getResponseDetails(User user) {
		
		Map<String,Object> responseDetails= new HashMap<String,Object>();
		responseDetails.put("firstName", user.getFirstName());
		responseDetails.put("lastName", user.getLastName());
		responseDetails.put("emailId", user.getEmailId());
		responseDetails.put("userName", user.getUserName());
		responseDetails.put("roles", user.getRoles());
		
		return responseDetails;
	}
	
	public static Map<String,String> successMessage(String message){
		
		Map<String,String> responseDetails= new HashMap<String,String>();
		responseDetails.put("message", message);
		return responseDetails;
	}
}
