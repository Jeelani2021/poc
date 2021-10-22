package com.poc.AuthService.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.AuthService.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
	User findByUserName(String userName);
	
}
