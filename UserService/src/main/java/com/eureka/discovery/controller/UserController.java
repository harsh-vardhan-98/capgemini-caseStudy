package com.eureka.discovery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.eureka.discovery.model.TrainDetails;
import com.eureka.discovery.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@Component
@RestController
@RequestMapping("/users")
public class UserController 
{
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/greet")
	public String greet() {
		return "Hello";
	}
	
	
	// This method finds the all trains in the database
	@GetMapping("/alltrains")
	@ApiOperation(value="Get all train details")
	public List<TrainDetails> getAllDetails()
	{
		return userService.getAllDetails();
	}
	
	// This method gets status of booking by pnr no
	@GetMapping("/status/{pnrNo}")
	@ApiOperation(value="Get status of your booking by Pnr Number")
	public String getStatus(@RequestParam long pnrNo)
	{
		return userService.pnrStatus(pnrNo);
	}
	
	// This method finds the train by train no
	@GetMapping("/trainNo/{trainNo}")
	@ApiOperation(value="Get Train details by train Number")
	public TrainDetails getDetailsBytrainNo(@RequestParam int trainNo) 
	{
		return userService.getDetailsByTrainNo(trainNo);
	}
	
	// This method find the trains by startpoint, endpoint and TrainDate
	@GetMapping("/findBy/{startPoint}/{endPoint}/{trainDate}")
	@ApiOperation(value="Get Train details by giving startPoint and endPoint locations and date")
	public TrainDetails[] getTrainDetailsByStartPointAndEndPointAndTrainDate(@PathVariable String startPoint,@PathVariable  String endPoint,@PathVariable String trainDate)
	{

		TrainDetails[] response=restTemplate.getForObject("http://localhost:8080/admin/access/findBy/"+startPoint +"/"+endPoint+"/"+trainDate,TrainDetails[].class);
		return response;
		
	}
	

}