/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Calendar;

public class ChangeMaxNumberOfFutureAuctionsAcceptanceTest {
	
	private Calendar cal;
	
	@Before
	public void setup(){
		
		cal = new Calendar();
	}
	
	@Test
	public void testMaxNumGreaterThanZeroOnNegativeNumber(){
	
		assertFalse(cal.setAuctionsAllowed(-3));
	}

	@Test
	public void testMaxNumGreaterThanZeroOnZero(){
		
		assertFalse(cal.setAuctionsAllowed(0));
	}
	
	@Test
	public void testMaxNumGreaterThanZeroOnNumberGreaterThanZero(){
		
		assertTrue(cal.setAuctionsAllowed(12));
	}
}
