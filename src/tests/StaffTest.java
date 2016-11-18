/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */

package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


import model.Staff;

/**
 * The test class for the staff class.
 * @author Dmitriy Onishchenko
 *
 */
public class StaffTest {	
	
	private Staff myStaff1;
	
	@Before
	public void setup() {
		
		myStaff1 = new Staff("Bill Clinton", "username", "1234");		
	}
	
	@Test
	public void testToStringForStaff() {
		
		assertEquals("Bill Clinton logged in as Auction Central Staff Person", myStaff1.toString());		
		
	}

}
