package tests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import model.Bidder;
import model.Date;
import model.Item;

public class CancelBidOnAnItemAcceptanceTest {

	private Item myItem;
	private Item myItem2;	
	private Bidder myBidder1;
	
	private LocalDate myLocalDate;	
	private Date myCurrDate;
	private Date myAuctDateMoreThan2Days;
	private Date myAuctDateEqualTo2Days;
	private Date myAuctDateLessThan2Days;
	private Date myAuctDateAfterAuctionDate;
	
	@Before
	public void setup() {
		myLocalDate = LocalDate.now();		
		myCurrDate = new Date(myLocalDate.getDayOfMonth(), myLocalDate.getMonth().toString(), myLocalDate.getYear());
		
		myAuctDateMoreThan2Days = new Date(myCurrDate.getDay() + 5, myCurrDate.getMonth(), myCurrDate.getYear());
		myAuctDateEqualTo2Days = new Date(myCurrDate.getDay() + 2, myCurrDate.getMonth(), myCurrDate.getYear());
		myAuctDateLessThan2Days = new Date(myCurrDate.getDay() + 1, myCurrDate.getMonth(), myCurrDate.getYear());
		myAuctDateAfterAuctionDate  = new Date(myCurrDate.getDay() - 1, myCurrDate.getMonth(), myCurrDate.getYear());
	
		myBidder1 = new Bidder("Dave Mathews", "dave", "3456", 
				"d@gmail.com", "1111 11th st San Diego", "111-111-1111");
		
		myItem = new Item("Autographed Sounders Jersey", "Good", "medium",
				50, "Seattle Sounders", "Game worn jersy signed by Brad Evan.",
				"CenturyLink Field front office has the jersey");
		
		myItem2 = new Item("Football", "good", "small", 100, "Bill and Melinda Gates", 
						"Really cool football", "hello");		
		myItem2.makeBid(myBidder1, 300);
	}
	
	/**
	 * Acceptance Test for : Cancelling a bid that is more than 2 days before the auction
	 */
	
	@Test
	public void testCancelBidOnMoreThanTwoDaysBeforeAuciton() {		
		
		assertTrue(myItem2.cancelBid(myBidder1, myAuctDateMoreThan2Days, myCurrDate));		
	}
	
	/**
	 * Acceptance Test for : Cancelling a bid that is exactly at 2 days before the auction
	 */
	@Test
	public void testCancelBidOnExactlyTwoDaysBeforeAuction() {
				
		assertTrue(myItem2.cancelBid(myBidder1, myAuctDateEqualTo2Days, myCurrDate));		
	}
	
	/**
	 * Acceptance Test for : Cancelling a bid that is less than 2 days before the auction
	 */
	@Test
	public void testCancelBidOnLessThanTwoDaysBeforeAuction() {
		
		assertFalse(myItem2.cancelBid(myBidder1, myAuctDateLessThan2Days, myCurrDate));
	}
	
	/**
	 * Acceptance Test for : Cancelling a bid that is less than 2 days before the auction
	 */
	@Test
	public void testCancelBidOnDayOfAuction() {
		
		assertFalse(myItem2.cancelBid(myBidder1, myCurrDate, myCurrDate));
	}
	
	/**
	 * Acceptance Test for : Cancelling a bid that is less than 2 days before the auction
	 */
	@Test
	public void testCancelBidOnDatePastAuction() {
		
		assertFalse(myItem2.cancelBid(myBidder1, myAuctDateAfterAuctionDate, myCurrDate));
	}
	
	
	/**
	 * Acceptance Test for : Cancelling a bid on a bidder who doesnt have bids
	 */
	@Test
	public void testCancelBidOnBiddersWhoDoesntHaveABid() {
		
		assertFalse(myItem.cancelBid(myBidder1, myAuctDateMoreThan2Days, myCurrDate));	
	}

}
