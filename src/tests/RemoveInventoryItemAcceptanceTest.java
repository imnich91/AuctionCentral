package tests;

import java.time.LocalDate;

import org.junit.Before;

import model.Bidder;
import model.Date;
import model.Item;

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
	private Bidder myBidder1;
	
	private LocalDate myLocalDate;	
	private Date myCurrDate;
	private Date myDateMoreThan2Days;
	private Date myDateEqualTo2Days;
	private Date myDateLessThan2Days;

	@Before
	public void setup() {
		myLocalDate = LocalDate.now();		
		myCurrDate = new Date(myLocalDate.getDayOfMonth(), myLocalDate.getMonth().toString(),
								myLocalDate.getYear());
		
		myDateMoreThan2Days = new Date(myCurrDate.getDay() - 5, myCurrDate.getMonth(), myCurrDate.getYear());
		myDateEqualTo2Days = new Date(myCurrDate.getDay() - 2, myCurrDate.getMonth(), myCurrDate.getYear());
		myDateLessThan2Days = new Date(myCurrDate.getDay() - 1, myCurrDate.getMonth(), myCurrDate.getYear());
		
	
		myBidder1 = new Bidder("Dave Mathews", "dave", "3456", 
				"d@gmail.com", "1111 11th st San Diego", "111-111-1111");
		
		myItem = new Item("Autographed Sounders Jersey", "Good", "medium",
				50, "Seattle Sounders", "Game worn jersy signed by Brad Evan.",
				"CenturyLink Field front office has the jersey");
		
		myItem2 = new Item("Football", "good", "small", 100, "Bill and Melinda Gates", 
						"Really cool football", "hello");		
		myItem2.makeBid(myBidder1, 300);
	}

}
