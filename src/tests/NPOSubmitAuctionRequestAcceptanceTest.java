package tests;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import model.Auction;
import model.AuctionRequest;
import model.Calendar;
import model.Date;
import model.Day;
import model.NonProfit;
import model.Time;

/**
 * This test class runs the acceptance tests for the user story
 * "As a contact for a NPO, I want to submit an auction request".
 * @author Georgia Wade
 *
 */
public class NPOSubmitAuctionRequestAcceptanceTest {
	
	private static final String NONPROFITNAME = "Bill and Melinda Gates";
	
	private Calendar c1;
	private Calendar c2;
	
	private Auction myAuction1;
	
	private AuctionRequest myAuctionRequest1;
	private AuctionRequest myAuctionRequest2;
	private AuctionRequest myAuctionRequest3;
	
	private AuctionRequest dayAuction1;
	private AuctionRequest dayAuction2;
	private AuctionRequest dayAuction3;
	private AuctionRequest dayAuction4;
	
	private AuctionRequest noDateRequest;
	private AuctionRequest noTimeRequest;
	
	private NonProfit myNonProfit1;
	private NonProfit myNonProfit2;
	
	private Day testDay;
	
	
	@Before
	public void setUp() {
		
		c1 = new Calendar();
		c2 = new Calendar();
		
		myAuction1 = new Auction(NONPROFITNAME, new Date(12, "December", 2016), new Time(02, 00, "PM"));

		myAuctionRequest1 = new AuctionRequest(new Date(18, "December", 2016),
									new Time(03, 00, "PM"), "Non Profit");
		myAuctionRequest2 = new AuctionRequest(new Date(20, "December", 2016),
									new Time(05, 00, "PM"), "Non Profit");
		myAuctionRequest3 = new AuctionRequest(new Date(27, "December", 2016),
									new Time(07, 00, "PM"), "Non Profit");
		
		dayAuction1 = new AuctionRequest(new Date(28, "December", 2016),
							new Time(01, 00, "PM"), "Non Profit1");
		dayAuction2 = new AuctionRequest(new Date(28, "December", 2016),
							new Time(04, 00, "PM"), "Non Profit2");
		dayAuction3 = new AuctionRequest(new Date(28, "December", 2016),
							new Time(07, 00, "PM"), "Non Profit3");
		dayAuction4 = new AuctionRequest(new Date(28, "December", 2016),
							new Time(9, 00, "AM"), "Non Profit1");

		noDateRequest = new AuctionRequest(null, new Time(07, 00, "PM"), "WWF");
		noTimeRequest = new AuctionRequest(new Date(28, "December", 2016), null, "WWF");
		
		myNonProfit1 = new NonProfit("Bill Gates", "billy", "1234", "Bill and Melinda Gates",
							"1234 Mercer Island", "111-111-1111");
		myNonProfit2 = new NonProfit("Billy Jean", "jeans", "1234", "Jackson Organization",
							"1234 Mercer Island", "111-111-1111");

		testDay = new Day("december", 28, 2016);
	}
	
	
	/**
	 * Acceptance test for: only the contact for this NPO can
	 * submit an auction request
	 */
	
	@Test
	public void testOnlyNPOContactSubmitRequestOnNPOContact(){
		
		assertTrue(myAuction1.checkContactPerson(myNonProfit1));
	}
	
	@Test
	public void testOnlyNPOContactSubmitRequestOnNotNPOContact(){
		
		assertFalse(myAuction1.checkContactPerson(myNonProfit2));
	}
	
	/**
	 * Acceptance test for: maximum of one future auction for 
	 * this NPO
	 */
	
	@Test
	public void testMaxOneFutureAuctionOnNoFutureAuction(){
		
		assertTrue(c1.canAddAuction(myAuctionRequest1));
	}
	
	@Test
	public void testMaxOneFutureAuctionOnOneFutureAuction(){
	
		c1.addAuction(myAuctionRequest1);
		
		assertFalse(c1.canAddAuction(myAuctionRequest2));
	}
	
	@Test
	public void testMaxOneFutureAuctionOnMoreThanOneFutureAuction(){
		
		c1.addAuction(myAuctionRequest1);
		//FIX IT
		c1.addAuction(myAuctionRequest2);
		
		assertFalse(c1.canAddAuction(myAuctionRequest3));
	}
	
	/**
	 * Acceptance test for: no auctions within the past year 
	 * for this NPO
	 */
	
