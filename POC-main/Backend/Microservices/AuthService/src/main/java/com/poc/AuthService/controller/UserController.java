package com.poc.AuthService.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.poc.AuthService.model.User;
import com.poc.AuthService.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
	
    private final UserService userService;
	@PostMapping("/user/save")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createUser(@Valid @RequestBody User user) throws Exception
	{
		//System.out.println(user.getRoles());
		Map<String,String> res=userService.saveUser(user);
		userService.addRoleToUser(user.getUserName(), user.getRolesToUser());
		return new ResponseEntity<Object>(res, HttpStatus.CREATED);
	}
	
	@GetMapping("/users/{userName}") 
	public ResponseEntity<?> getUserName(@PathVariable String userName) throws Exception
	{
		System.out.println(userName);
		System.out.println(userService.getUser(userName));
		return ResponseEntity.ok().body(userService.getUser(userName));
	}
	


}
