package tests;

import model.Member;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class MemberTestCase {
	
	private Member member;

	@Before
	public void setUp() throws Exception {
		member= new Member();
	}
	
	@Test
	public void CreditTest(){
		member.addCredit(40.0);
		assertEquals(80.0, member.getCredit(), 0);
	}

}
