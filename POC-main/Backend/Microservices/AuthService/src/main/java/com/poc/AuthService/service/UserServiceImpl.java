package com.poc.AuthService.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.poc.AuthService.exception.UsernameFoundException;
import com.poc.AuthService.model.Role;
import com.poc.AuthService.model.RoleToUser;
import com.poc.AuthService.model.User;
import com.poc.AuthService.repository.RoleRepo;
import com.poc.AuthService.repository.UserRepo;
import com.poc.AuthService.response.JsonResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @RequiredArgsConstructor @Slf4j @Transactional
public class UserServiceImpl implements UserService,UserDetailsService{
	
	final private UserRepo userRepo;
	final private RoleRepo roleRepo;
	final private PasswordEncoder passwordEncoder;
	
	
	@Override
	public Map<String,String> saveUser(User user) throws Exception{
		user.setUserCreatedTime(new Date());
		
		//User user = userRepo.findByUserName(user.getUserName());
		if(userRepo.findByUserName(user.getUserName()) != null) {
			
			log.error("User not found in the database");
			throw new UsernameFoundException(user.getUserName()+" is already present in the database");
		} 
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
		return JsonResponse.successMessage("User created successfully");
	}
	
	@Override
	public void addRoleToUser(String userName, Collection<RoleToUser> userRoles) throws Exception{
		
		
		User user=userRepo.findByUserName(userName);
		userRoles.forEach(role->{
			Role roleObject= roleRepo.findByName(role.getRoleName());
				user.getRoles().add(roleObject);
		});
	}
	
	@Override
	public Map<String,Object> getUser(String userName) throws Exception{
		
		return JsonResponse.getResponseDetails(userRepo.findByUserName(userName));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUserName(username);
		if(user == null) {
			log.error("User not found in the database");
			throw new UsernameNotFoundException(username);
		} else {
			log.info("User found in the database");
		} 
		
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role ->{
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
	}

	
	
}

