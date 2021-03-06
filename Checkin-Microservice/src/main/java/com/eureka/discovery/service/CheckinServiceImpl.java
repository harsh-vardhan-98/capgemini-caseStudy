package com.eureka.discovery.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eureka.discovery.model.CheckinDetails;
import com.eureka.discovery.repository.CheckinRepository;

@Service
public class CheckinServiceImpl implements CheckinService {

	@Autowired
	private CheckinRepository checkinRepo;
	long id;

	// This method is to get all checkin done
	@Override
	public List<CheckinDetails> getAllCheckins() {
		List<CheckinDetails> checkinDetails = new ArrayList<CheckinDetails>();
		checkinRepo.findAll().forEach(checkinDetails1 -> checkinDetails.add(checkinDetails1));
		return checkinDetails;
	}

	@Override
	public CheckinDetails findByPnrNo(long pnrNo) {
		return checkinRepo.findByPnrNo(pnrNo);

	}

	// This method is to get Checkin details
	@Override
	public void addDetails(CheckinDetails cd) {
		checkinRepo.save(cd);

	}

}
