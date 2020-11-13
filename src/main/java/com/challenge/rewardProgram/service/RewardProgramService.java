package com.challenge.rewardProgram.service;

import com.challenge.rewardProgram.exception.RewardProgramServiceException;

public interface RewardProgramService {
	
	public int getRewardsEarnedPerMonthByCustomer(String customerId, int month) throws RewardProgramServiceException;
	
	public int getRewardsEarnedThroughoutByCustomer(String customerId) throws RewardProgramServiceException;
}
