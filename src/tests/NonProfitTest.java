/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */


package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.NonProfit;

/**
 * The test class for non profit
 * @author Dmitriy Onishchenko.
 *
 */
public class NonProfitTest {
	
	private NonProfit myNonProfit1;
	
	@Before
	public void setup() {
		
		myNonProfit1 = new NonProfit("Bill Gates", "username", "1234",
							"Bill and Melinda Gates", "1234 Mercer Island", "111-111-1111");				
		
	}
	
	@Test
	public void testToStringForNonProfit() {
		
		assertEquals("Bill Gates logged in as Non-Profit representative "
						+ "for Bill and Melinda Gates", myNonProfit1.toString());				
	}

}
