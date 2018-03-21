package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import builders.UserBuilder;
import model.User;


public class UserTestCase {
	
		
	@Test
	public void shouldCreditThrows80WhenAdd80ToNewUser(){
		User user= UserBuilder.anUser().build();
		assertEquals(user.addCredit(80),80,0);
	}
	
	@Test
	public void shouldCreditThrows40WhenDebit20To60(){
		User user= UserBuilder.anUser().withCredit(60).build();
		assertEquals(user.debitCredit(20),40,0);
		
	}

}
