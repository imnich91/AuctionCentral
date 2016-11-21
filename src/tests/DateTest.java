/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */


package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Date;

/**
 * Test class for Date class
 * @author Dmitriy Onishchenko
 *
 */
public class DateTest {

	private Date myDate;
	
	@Before
	public void setup() {
		
		myDate = new Date(12, "december", 2016);
		
	}
	
	@Test
	public void testToString() {
		
		assertEquals("december 12, 2016", myDate.toString());
		
	}

}
