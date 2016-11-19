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
import model.Item;
import model.NonProfit;
import model.Time;
import model.Auction;
//hi
/**
 * Test class for Auction class.
 * @author Dmitriy Onishchenko, Colin Casey 
 *
 */
public class AuctionTest {

	private static final String NONPROFITNAME = "Bill and Melinda Gates";
		
	private Item myItemTestingAgainst;
	private Item myItem1;
	private Item myItem2;
	private Item myItem3;
	
	private Auction myAuction;
	private NonProfit myNonProfit;
	private NonProfit myNonProfit2;
	
	
	@Before
	public void setUp() {
		
		myNonProfit = new NonProfit("Bill Gates", "billy", "1234",
				"Bill and Melinda Gates", "1234 Mercer Island", "111-111-1111");

		myNonProfit2 = new NonProfit("Billy Jean", "jeans", "1234",
				"Jackson Organization", "1234 Mercer Island", "111-111-1111");
		
		myAuction = new Auction(NONPROFITNAME, new Date(12, "December", 2016), 
								new Time(2, 00, "PM"));
		
		
		// populate Auction inventory
		myItemTestingAgainst = new Item("Football", "good", "small", 100, NONPROFITNAME, 
				"Really cool football", "hello");
		
		
		
		myItem1 = new Item("", "good", "small", 100, NONPROFITNAME, 
				"Really cool football", "hello");
		
		myItem2 = new Item("Football", "", "small", 100, NONPROFITNAME, 
				"Really cool football", "hello");
		
		myItem3 = new Item("Football", "good", "", 100, NONPROFITNAME, 
				"Really cool football", "hello");

		myAuction.addItem(myNonProfit, myItemTestingAgainst);
		
	}
	
	@Test
	public void testCheckRequiredItemFieldsNameIsEmpty() {		
		assertFalse(myAuction.checkRequiredItemFields(myItem1));
		
	}
	
	@Test
	public void testCheckRequiredItemFieldsConditionIsEmpty() {		
		assertFalse(myAuction.checkRequiredItemFields(myItem2));
		
	}
	
	@Test
	public void testCheckRequiredItemFieldsSizeIsEmpty() {		
		assertFalse(myAuction.checkRequiredItemFields(myItem3));
		
	}
	
	@Test
	public void testCheckRequiredItemFieldsMinBidIsNegative() {	
		
		Item itemTest = new Item("Football", "good", "small", -1, "Bill and Melinda Gates", 
				"Really cool football", "hello");
		
		assertFalse(myAuction.checkRequiredItemFields(itemTest));
		
	}
	
	@Test
	public void testCheckRequiredItemFieldsMinBidIsZero() {	
		
		Item itemTest = new Item("Football", "good", "small", 0, "Bill and Melinda Gates", 
				"Really cool football", "hello");
		
		assertFalse(myAuction.checkRequiredItemFields(itemTest));
		
	}
	
	@Test
	public void testCheckRequiredItemFieldsMinBidIsPositive() {	
		
		Item itemTest = new Item("Football", "good", "small", 1, "Bill and Melinda Gates", 
				"Really cool football", "hello");
		
		assertTrue(myAuction.checkRequiredItemFields(itemTest));
		
	}
	
	@Test
	public void testCheckContactPersonSameOrganization() {		
		assertTrue(myAuction.checkContactPerson(myNonProfit));	
		
	}
	
	@Test
	public void testCheckContactPersonDifferntOrganization() {		
		assertFalse(myAuction.checkContactPerson(myNonProfit2));	
		
	}
	
	@Test
	public void testCheckForDuplicatesNone() {		
		Item item = new Item("ball", "good", "small", 100, "Melinda Gates", 
				"Really cool ball", "hello");
		assertTrue(myAuction.checkForDuplicates(item));	
		
	}
	
	@Test
	public void testCheckForDuplicatesOne() {	
		Item item = new Item("Football", "good", "small", 100, "Bill and Melinda Gates", 
				"Really cool football", "hello");
		assertFalse(myAuction.checkForDuplicates(item));	
		
	}
	
	@Test
	public void testAddItemTrue() {	
		Item item = new Item("ball", "good", "small", 100, "Melinda Gates", 
				"Really cool football", "hello");
		assertTrue(myAuction.addItem(myNonProfit, item));	
		
	}
	@Test
	public void testAddItemFalse() {
		Item item = new Item("Football", "good", "small", 100, "Bill and Melinda Gates", 
				"Really cool football", "hello");
		assertTrue(myAuction.addItem(myNonProfit, item));	
		
	}
	
	@Test
	public void testToString() {

		assertEquals(NONPROFITNAME + ", December 12, 2016, 2:00 PM", myAuction.toString());
		
	}
}
