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

/**
 * This test class runs the acceptance tests for the user story
 * "As a staff, I want to change the maximum number of future auctions."
 * @author Georgia Wade
 *
 */
public class ChangeMaxNumberOfFutureAuctionsAcceptanceTest {
	
	private Calendar cal;
	
	@Before
	public void setup(){
		
		cal = new Calendar();
	}
	
	/**
	 * Acceptance test for: max number of auctions allowed is negative
	 */
	@Test
	public void testMaxNumGreaterThanZeroOnNegativeNumber(){
	
		assertFalse(cal.setAuctionsAllowed(-3));
	}

	/**
	 * Acceptance test for: max number of auctions allowed is zero
	 */
	@Test
	public void testMaxNumGreaterThanZeroOnZero(){
		
		assertFalse(cal.setAuctionsAllowed(0));
	}
	
	/**
	 * Acceptance test for: max number of auctions allowed is positive
	 */
	@Test
	public void testMaxNumGreaterThanZeroOnNumberGreaterThanZero(){
		
		assertTrue(cal.setAuctionsAllowed(12));
	}
}
