package com.poc.AuthService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.AuthService.model.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{
	Role findByName(String name);

}
