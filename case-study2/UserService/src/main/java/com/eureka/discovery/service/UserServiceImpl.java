package com.eureka.discovery.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eureka.discovery.exception.ResourceNotFoundException;
import com.eureka.discovery.model.Credentials;
import com.eureka.discovery.model.UserDetail;
import com.eureka.discovery.model.UserForm;
import com.eureka.discovery.repository.CredentialRepository;
import com.eureka.discovery.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDetail userDetail;
	
	@Autowired
	private Credentials credentials;
	
	@Autowired
	private CredentialRepository credentialRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailServiceImpl emailServiceImpl;
	
	String secret_key = "";
	
	@Override
	public String createUser(UserForm userForm) {
		try {
			if (userForm.getContact_no().length() != 10 && isNumeric(userForm.getContact_no())) {
				throw new ResourceNotFoundException("Please Check Your Number");
			}
			if (!isEmailValid(userForm.getEmail_address())) {
				throw new ResourceNotFoundException("Invalid Mail");
			}
			
			InternetAddress internetAddress = new InternetAddress(userForm.getEmail_address());
			internetAddress.validate();

		} catch (ResourceNotFoundException | AddressException  e) {
			return e.getMessage();
		}
//		adminDetails.setadminname(Arrays.stream(adminForm.getFull_name().split(" ")).collect(Collectors.toList()).get(0)+new Random().nextInt(10000));
		userDetail.setName(userForm.getFull_name());
//		AdminDetails.setAge(adminForm.getAge());
		userDetail.setContact(userForm.getContact_no());
		credentials.setUser_id(Arrays.stream(userForm.getFull_name().split(" ")).collect(Collectors.toList()).get(0)
				+ new Random().nextInt(10000));
		credentials.setUsername(
				"user" + Arrays.stream(userForm.getFull_name().split(" ")).collect(Collectors.toList()).get(0)
						+ new Random().nextInt(10000));
		userDetail.setEmailId(userForm.getEmail_address());
		String password = RandomStringUtils.randomAlphanumeric(new Random().nextInt(3) + 8);
		Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder();
		credentials.setPassword(encoder.encode(password));

		if (secret_key == "" ){
			userDetail.setRoles("USER");
		} else {
			userDetail.setRoles("ADMIN");
		}

		credentials.setRoles(userDetail.getRoles());
		userRepository.save(userDetail);
		credentialRepository.save(credentials);
		emailServiceImpl.sendSimpleEmail(userForm.getEmail_address(), "Dear " + userForm.getFull_name()
				+ ",\nThank you for registering on Railway application System\n\nWith Regards\nRailway Developer\nrailway.reservation.system@gmail.com",
				"Welcome to Railway Reservation System");
		emailServiceImpl.sendSimpleEmail(
				userForm.getEmail_address(), "User Created Successfully \n\n Your Credentials are here \n Username : "
						+ credentials.getUsername() + " \n Password : " + password,
				"Your user account has been Created");
		return "Your User Account has been created";

	}
	
	

	@Override
	public List<UserDetail> getAllUser() {
		
		return userRepository.findAll();
	}
	
	public Boolean isNumeric(String info) {
		try {
			Double.parseDouble(info);
		} catch (NumberFormatException nfe) {
			return false;
		}

		return true;
	}

	public Boolean isEmailValid(String email) {
		String regex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
}
