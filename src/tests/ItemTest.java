/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */

package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import model.Bidder;
import model.Date;
import model.Item;
import model.NonProfit;
import model.Staff;

/**
 * The test class for item class.
 * @author Dmitriy Onishchenko, Georgia Wade.
 *
 */


public class ItemTest {	
	
	private Item myItem;
	private Item myItem2;	
	private Bidder myBidder1;
	private Bidder myBidder2;
	private Bidder myUnregisterBidder;
	private Staff myStaff1;
	private NonProfit myNonProfit1;
	
	private LocalDate myLocalDate;	
	private Date myCurrDate;
	private Date myDateMoreThan2Days;
	private Date myDateEqualTo2Days;
	private Date myDateLessThan2Days;

	@Before
	public void setup() {
		
		myLocalDate = LocalDate.now();		
		myCurrDate = new Date(myLocalDate.getDayOfMonth(), myLocalDate.getMonth().toString(), myLocalDate.getYear());
		
		myDateMoreThan2Days = new Date(myCurrDate.getDay() - 5, myCurrDate.getMonth(), myCurrDate.getYear());
		myDateEqualTo2Days = new Date(myCurrDate.getDay() - 2, myCurrDate.getMonth(), myCurrDate.getYear());
		myDateLessThan2Days = new Date(myCurrDate.getDay() - 1, myCurrDate.getMonth(), myCurrDate.getYear());
		
	
		
		myItem = new Item("Autographed Sounders Jersey", "Good", "medium",
				50, "Seattle Sounders", "Game worn jersy signed by Brad Evan.",
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
		
	}
	
	
	@Test
	public void testMakeBidOnBidderWhoHasNotPreviouslyBid() {
		
		assertTrue(myItem2.makeBid(myBidder2, 200));			
	}
	
	@Test
	public void testMakeBidOnBidderWhoHasPreviouslyBid() {
		
		assertFalse(myItem2.makeBid(myBidder1, 200));		
	}
	
	@Test
	public void testMakeBidOnUnregisterdBidder() {		
		assertFalse(myItem2.makeBid(myUnregisterBidder, 200));		
	}
	
	@Test
	public void testMakeBidOnRegisteredBidder() {		
		assertTrue(myItem2.makeBid(myBidder2, 200));			
	}
	
	@Test
	public void testMakeBidOnStaffUser() {		
		assertFalse(myItem2.makeBid(myStaff1, 200));			
	}
	
	@Test
	public void testMakeBidderOnNonProfitUser() {		
		assertFalse(myItem2.makeBid(myNonProfit1, 200));			
	}
	
	@Test
	public void testValidBidPriceOnNegativeBidAmount(){
		assertEquals(false, myItem.isValidBidPrice(-1));
	}
	
	@Test
	public void testValidBidPriceOnZeroBidAmount(){
		assertEquals(false, myItem.isValidBidPrice(0));
	}
	
	@Test
	public void testValidBidPriceOnLessThanMinimumBidAmount(){
		assertEquals(false, myItem.isValidBidPrice(25));
	}
	
	@Test
	public void testValidBidPriceOnEqualToMinimumBidAmount(){
		assertEquals(true, myItem.isValidBidPrice(50));
	}
	
	@Test
	public void testValidBidPriceOnGreaterThanMinimumBidAmount(){
		assertEquals(true, myItem.isValidBidPrice(75));
	}
	
	@Test
	public void testIsValidBidderOnNonProfit() {
		
		assertFalse(myItem.isValidBidder(myNonProfit1));	
	}
	
	@Test
	public void testIsValidBidderOnStaff() {
		
		assertFalse(myItem.isValidBidder(myStaff1));	
	}
	
	@Test
	public void testIsValidBidderOnRegisteredBidder() {
		
		assertTrue(myItem.isValidBidder(myBidder1));	
	}
	
	@Test
	public void testIsValidBidderOnUnRegisteredBidder() {
		
		assertFalse(myItem.isValidBidder(myUnregisterBidder));	
	}
	
	
	@Test
	public void testCancelBidOnUserIsStaffMember() {		
	
		assertFalse(myItem.cancelBid(myStaff1, myDateMoreThan2Days, myCurrDate));		
	}
	
	@Test
	public void testCancelBidOnUserIsNonProfit() {		
	
		assertFalse(myItem.cancelBid(myNonProfit1, myDateMoreThan2Days, myCurrDate));		
	}
	
	@Test
	public void testCancelBidOnUserIsBidderMoreThanTwoDaysBeforeAuciton() {		
		
		assertTrue(myItem2.cancelBid(myBidder1, myDateMoreThan2Days, myCurrDate));		
	}
	
	@Test
	public void testCancelBidOnUserIsBidderAtTwoDaysBeforeAuction() {
		
		assertTrue(myItem2.cancelBid(myBidder1, myDateEqualTo2Days, myCurrDate));		
	}
	
	@Test
	public void testCancelBidOnUserIsBidderLessThanTwoDaysBeforeAuction() {
		
		assertFalse(myItem2.cancelBid(myBidder1, myDateLessThan2Days, myCurrDate));	
	}
	
	@Test
	public void testCancelBidOnUserIsBidderWhoDoesntHaveABid() {
		
		assertFalse(myItem.cancelBid(myBidder1, myDateMoreThan2Days, myCurrDate));	
	}
	
}
