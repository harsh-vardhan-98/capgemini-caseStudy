package com.eureka.discovery.model;

import org.springframework.stereotype.Component;

@Component
public class UserForm{

	private String full_name;
	private int age;
	private String contact_no;
	private String email_address;
	private String secret_key;
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getContact_no() {
		return contact_no;
	}
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	public String getEmail_address() {
		return email_address;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	public UserForm(String full_name, int age, String contact_no, String email_address , String secret_key) {
		super();
		this.full_name = full_name;
		this.age = age;
		this.contact_no = contact_no;
		this.email_address = email_address;
		this.secret_key = secret_key;
		
	}
	public UserForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getSecret_key() {
		return secret_key;
	}
	public void setSecret_key(String secret_key) {
		this.secret_key = secret_key;
	}





}