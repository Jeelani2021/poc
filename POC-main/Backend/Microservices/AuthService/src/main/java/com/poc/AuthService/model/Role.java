package com.poc.AuthService.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @NoArgsConstructor @AllArgsConstructor @Data
public class Role {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "native") 
	private Long id;
	private String name;
	
	
}
