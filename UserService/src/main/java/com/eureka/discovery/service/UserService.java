package com.eureka.discovery.service;

import java.util.List;

import com.eureka.discovery.model.TrainDetails;
import com.eureka.discovery.model.UserDetailsmodel;

public interface UserService 
{
	public List<TrainDetails> getAllDetails();
	public String pnrStatus(long pnrNo);
	public TrainDetails getDetailsByTrainNo(int trainNo);
	public List<TrainDetails> findByStartPointAndEndPointAndTrainDate(String startPoint, String endPoint,
			String trainDate);
	
	public void addUser(UserDetailsmodel userDetails);
	
}