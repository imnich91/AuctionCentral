/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */


package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Time;

/**
 * Test class for Time class.
 * @author Dmitriy Onishchenko
 *
 */
public class TimeTest {

	private Time myTime;
	
	@Before
	public void setup() {
		myTime = new Time(2, 0, "PM");		
		
	}
	
	@Test
	public void testToString() {		
		assertEquals("2:00 PM", myTime.toString());		
	}

}
