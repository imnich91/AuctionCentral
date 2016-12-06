/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import model.Auction;
import model.Bidder;
import model.Item;
import model.NonProfit;
import model.Staff;
import model.Time;
import model.Date;

/**
 * This test class runs the acceptance tests for the user story
 * "As a bidder, I want to bid for an item in an auction".
 * @author Georgia Wade
 *
 */

public class BidOnAnItemAcceptanceTest {
	
	private Item myItem1;
	private Item myItem2;
	
	private Bidder myBidder1;
	private Bidder myBidder2;
	private Bidder myUnregisterBidder;
	
	private Staff myStaff1;
	
	private NonProfit myNonProfit1;
	
	private Auction myAuction1;
	private Auction myAuction2;
	private Auction myAuction3;
	
	private Date today;
	

	@Before
	public void setup() {

		LocalDate myDate = LocalDate.now();
		
		today = new Date(myDate.getDayOfMonth(), myDate.getMonth().toString(), myDate.getYear());
		
		myItem1 = new Item("Autographed Sounders Jersey", "Good", "medium",
				50, "Seattle Sounders", "Game worn jersy signed by Brad Evans.",
				"CenturyLink Field front office has the jersey");
		
		myItem2 = new Item("Football", "good", "small", 100, "Bill and Melinda Gates", 
						"Really cool football", "hello");		
		
		myNonProfit1 = new NonProfit("Bill Gates", "billy", "1234",
				"Bill and Melinda Gates", "1234 Mercer Island", "111-111-1111");		
		
		myStaff1 = new Staff("Bill Clinton", "billyC", "2345");
		
		myBidder1 = new Bidder("Dave Mathews", "dave", "3456", 
				"d@gmail.com", "1111 11th st San Diego", "111-111-1111");
		
		myBidder2 = new Bidder("Steve harvey", "s", "3456", 
				"d@gmail.com", "1111 11th st San Diego", "111-111-1111");
		
		myUnregisterBidder = new Bidder("George Bush", "", "", 
				"d@gmail.com", "1111 11th st San Diego", "111-111-1111");		
		
		myItem2.makeBid(myBidder1, 300);
		
		myAuction1 = new Auction("Bill and Melinda Gates", new Date(12, "December", 2017), new Time(11, 00, "AM"));
				
		myAuction2 = new Auction("Bill and Melinda Gates", new Date(1, "December", 2016), 
							new Time(01, 00, "PM"));
		
		myAuction3 = new Auction("Bill and Melinda Gates", today, new Time(05, 00, "PM"));
	}

	/**
	 * Acceptance test for: user must be register
	 */
	@Test
	public void testBidderMustBeRegisteredOnUnregisteredBidder(){
		
		assertFalse(myItem2.makeBid(myUnregisterBidder, 200));
	}
	
	@Test
	public void testBidderMustBeRegisteredOnRegisteredBidder(){
		
		assertTrue(myItem2.makeBid(myBidder2, 200));
	}
	
	/**
	 * Acceptance test for: no AC staff or NPO contact for auction
	 * may make a bid
	 */
	
	@Test
	public void testNoACStaffOrNPOContactCanBidOnNotNPONotAC(){
		
		assertTrue(myItem2.isValidBidder(myBidder1));
	}
	
	@Test
	public void testNoACStaffOrNPOContactCanBidOnACStaff(){
		
		assertFalse(myItem2.isValidBidder(myStaff1));
	}
	
	@Test
	public void testNoACStaffOrNPOContactCanBidOnNPOContact(){
	
		assertFalse(myItem2.isValidBidder(myNonProfit1));
	}

	
	/**
	 * Acceptance test for: previous bids for this bidder
	 */
	@Test
	public void testPreviousBidOnBidderWithPreviousBid(){
		
		assertFalse(myItem2.makeBid(myBidder1, 200));
	}
	
	@Test
	public void testPreviousBidOnBidderWithNoPreviousBid(){
		
		assertTrue(myItem2.makeBid(myBidder2, 200));
	}
	
	/**
	 * Acceptance test for: valid price
	 */
	@Test
	public void testValidPriceOnZeroBidAmount(){
		
		assertFalse(myItem1.isValidBidPrice(0));
	}
	
	@Test
	public void testValidPriceOnNegativBidAmount(){
		
		assertFalse(myItem1.isValidBidPrice(-1));
	}
	
	@Test
	public void testValidPriceOnGreaterThanMinimumBidAmount(){
		
		assertTrue(myItem1.isValidBidPrice(75));
	}
	
	@Test
	public void testValidPriceOnEqualToMinimumBidAmount(){
		
		assertTrue(myItem1.isValidBidPrice(50));
	}
	
	@Test
	public void testValidPriceOnPostiveLessThanMinimumBidAmount(){
		
		assertFalse(myItem1.isValidBidPrice(25));
	}
	
	/**
	 * Acceptance test for: auction date
	 */
	
	@Test
	public void testAuctionDateOnAuctionScheduledToday(){
		
		myAuction3.addItem(myNonProfit1, myItem1);
		
		assertFalse(myItem1.canMakeBid(myAuction3.getDate()));
	}
	
	@Test
	public void testAuctionDateOnAuctionScheduledInPast(){
		
		myAuction2.addItem(myNonProfit1, myItem1);
		
		assertFalse(myItem1.canMakeBid(myAuction2.getDate()));
		
	}
	
	@Test
	public void testAuctionDateOnAuctionScheduledInFuture(){
		
		myAuction1.addItem(myNonProfit1, myItem1);
		
		assertTrue(myItem1.canMakeBid(myAuction1.getDate()));
	}
	
}
