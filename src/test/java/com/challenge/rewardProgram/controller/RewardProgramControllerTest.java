package com.challenge.rewardProgram.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;


@SpringBootTest
public class RewardProgramControllerTest {
	@Autowired
	private RewardProgramController controller;
	
	@Test
	public void testPositiveScenarioPerMonth()  {
		String customerId= "42341";
		int month=8;
		ResponseEntity<String> entity =  controller.getRewardsEarnedPerMonthByCustomer(customerId, month);
		Assertions.assertEquals("Reward points earned : 150", entity.getBody());
		Assertions.assertEquals(200, entity.getStatusCodeValue());
	}
	
	@Test
	public void testNegativeScenarioPerMonth()  {
		String customerId= "42341";
		int month=7;
		ResponseEntity<String> entity =  controller.getRewardsEarnedPerMonthByCustomer(customerId, month);
		Assertions.assertEquals("The month does not fall under the dataset , please note that only the past three months data can be fetched", entity.getBody());
		Assertions.assertEquals(400, entity.getStatusCodeValue());
	}
	
	
	@Test
	public void testRewardPointsOverTheLastThreeMonths() throws Exception {
		String customerId="42342";
		ResponseEntity<String> entity =  controller.getRewardsEarnedThroughoutByCustomer(customerId);
		Assertions.assertEquals("Reward points earned: 350", entity.getBody());
		Assertions.assertEquals(200, entity.getStatusCodeValue());
	}
		

}
