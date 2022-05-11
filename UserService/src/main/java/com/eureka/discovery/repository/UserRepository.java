package com.eureka.discovery.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.eureka.discovery.model.UserDetailsmodel;

@Repository
public interface UserRepository extends MongoRepository<UserDetailsmodel,String>{

}
