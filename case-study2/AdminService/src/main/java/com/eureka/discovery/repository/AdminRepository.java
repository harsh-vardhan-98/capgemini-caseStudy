package com.eureka.discovery.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.eureka.discovery.model.AdminDetails;

@Repository
public interface AdminRepository extends MongoRepository<AdminDetails,String> {



}
