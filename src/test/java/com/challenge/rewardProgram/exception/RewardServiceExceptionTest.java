package com.challenge.rewardProgram.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RewardServiceExceptionTest {

	@Test
	public void testNoArgs() {

		RewardProgramServiceException exception = new RewardProgramServiceException();
		Assertions.assertNotNull(exception);

	}

	@Test
	public void testOneArgs() {

		RewardProgramServiceException exception = new RewardProgramServiceException("test");
		Assertions.assertNotNull(exception);

	}

	@Test
	public void testTwoArgs() {

		RewardProgramServiceException exception = new RewardProgramServiceException("test", "test1");
		Assertions.assertNotNull(exception);

	}
}
