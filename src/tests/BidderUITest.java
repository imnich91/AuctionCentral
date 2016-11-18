/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */


package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import model.Bid;
import model.Bidder;
import model.Calendar;
import model.Item;
import userInterface.BidderUI;

/**
 * Test class for BidderUI class.
 * @author Dmitriy Onishchenko
 *
 */
public class BidderUITest {

	private BidderUI myBidderUI;
	private Bidder myBidder;
	private Bidder myBidder2;
	private Item myItem;	
	private Calendar myCalendar;
	
	@Before
	public void test() {
		
		myCalendar = new Calendar();
		
		myItem = new Item("Football", "good", "small", 100, "Bill and Melinda Gates", 
						"Really cool football", "hello");	
		
		myBidder = new Bidder("Dave Mathews", "dave", "3456", 
				"d@gmail.com", "1111 11th st San Diego", "222-222-2222");
		
		myBidder2 = new Bidder("Steve harvey", "s", "34", 
				"d@gmail.com", "1111 11th st San Diego", "222-222-2222");
		
		myBidderUI = new BidderUI(myBidder, new Scanner(System.in), myCalendar);
		
		myItem.makeBid(myBidder, 150);		
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCurrentBiddersBidBidderHasMadeABid() {
		
		assertEquals(150, myBidderUI.getCurrentBiddersBid(myBidder, (ArrayList<Bid>)myItem.getBunchObids()));
				
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCurrentBiddersBidBidderHasNotMadeBid() {
		
		assertEquals(-1, myBidderUI.getCurrentBiddersBid(myBidder2, (ArrayList<Bid>)myItem.getBunchObids()));
				
	}

}
