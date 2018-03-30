package tests.Model;

import static org.junit.Assert.*;

import org.junit.Test;

import builders.UserBuilder;
import model.User;


public class UserTestCase {
	
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
	
	
	/**
	 * Status test.
	 */
	@Test
	public void shouldBeDisabledUserWhenTheReputationIsLowerThanEnablingScore(){
		User user= UserBuilder.anUser().build();
		user.processScore(1);
		assertFalse(user.isEnabled());
	}
	
	@Test
	public void shouldBeEnabledUserWhenTheReputationIsHigherOrEqualThanEnablingScore(){
		User user=UserBuilder.anUser().build();
		user.processScore(5);
		assertTrue(user.isEnabled());
	}

	@Test
	public void shouldBeEnabledUserWhenTheReputationIsLowerThanEnablingScore(){
		User user= UserBuilder.anUser().build();
		user.processScore(1);
		assertFalse(user.isEnabled());
	}
}
