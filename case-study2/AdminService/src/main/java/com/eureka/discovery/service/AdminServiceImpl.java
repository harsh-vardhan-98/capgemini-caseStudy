package com.eureka.discovery.service;

import java.time.LocalDate;
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
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eureka.discovery.AdminServiceApplication;
import com.eureka.discovery.exception.ResourceNotFoundException;
import com.eureka.discovery.model.AdminDetails;
import com.eureka.discovery.model.AdminForm;
import com.eureka.discovery.model.Credentials;
//import com.eureka.discovery.model.Credentials;
import com.eureka.discovery.model.TrainDetails;
import com.eureka.discovery.repository.TrainRepository;
import com.eureka.discovery.repository.AdminRepository;
import com.eureka.discovery.repository.CredentialRepository;
//import com.eureka.discovery.repository.CredentialRepository;


@Service
public class AdminServiceImpl implements AdminService{
	
	public String secret_key = "railway";

	@Autowired 
	private TrainRepository trainRepository;

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private CredentialRepository credentialRepository;
	
	@Autowired
	private EmailServiceImpl emailService;
	
	@Autowired
	private AdminDetails adminDetails;
	
    @Autowired
    private Credentials credentials;

	// This method finds the all Trains
	public List<TrainDetails> getAllDetails() {
		List<TrainDetails> TrainDetails = new ArrayList<TrainDetails>();
		trainRepository.findAll().forEach(TrainDetails1 -> TrainDetails.add(TrainDetails1));
		return TrainDetails;
	}

	// This method add the new train
	public void addTrainDetails1(TrainDetails trainDetails) {
		trainRepository.save(trainDetails);
	}

	// This method update the details of previous Train
	public TrainDetails updateTrainDetails(int trainNo, TrainDetails trainDetails) {
		TrainDetails existingDetails =trainRepository.findByTrainNo(trainNo);
		existingDetails.setTrainDate(trainDetails.getTrainDate());
		existingDetails.setStartPoint(trainDetails.getStartPoint());
		existingDetails.setEndPoint(trainDetails.getEndPoint());
		existingDetails.setArrivalTime(trainDetails.getArrivalTime());
		existingDetails.setDeptTime(trainDetails.getDeptTime());
		existingDetails.setDuration(trainDetails.getDuration());
		existingDetails.setNoOfSeats(trainDetails.getNoOfSeats());
		existingDetails.setFirstClass(trainDetails.getFirstClass());
		existingDetails.setSecondClass(trainDetails.getSecondClass());
		existingDetails.setSleeperClass(trainDetails.getSleeperClass());
		return trainRepository.save(existingDetails);
	}

	// This method delete the Train by trainNo
	@Override
	public ResponseEntity<TrainDetails>deleteTrainDetails(int trainNo) {
		TrainDetails existingDetails =trainRepository.findByTrainNo(trainNo);
		trainRepository.delete(existingDetails);
		// fareRepo.deleteById(trainNo);
		return ResponseEntity.ok().build();
	}

	// This method finds the Train by train no
	@Override
	public TrainDetails findByTrainNo(int trainNo) {
		return trainRepository.findByTrainNo(trainNo);
	}

	// This method find the trains by startpoint, endpoint and trainDate
	@Override 
	public TrainDetails[] findByStartPointAndEndPointAndTrainDate(String startPoint, String endPoint,
			String trainDate) {
		return trainRepository.findByStartPointAndEndPointAndTrainDate(startPoint, endPoint, trainDate);

	}
	
	// This method add the admin details
	public void addAdmin(AdminDetails adminDetails) {
		adminRepository.save(adminDetails);
	}

	@Override
	public void addTrainDetails(TrainDetails trainDetails) {
		// TODO Auto-generated method stub
		trainRepository.insert(trainDetails);
		
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
	
	
	
	@Override
	public String createAdmin(AdminForm adminForm) {
		try {
			if (adminForm.getContact_no().length() != 10 && isNumeric(adminForm.getContact_no())) {
				throw new ResourceNotFoundException("Please Check Your Number");
			}
			if (!isEmailValid(adminForm.getEmail_address())) {
				throw new ResourceNotFoundException("Invalid Mail");
			}
			
			InternetAddress internetAddress = new InternetAddress(adminForm.getEmail_address());
			internetAddress.validate();

		} catch (ResourceNotFoundException | AddressException  e) {
			return e.getMessage();
		}
//		adminDetails.setadminname(Arrays.stream(adminForm.getFull_name().split(" ")).collect(Collectors.toList()).get(0)+new Random().nextInt(10000));
		adminDetails.setName(adminForm.getFull_name());
//		AdminDetails.setAge(adminForm.getAge());
		adminDetails.setContact(adminForm.getContact_no());
		credentials.setUser_id(Arrays.stream(adminForm.getFull_name().split(" ")).collect(Collectors.toList()).get(0)
				+ new Random().nextInt(10000));
		credentials.setUsername(
				"user" + Arrays.stream(adminForm.getFull_name().split(" ")).collect(Collectors.toList()).get(0)
						+ new Random().nextInt(10000));
		adminDetails.setEmailId(adminForm.getEmail_address());
		String password = RandomStringUtils.randomAlphanumeric(new Random().nextInt(3) + 8);
		Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder();
		credentials.setPassword(encoder.encode(password));

		if (secret_key.equals(adminForm.getSecret_key())) {
			adminDetails.setRoles("ADMIN");
		} else {
			adminDetails.setRoles("USER");
		}

		credentials.setRoles(adminDetails.getRoles());
		adminRepository.save(adminDetails);
		credentialRepository.save(credentials);
		emailService.sendSimpleEmail(adminForm.getEmail_address(), "Dear " + adminForm.getFull_name()
				+ ",\nThank you for registering on Railway application System\n\nWith Regards\nRailway Developer\nrailway.reservation.system@gmail.com",
				"Welcome to Railway Reservation System");
		emailService.sendSimpleEmail(
				adminForm.getEmail_address(), "User Created Successfully \n\n Your Credentials are here \n Username : "
						+ credentials.getUsername() + " \n Password : " + password,
				"Your Admin account has been Created");
		return "Your Admin Account has been created";

	}
	
	
}
