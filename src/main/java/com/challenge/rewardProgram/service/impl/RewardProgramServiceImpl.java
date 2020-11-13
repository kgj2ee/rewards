package com.challenge.rewardProgram.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.challenge.rewardProgram.exception.RewardProgramServiceException;
import com.challenge.rewardProgram.model.Transaction;
import com.challenge.rewardProgram.service.RewardProgramService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class RewardProgramServiceImpl implements RewardProgramService {
	private static final Logger LOG = LogManager.getLogger(RewardProgramServiceImpl.class.getName());

	@Override
	public int getRewardsEarnedPerMonthByCustomer(String customerId, int month) throws RewardProgramServiceException {
		// we are having transactions over the last three months (August, September, October)
		if(month<8 || month>10) {
			LOG.info("Invalid month, throw exception");
			throw new RewardProgramServiceException("The month does not fall under the dataset , please note that only the past three months data can be fetched");
		}
		List<Transaction> transactions=getTransactions().stream().filter(t->t.getCustomerId().equals(customerId)).collect(Collectors.toList());
		LOG.info("Number of Transactions made by the customer over the past 3 months "+transactions.size());
		List<Transaction> finalTransactions= new ArrayList<>();
		for (Transaction transaction : transactions) {
			String[] dateParts= transaction.getTransactionDate().split("-");
			if(month==Integer.parseInt(dateParts[0])){
				finalTransactions.add(transaction);
			}
		}
		LOG.info("Number of Transactions made by the customer in the requested month "+finalTransactions.size());
		return getRewardPoints(finalTransactions);
	}

	@Override
	public int getRewardsEarnedThroughoutByCustomer(String customerId) throws RewardProgramServiceException {
		List<Transaction> transactions=getTransactions().stream().filter(t->t.getCustomerId().equals(customerId)).collect(Collectors.toList());
		LOG.info("Number of Transactions made by the customer over the past 3 months "+transactions.size());
		return getRewardPoints(transactions);
	}
	
	public List<Transaction> getTransactions() throws RewardProgramServiceException{
		try {
			File resource = new ClassPathResource("dataSet.json").getFile();
			String json = new String(Files.readAllBytes(resource.toPath()));
			Gson gson= new Gson();
			List<Transaction> transactions = gson.fromJson(json, new TypeToken<List<Transaction>>(){}.getType());
			return transactions;
		} catch (IOException e) {
			LOG.info("Failed to read data set , reason "+ e.getMessage());
			throw new RewardProgramServiceException();
		}
		 
	}
	
	public int getRewardPoints(List<Transaction> transactions){
		int rewardPoints=0;
		for (Transaction transaction : transactions) {
			int over100 = transaction.getAmount() - 100;
		    if (over100 > 0) {
		      // A customer receives 2 points for every dollar spent over $100 in each transaction      
		      rewardPoints += (over100 * 2);
		    }    
		    if (transaction.getAmount() > 50) {
		      // plus 1 point for every dollar spent over $50 in each transaction
		      rewardPoints += 50;      
		    }
		}
		
		return rewardPoints;
		
	}

}
