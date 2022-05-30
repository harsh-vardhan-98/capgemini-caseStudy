package com.eureka.discovery.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.eureka.discovery.model.TrainDetails;

@Repository
public interface TrainRepository extends MongoRepository<TrainDetails, Long> {

	
	TrainDetails findByTrainNo(int trainNo);
	TrainDetails[] findByStartPointAndEndPointAndTrainDate(String startPoint, String endPoint,String trainDate);
	
}
