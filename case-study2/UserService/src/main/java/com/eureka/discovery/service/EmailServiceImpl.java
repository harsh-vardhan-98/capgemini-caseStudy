package com.eureka.discovery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String email;

	
	public String sendSimpleEmail(String to, String body, String subject) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		mailMessage.setFrom(email);
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(body);
		
		javaMailSender.send(mailMessage);
		
		return "Success";
	}

	

}