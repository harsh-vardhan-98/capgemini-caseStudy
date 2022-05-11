package com.hms.payment.repository;

import java.util.Map;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.hms.payment.models.PayModel;
import com.hms.payment.models.PaytmDetails;

@Repository
public interface PaymentRepository extends MongoRepository<PayModel, String>{

}