	@Test
	public void testNoAuctionInPastYearOnLessThanYearInPast(){
		
	}

	@Test
	public void testNoAuctionInPastYearOnOneYearMinusOneDayInPast(){
	
	}
	
	@Test
	public void testNoAuctionInPastYearOnMultipleAuctionsLessThanYearInPast(){
				
	}
	
	@Test
	public void testNoAuctionInPastYearOnNoAuctionWithInPastYear(){
		
	}
	
	@Test
	public void testNoAuctionInPastYearOnHadAuctionExactlyOneYearInPast(){
	
	}
	
	@Test
	public void testNoAuctionInPastYearOnNoAuctionsInPast(){
		
	}
	
	/**
	 * Acceptance test for: maximum of two auctions per day
	 */
	
	@Test
	public void testMaxTwoAuctionPerDayOnNoAuctionsScheduled(){
		
		assertTrue(testDay.canAddAuction(dayAuction1));
	}
	
	@Test
	public void testMaxTwoAuctionPerDayOnOneAuctionScheduled(){
	
		testDay.addAuction(dayAuction1);
		
		assertTrue(testDay.canAddAuction(dayAuction2));
	}
	
	@Test
	public void testMaxTwoAuctionPerDayOnTwoAuctionsScheduled(){
		
		testDay.addAuction(dayAuction1);
		testDay.addAuction(dayAuction2);
		
		assertFalse(testDay.canAddAuction(dayAuction3));
	}
	
	@Test
	public void testMaxTwoAuctionPerDayOnMoreThanTwoAuctionsScheduled(){
		
		testDay.addAuction(dayAuction1);
		testDay.addAuction(dayAuction2);
		
		testDay.setMyNumAuctions(0); 
		testDay.addAuction(dayAuction3);
		testDay.setMyNumAuctions(2);
		
		assertFalse(testDay.canAddAuction(dayAuction4));
	}
	
	/**
	 * Acceptance test for: maximum of 25 upcoming auctions
	 */
	
	@Test
	public void testMaxTwentyFiveAuctionsOnLessThanTwentyFourScheduled(){
		
		c2.setAuctionsTotal(20);
		
		assertTrue(c2.addAuction(myAuctionRequest1));
	}
	
	@Test
	public void testMaxTwentyFiveAuctionsOnTwentyFourScheduled(){
		
		c2.setAuctionsTotal(24);
		
		assertTrue(c2.addAuction(myAuctionRequest1));
	}
	
	@Test
	public void testMaxTwentyFiveAuctionsOnTwentyFiveScheduled(){
		
		c2.setAuctionsTotal(25);
		
		assertFalse(c2.addAuction(myAuctionRequest1));
	}
	
	@Test
	public void testMaxTwentyFiveAuctionsOnMoreThanTwentyFiveScheduled(){
		
		c2.setAuctionsTotal(26);
		
		assertFalse(c2.addAuction(myAuctionRequest1));
	}
	
	/**
	 * Acceptance test for: all require fields much be specified to submit an
	 * auction request.
	 * Required fields:
	 * auction date [DD/MM/YYYY]
	 * auction time [HH AM] or [HH PM]
	 */
	
	@Test
	public void testAllRequiredFieldsToSubmitRequestOnOneFieldMissingDate(){
	
		assertFalse(noDateRequest.checkRequiredField());
	}

	@Test
	public void testAllRequiredFieldsToSubmitRequestOnOneFieldMissingTime(){
		
		assertFalse(noTimeRequest.checkRequiredField());
	}
	
	@Test
	public void testAllRequiredFieldsToSubmitRequestOnAllFieldsFilled(){
		
		assertTrue(myAuctionRequest1.checkRequiredField());
	}
	
	/**
	 * Acceptance test for: auction date is at least one week from the date
	 * in which it is being added  
	 */
	
	@Test
	public void testAtLeastOneWeekBeforeOnExactlyOneWeek(){
		
		
	}
	
	@Test
	public void testAtLeastOneWeekBeforeOnGreaterThanOneWeek(){
		
	}
	
	@Test
	public void testAtLeastOneWeekBeforeOnSixDays(){
		
	}
	
	@Test
	public void testAtLeastOneWeekBeforeOnLessThanSixDays(){
		
	}
	
	@Test
	public void testAtLeastOneWeekBeforeOnCurrentDate(){
		
	}
	
	@Test
	public void testAtLeastOneWeekBeforeOnDateInPast(){
		
	}
	
}
