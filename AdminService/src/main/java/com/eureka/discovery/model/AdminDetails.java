package com.eureka.discovery.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "adminSignUpDetails")
@Component
public class AdminDetails {

	@Id
	private String name;
	private String emailId;
	private String contact;
	private String roles; 
	

	public AdminDetails() {
		super();
	}

	public AdminDetails(String name, String emailId, String contact, String roles) {
		super();
		this.name = name;
		this.emailId = emailId;
		this.contact = contact;
		this.roles = roles;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	

	
}