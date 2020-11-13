package com.challenge.rewardProgram.service.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.challenge.rewardProgram.exception.RewardProgramServiceException;

@SpringBootTest
public class RewardProgramServiceImplTest {

	@Autowired
	RewardProgramServiceImpl service;

	@Test
	public void testRewardPointsByMonthPositive() throws RewardProgramServiceException {
		Assertions.assertTrue(service.getRewardsEarnedPerMonthByCustomer("42341", 8) == 150);
	}

	@Test
	public void testRewardPointsOverDurationPositive() throws RewardProgramServiceException {
		Assertions.assertTrue(service.getRewardsEarnedThroughoutByCustomer("42342") == 350);
	}

	@Test
	public void testRewardPointServiceExceptionThrow() {

		Assertions.assertThrows(RewardProgramServiceException.class, () -> {

			service.getRewardsEarnedPerMonthByCustomer("42341", 7);
		});
	}
}
