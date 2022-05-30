package com.eureka.discovery.service;

import java.util.List;

import com.eureka.discovery.model.CheckinDetails;

public interface CheckinService {

	public List<CheckinDetails> getAllCheckins();

	public void addDetails(CheckinDetails userDetails);

	public CheckinDetails findByPnrNo(long pnrNo);
}
