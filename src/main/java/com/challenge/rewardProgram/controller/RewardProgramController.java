package com.challenge.rewardProgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.rewardProgram.exception.RewardProgramServiceException;
import com.challenge.rewardProgram.service.RewardProgramService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class RewardProgramController {
	
	@Autowired
	RewardProgramService rewardProgramService;
	
	@GetMapping("/getRewardsEarnedPerMonthByCustomer/{customerId}/{month}")
	@ApiOperation(
	        value = "Reward points earned by customer for the transactions in a specific month",
	        notes = "Returns HTTP 400 if the month is not in range of 8,9,10 (August,September and October)",
	        response = String.class 
	)
	@ApiResponses(value = {
	        @ApiResponse(code = 400, message = "The month does not fall under the dataset , please note that only the past three months data can be fetched")
	})
	public ResponseEntity<String> getRewardsEarnedPerMonthByCustomer(@PathVariable String customerId, @PathVariable int month){
		try {
			int rewardPointsEarned= rewardProgramService.getRewardsEarnedPerMonthByCustomer(customerId, month);
			return new ResponseEntity<String>("Reward points earned : "+ rewardPointsEarned, HttpStatus.OK);
		}catch(RewardProgramServiceException e){
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/getRewardsEarnedThroughoutByCustomer/{customerId}")
	@ApiOperation(
	        value = "Reward points earned by customer for the transactions in the past three months: August, September and October",
	        response = String.class
	)
	public ResponseEntity<String> getRewardsEarnedThroughoutByCustomer(@PathVariable String customerId){
		try {
			int rewardPointsEarned= rewardProgramService.getRewardsEarnedThroughoutByCustomer(customerId);
			return new ResponseEntity<String>("Reward points earned: "+rewardPointsEarned, HttpStatus.OK);
		}catch(RewardProgramServiceException e){
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}

}
