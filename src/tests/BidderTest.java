/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */


package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Bidder;

/**
 * The test class for bidder.
 * @author Dmitriy Onishchenko.
 *
 */
public class BidderTest {
	
	
	private Bidder myBidder1;
	
	@Before
	public void setup() {
		
		myBidder1 = new Bidder("Dave Mathews", "username", "1234", 
							"d@gmail.com", "1111 11th st San Diego", "111-111-1111");
		
	}
	
	@Test
	public void testToStringForBidder() {
		
		assertEquals("Dave Mathews logged in as Bidder", myBidder1.toString());		
		
	}

}
