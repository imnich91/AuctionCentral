/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */
package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import model.Auction;
import model.Bidder;
import model.Date;
import model.Item;
import model.NonProfit;
import model.Time;

/**
 * This test class runs the acceptance tests for the user story
 * "As a contact person for a non-profit organization, I want to
 * remove an inventory item from an auction".
 * @author Georgia Wade
 *
 */
public class RemoveInventoryItemAcceptanceTest {
	
	private Item myItem;
	private Item myItem2;	

	private NonProfit myNonProfit;
	
	private LocalDate myLocalDate;	
	private Date myCurrDate;
	private Date myAuctDateMoreThan2Days;
	private Date myAuctDateEqualTo2Days;
	private Date myAuctDateLessThan2Days;
	private Date myAuctDateAfterAuctionDate;
	
	private Auction myAuction;


	@Before
	public void setup() {
		myLocalDate = LocalDate.now();		
		myCurrDate = new Date(myLocalDate.getDayOfMonth(), myLocalDate.getMonth().toString(),
								myLocalDate.getYear());
		
		myAuctDateMoreThan2Days = new Date(myCurrDate.getDay() + 5, myCurrDate.getMonth(), myCurrDate.getYear());
		myAuctDateEqualTo2Days = new Date(myCurrDate.getDay() + 2, myCurrDate.getMonth(), myCurrDate.getYear());
		myAuctDateLessThan2Days = new Date(myCurrDate.getDay() + 1, myCurrDate.getMonth(), myCurrDate.getYear());
		myAuctDateAfterAuctionDate  = new Date(myCurrDate.getDay() - 1, myCurrDate.getMonth(), myCurrDate.getYear());
		
		myItem = new Item("Autographed Sounders Jersey", "Good", "medium",
				50, "Seattle Sounders", "Game worn jersy signed by Brad Evan.",
				"CenturyLink Field front office has the jersey");
		
		myItem2 = new Item("Football", "good", "small", 100, "Bill and Melinda Gates", 
						"Really cool football", "hello");
		
		myNonProfit = new NonProfit("Amber Smith", "asmith", "9389", "World Vision",
										"8372 Seattle", "8675309");
		
		myAuction = new Auction("World Vision", myCurrDate, new Time(02, 00, "PM"));

		myAuction.addItem(myNonProfit, myItem);
		
	}
	
	/**
	 * Acceptance test for: removing an item after two or more days
	 */
	@Test 
	public void testRemoveItemOnMoreThanTwoDays() {
		
		assertTrue(myAuction.removeItem(myNonProfit, myItem.getItemNumber(), myAuctDateMoreThan2Days, myCurrDate));
	}
	
	/**
	 * Acceptance test for: removing an item after exactly two days
	 */
	@Test
	public void testRemoveItemOnExactlyTwoDays(){
		
		assertTrue(myAuction.removeItem(myNonProfit, myItem.getItemNumber(), myAuctDateEqualTo2Days, myCurrDate));
	}
	
	/**
	 * Acceptance test for: removing an item within two days
	 */
	@Test
	public void testRemoveItemOnLessThanTwoDays(){
		
		assertFalse(myAuction.removeItem(myNonProfit, myItem.getItemNumber(), myAuctDateLessThan2Days, myCurrDate));
	}
	
	/**
	 * Acceptance test for: removing an item within two days
	 */
	@Test
	public void testRemoveItemOnDayOfAuction(){
		
		assertFalse(myAuction.removeItem(myNonProfit, myItem.getItemNumber(), myCurrDate, myCurrDate));
	}
	
	/**
	 * Acceptance test for: removing an item within two days
	 */
	@Test
	public void testRemoveItemOnDatePastAuction(){
		
		assertFalse(myAuction.removeItem(myNonProfit, myItem.getItemNumber(), myAuctDateAfterAuctionDate, myCurrDate));
	}
	
}
