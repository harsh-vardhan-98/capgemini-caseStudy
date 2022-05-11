package com.eureka.discovery.service;

import java.util.List;

import com.eureka.discovery.model.UserDetails;

public interface UserService 
{ 
	public List<UserDetails> getAll(); 
	public UserDetails getUserDetailsById(long pnrNo); 
	public String addUserBookingDetails(UserDetails userDetails); 
	public String deleteUserBookingDetails(long pnrNo); 
}