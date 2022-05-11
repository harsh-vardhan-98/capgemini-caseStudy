package com.eureka.discovery.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.eureka.discovery.model.CheckinDetails;

@Repository
public interface CheckinRepository extends MongoRepository<CheckinDetails, Integer> {
	public CheckinDetails findByPnrNo(long pnrNo); 
}