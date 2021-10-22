package com.poc.AuthService.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@NoArgsConstructor @AllArgsConstructor @Data
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator = "native")
	private Long id;
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@NotEmpty
	@Email
	private String emailId;
	private String city;
	private String state;
	private String country;
	private Date userCreatedTime;
	@NotEmpty
	@Size(min=7, max=15,message="Username should have atleast 7 characters and maximum 10 characters.") 
	@Pattern(regexp = "^[a-zA-Z]+[!@#$%*]?[0-9]+$", message="Username should starts with characters and end with numbers")
	private String userName;
	//@Column(columnDefinition="TEXT")
	@NotEmpty
	@Size(min=8)
	//@Pattern(regexp = "^[A-Z]{1,2}[a-z]+[%+_!~-]{1,2}[0-9]+$",message="Invalid Password, Check Password Policy")
	private String password;
	private Date loginTime;
	private Date logOffTime;
	@ManyToMany(fetch = FetchType.EAGER,cascade= {CascadeType.PERSIST})
	private Collection<Role> roles= new ArrayList<>();
	@Transient
	private Collection<RoleToUser> rolesToUser;
	
	
	

	}
