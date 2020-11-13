package com.challenge.rewardProgram.exception;


public class RewardProgramServiceException extends Exception{

	String message;
	String details;
	
	private static final long serialVersionUID = 1L;

	
	public RewardProgramServiceException() {
		super();
	}
	
	public RewardProgramServiceException(String msg){
		super(msg);
		this.message=msg;
	}
	
	public RewardProgramServiceException(String msg,String details){
		this.message=msg;
		this.details= details;
	}
	
	
	
}
