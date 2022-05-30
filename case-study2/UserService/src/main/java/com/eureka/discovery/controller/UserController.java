package com.eureka.discovery.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eureka.discovery.configuration.AuthenticationRequest;
import com.eureka.discovery.configuration.AuthenticationResponse;
import com.eureka.discovery.configuration.JwtUtil;
import com.eureka.discovery.model.UserDetail;
import com.eureka.discovery.model.UserForm;
import com.eureka.discovery.service.MyUserDetailsService;
import com.eureka.discovery.service.UserService;

import io.swagger.annotations.ApiOperation;

//@CrossOrigin("*")
@Component
@RestController
@RequestMapping("/users")
public class UserController 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private MyUserDetailsService custService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	private String token = "";
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/greet")
	public String greet() {
		return "Hello";
	}
	
	@PostMapping("/auth")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request){
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException e) {
			System.out.println("wrong credential");
			return ResponseEntity.of(Optional.of("wrong username and password"));
		}
		final UserDetails userDetail = custService.loadUserByUsername(request.getUsername());
		final String jwt = jwtUtil.generateToken(userDetail);
		token = jwt;
		return ResponseEntity.ok(new AuthenticationResponse(token));
		
	}  
	
	
	@PostMapping("/createUser")
	public String createUser(@RequestBody UserForm userForm) {
		return userService.createUser(userForm);
	}
	
	
	@GetMapping("/getAllUser")
	public List<UserDetail> allUsers(){
		return userService.getAllUser();
	}
	

}