package com.eureka.discovery.service;


import java.util.List;

import com.eureka.discovery.model.UserDetail;
import com.eureka.discovery.model.UserForm;

public interface UserService 
{
	public String createUser(UserForm userForm);
	
	public List<UserDetail>getAllUser();
	
}