package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Auction;
import model.Date;
import model.Item;
import model.NonProfit;
import model.Staff;
import model.Time;
public class NPOAddInventoryItemAcceptanceTest {

	private static final String NONPROFITNAME = "Bill and Melinda Gates";

	/** The test contact person from non profit. */
	private NonProfit myNonProfit1;
	
	/** A different contact person from non profit. */
	private NonProfit myNonProfit2;
	
	/** The main test item */
	private Item myTestItem;
	
	/** The test item where the item's field name is empty */
	private Item myItem1;
	
	/** The test item where the item's condition is empty */
	private Item myItem2;
	
	/** The test item where the item's size is empty */
	private Item myItem3;
	
	/** The new test item which does not exist in the auction */
	private Item myNewItem;
	
	/** The test item used for checking duplicates. */
	private Item myDuplicateItem;
	
	private Auction myAuction;


	
	@Before
	public void setup() {
		myNonProfit1 = new NonProfit("Bill Gates", "billy", "1234",
				"Bill and Melinda Gates", "1234 Mercer Island", "111-111-1111");

		myNonProfit2 = new NonProfit("Billy Jean", "jeans", "1234",
				"Jackson Organization", "1234 Mercer Island", "111-111-1111");
		
		
		myAuction = new Auction(NONPROFITNAME, new Date(12, "December", 2016), 
								new Time(2, 00, "PM"));

		myTestItem = new Item("Football", "good", "small", 100, NONPROFITNAME, 
				"Really cool football", "hello");
		
		myItem1 = new Item("", "good", "small", 100, NONPROFITNAME, 
				"Really cool football", "hello");
		
		myItem2 = new Item("Football", "", "small", 100, NONPROFITNAME, 
				"Really cool football", "hello");
		
		myItem3 = new Item("Football", "good", "", 100, NONPROFITNAME, 
				"Really cool football", "hello");

		myNewItem = new Item("ball", "good", "small", 100, "Melinda Gates", 
				"Really cool ball", "hello");
		
		myDuplicateItem = new Item("Football", "good", "small", 100, "Bill and Melinda Gates", 
				"Really cool football", "hello");
		
		myAuction.addItem(myNonProfit1, myTestItem);
	}
	
	/**
	 *  Acceptance test for: user is the contact person for non-profit organization associated with this auction
	 */
	@Test
	public void testCanAddInventoryItemOnUserBeingTheContactFromNPO() {
		assertTrue(myAuction.checkContactPerson(myNonProfit1));
	}
	
	/**
	 *  Acceptance test for: user is not the contact person for non-profit organization associated with this auction
	 */
	@Test
	public void testCanAddInventoryItemOnUserNotBeingTheContactFromNPO() {
		assertFalse(myAuction.checkContactPerson(myNonProfit2));
	}
	
	/**
	 * Acceptance test for: adding the item that already exists in the auction
	 */
	@Test
	public void testAddInventoryItemOnTheSameItem() {
		assertFalse(myAuction.checkForDuplicates(myDuplicateItem));
	}
	
	/**
	 * Acceptance test for: adding a new item that does not exist in the auction
	 */
	@Test
	public void testAddInventoryItemOnNewItem() {
		assertTrue(myAuction.checkForDuplicates(myNewItem));
	}
	
	/**
	 * Acceptance Test for: Adding an item without filling in the Item's name field
	 */
	@Test
	public void testCheckRequiredItemFieldOnItemName() {		
		assertFalse(myAuction.checkRequiredItemFields(myItem1));	
	}
	
	/**
	 * Acceptance Test for: Adding an item without filling in the Item's conditions field
	 */
	@Test
	public void testCheckRequiredItemFieldOnItemConditions() {		
		assertFalse(myAuction.checkRequiredItemFields(myItem2));
	}
	
	/**
	 * Acceptance Test for: Adding an item without filling in the Item's size field
	 */
	@Test
	public void testCheckRequiredItemFieldOnItemSize() {		
		assertFalse(myAuction.checkRequiredItemFields(myItem3));
	}
	
	/**
	 * Acceptance Test for: checking minimum bid is not negative
	 */
	@Test
	public void testCheckRequiredItemFieldOnNonNegativeMinimumBids() {	
		
		Item itemTest = new Item("Football", "good", "small", -1, "Bill and Melinda Gates", 
				"Really cool football", "hello");
		
		assertFalse(myAuction.checkRequiredItemFields(itemTest));
	}
	
	/**
	 * Acceptance Test for: checking minimum bid is not zero
	 */
	@Test
	public void testCheckRequiredItemFieldsOnZeroMinimumBid() {	
		
		Item itemTest = new Item("Football", "good", "small", 0, "Bill and Melinda Gates", 
				"Really cool football", "hello");
		
		assertFalse(myAuction.checkRequiredItemFields(itemTest));
	}
	
	/**
	 * Acceptance Test for: checking minimum bid is positive
	 */
	@Test
	public void testCheckRequiredItemFieldsOnPostiveMinimumBids() {			
		Item itemTest = new Item("Football", "good", "small", 1, "Bill and Melinda Gates", 
				"Really cool football", "hello");
		
		assertTrue(myAuction.checkRequiredItemFields(itemTest));
	}
	

}
