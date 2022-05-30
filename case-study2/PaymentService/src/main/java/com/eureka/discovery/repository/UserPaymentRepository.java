package com.eureka.discovery.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.eureka.discovery.model.PaymentDetails;

@Repository
public interface UserPaymentRepository extends MongoRepository<PaymentDetails, Long>
{

}
