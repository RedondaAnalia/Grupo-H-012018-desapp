package tests.Model;

import static org.junit.Assert.*;

import org.junit.Test;

import builders.UserBuilder;
import model.User;


public class UserTestCase {
	
	/*
	 * Credit Tests
	 */		
	@Test
	public void shouldCreditThrows80WhenAdd80ToNewUser(){
		User user= UserBuilder.anUser().build();
		assertEquals(user.getAccount().addCredit(80),80,0);
	}
	
	@Test
	public void shouldCreditThrows40WhenDebit20To60(){
		User user= UserBuilder.anUser().withCredit(60).build();
		assertEquals(user.getAccount().debitCredit(20),40,0);
	}
	
	/*
	 * Reputation Tests.
	 */
	@Test
	public void shouldReputationThrows3WhenNewUser(){
		User user= UserBuilder.anUser().build();
		assertEquals(user.reputation(),3.0,0);
	}
	
	@Test
	public void shouldReputationThrows4WhenAVGIs4(){
		User user= UserBuilder.anUser().build();
		user.processScore(5);
		user.processScore(3);
		assertEquals(user.reputation(),4.0,0);
	}
	
	@Test(expected=RuntimeException.class)
	public void shouldThrowAnExceptionWhenIPutAnOutOfRangeScore(){
		User user= UserBuilder.anUser().build();
		user.processScore(6);
	}
	
	@Test
	public void shouldBeDisableUserWhenTheReputationIsLowerThan3(){
		User user= UserBuilder.anUser().build();
		user.processScore(1);
		assertFalse(user.isEnabled());
	}
	
	@Test
	public void shouldBeEnabledUserWhenTheReputationIsHigherOrEqualThan3(){
		User user=UserBuilder.anUser().build();
		user.processScore(5);
		assertTrue(user.isEnabled());
	}

	@Test
	public void shouldBeEnabledUserWhenTheReputationIsLowerThan3(){
		User user= UserBuilder.anUser().build();
		user.processScore(1);
		assertFalse(user.isEnabled());
	}
}
