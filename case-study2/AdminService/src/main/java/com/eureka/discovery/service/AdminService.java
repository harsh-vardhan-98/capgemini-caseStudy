package com.eureka.discovery.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.eureka.discovery.model.AdminDetails;
import com.eureka.discovery.model.AdminForm;
import com.eureka.discovery.model.TrainDetails;

public interface AdminService {

public List<TrainDetails> getAllDetails();
	
	public void addTrainDetails(TrainDetails trainDetails);
	
	public TrainDetails updateTrainDetails(int trainNo,TrainDetails trainDetails);
	
	public ResponseEntity<TrainDetails> deleteTrainDetails(int trainNo);

	public TrainDetails findByTrainNo(int trainNo);

	public TrainDetails[] findByStartPointAndEndPointAndTrainDate(String startPoint, String endPoint,
			String trainDate);

	public void addAdmin(AdminDetails adminDetails) ;
	
	public String createAdmin(AdminForm adminForm);
	
}
