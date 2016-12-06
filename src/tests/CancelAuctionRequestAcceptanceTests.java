/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */
package tests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import model.AuctionRequest;
import model.Calendar;
import model.Date;
import model.NonProfit;
import model.Time;

public class CancelAuctionRequestAcceptanceTests {

	private NonProfit myNonProfit = new NonProfit("Bill Gates", "bill", "1234",
			"Bill", "1234 Mercer Island", "111-111-1111");
	private Date d1 = new Date(20, "december", 2016);
	private Time t1 = new Time(2, 40, "pm");

	private LocalDate myLocalDate;	
	private Date myCurrDate;
	private Date myDateMoreThan2Days;
	private Date myDateEqualTo2Days;
	private Date myDateLessThan2Days;
	
	@Before
	public void setup() {
		
		myLocalDate = LocalDate.now();		
		myCurrDate = new Date(myLocalDate.getDayOfMonth(), myLocalDate.getMonth().toString(), myLocalDate.getYear());
		
		myDateMoreThan2Days = new Date(myCurrDate.getDay() + 5, myCurrDate.getMonth(), myCurrDate.getYear());
		myDateEqualTo2Days = new Date(myCurrDate.getDay() + 2, myCurrDate.getMonth(), myCurrDate.getYear());
		myDateLessThan2Days = new Date(myCurrDate.getDay() + 1, myCurrDate.getMonth(), myCurrDate.getYear());
		
	}

	/**
	 * Acceptance Test for : Cancelling an auction that exists for more than 2 days
	 */
	@Test
	public void testCancelAuctionOnAuctionExsistsForNonProfitMoreThan2Days(){
		
		Calendar c = new Calendar();		
		c.addAuction(new AuctionRequest(d1, t1, myNonProfit.getOrgName()));		
		assertTrue(c.cancelAuction(myNonProfit, myDateMoreThan2Days, myCurrDate));
		
	}
	
	/**
	 * Acceptance Test for : Cancelling an auction that exists for less than 2 days
	 */
	@Test
	public void testCancelAuctionOnAuctionExsistsForNonProfitLessThan2Days(){
		
		Calendar c = new Calendar();		
		c.addAuction(new AuctionRequest(d1, t1, myNonProfit.getOrgName()));		
		assertFalse(c.cancelAuction(myNonProfit, myDateLessThan2Days, myCurrDate));
		
	}
	
	/**
	 * Acceptance Test for : Cancelling an auction that exists for exactly 2 days
	 */
	@Test
	public void testCancelAuctionOnAuctionExsistsForNonProfitEqualTo2Days(){
		
		Calendar c = new Calendar();		
		c.addAuction(new AuctionRequest(d1, t1, myNonProfit.getOrgName()));		
		assertTrue(c.cancelAuction(myNonProfit, myDateEqualTo2Days, myCurrDate));
		
	}
	
	
	/**
	 * Acceptance Test for : Cancelling an auction that does not exist
	 */
	@Test
	public void testCancelAuctionOnAuctionDoesntExistForNonProfit(){
		Calendar c = new Calendar();	
		assertFalse(c.cancelAuction(myNonProfit, myDateMoreThan2Days, myCurrDate));		
	}
	
	

}
