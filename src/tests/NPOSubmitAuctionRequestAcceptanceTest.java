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
	
	private LocalDate today = LocalDate.now();
	
	private Time auctionTime = new Time(05, 00, "PM");
	
	
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
							new Time(9, 00, "AM"), "Non Profit4");

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
	
	/**
	 * Acceptance test for: Not NPO users trying to submit request
	 */
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
	
	/**
	 * Acceptance test for: maximum of one future auction for 
	 * this NPO who has one future auction
	 */
	@Test
	public void testMaxOneFutureAuctionOnOneFutureAuction(){
	
		c1.addAuction(myAuctionRequest1);
		
		assertFalse(c1.canAddAuction(myAuctionRequest2));
	}
	
	/**
	 * Acceptance test for: maximum of one future auction for 
	 * this NPO who has more than one future auction
	 */
	@Test
	public void testMaxOneFutureAuctionOnMoreThanOneFutureAuction(){
		
		c1.addAuction(myAuctionRequest1);
													
		c1.addAuction(myAuctionRequest2);
		
		assertFalse(c1.canAddAuction(myAuctionRequest3));
	}
	
	/**
	 * Acceptance test for: no auctions within the past year 
	 * for this NPO
	 */
	

	@Test
	public void testNoAuctionInPastYearOnOneYearMinusOneDayInPast(){

		AuctionRequest auction = new AuctionRequest(new Date(today.minusDays(1).getDayOfMonth(),
				"December", 2015), auctionTime, "RedRum");
		AuctionRequest request = new AuctionRequest(new Date(30, "December", 2016), auctionTime, "RedRum");
		c2.getCalendar().get(365).addAuction(auction);
		assertFalse(c2.canAddAuction(request));
	}
	
	/**
	 * Acceptance test for: no auctions within the past year 
	 * for this NPO who has multiple auctions less than year in past
	 */
	@Test
	public void testNoAuctionInPastYearOnMultipleAuctionsLessThanYearInPast(){
		
		AuctionRequest auction1 = new AuctionRequest(new Date(19, "May", 2016), auctionTime, "RedRum");
		AuctionRequest auction2 = new AuctionRequest(new Date(27, "August", 2016), auctionTime, "RedRum");
		AuctionRequest request = new AuctionRequest(new Date(30, "December", 2016), auctionTime, "RedRum");
		
		c2.getCalendar().get(60).addAuction(auction1);
		c2.getCalendar().get(180).addAuction(auction2);
		
		assertFalse(c2.canAddAuction(request));
	}
	
	/**
	 * Acceptance test for: no auctions within the past year 
	 * for this NPO on auction within past year
	 */
	@Test
	public void testNoAuctionInPastYearOnNoAuctionWithInPastYear(){
		
		AuctionRequest auction1 = new AuctionRequest(new Date(19, "December", 2015), auctionTime, "RedRum");
		AuctionRequest request = new AuctionRequest(new Date(30, "December", 2016), auctionTime, "RedRum");
		c2.getCalendar().get(5).addAuction(auction1);

		assertFalse(c2.canAddAuction(request));
	}
	
	/**
	 * Acceptance test for: no auctions within the past year 
	 * for this NPO who had an auction exactly one year in the past
	 */
	@Test
	public void testNoAuctionInPastYearOnHadAuctionExactlyOneYearInPast(){
		AuctionRequest auction1 = new AuctionRequest(new Date(today.getDayOfMonth(), "December", 2015), auctionTime, "RedRum");
		AuctionRequest request = new AuctionRequest(new Date(30, "December", 2016), auctionTime, "RedRum");
		c2.getCalendar().get(0).addAuction(auction1);
		assertFalse(c2.canAddAuction(request));
	}
	
	/**
	 * Acceptance test for: no auctions within the past year 
	 * for this NPO who had no auction in the past
	 */
	@Test
	public void testNoAuctionInPastYearOnNoAuctionsInPast(){
		AuctionRequest request = new AuctionRequest(new Date(30, "December", 2016), auctionTime, "RedRum");
		
		assertTrue(c2.addAuction(request));
		
	}
	
	/**
	 * Acceptance test for: maximum of two auctions per day on no auctions
	 */
	
	@Test
	public void testMaxTwoAuctionPerDayOnNoAuctionsScheduled(){
		
		assertTrue(testDay.canAddAuction());
	}
	
	/**
	 * Acceptance test for: maximum of two auctions per day with one auction scheduled
	 */
	@Test
	public void testMaxTwoAuctionPerDayOnOneAuctionScheduled(){
	
		testDay.addAuction(dayAuction1);
		
		assertTrue(testDay.canAddAuction());
	}
	
	/**
	 * Acceptance test for: maximum of two auctions per day with two auctions scheduled
	 */
	@Test
	public void testMaxTwoAuctionPerDayOnTwoAuctionsScheduled(){
		
		testDay.addAuction(dayAuction1);
		testDay.addAuction(dayAuction2);
		
		assertFalse(testDay.canAddAuction());
	}
	
	
	/**
	 * Acceptance test for: maximum of two auctions per day with more than two auctions scheduled
	 */
	@Test
	public void testMaxTwoAuctionPerDayOnMoreThanTwoAuctionsScheduled(){
		
		testDay.addAuction(dayAuction1);
		testDay.addAuction(dayAuction2);
		
		testDay.setMyNumAuctions(0); 
		testDay.addAuction(dayAuction3);
		testDay.setMyNumAuctions(2);
		
		assertFalse(testDay.canAddAuction());
	}
	
	/**
	 * Acceptance test for: maximum of 25 upcoming auctions
	 */
	
	@Test
	public void testMaxTwentyFiveAuctionsOnLessThanTwentyFourScheduled(){
		
		c2.setAuctionsTotal(20);
		
		assertTrue(c2.addAuction(myAuctionRequest1));
	}
	
	/**
	 * Acceptance test for: maximum of 25 upcoming auctions with 24 scheduled
	 */
	
	@Test
	public void testMaxTwentyFiveAuctionsOnTwentyFourScheduled(){
		
		c2.setAuctionsTotal(24);
		
		assertTrue(c2.addAuction(myAuctionRequest1));
	}
	
	/**
	 * Acceptance test for: maximum of 25 upcoming auctions with 25 scheduled
	 */
	
	@Test
	public void testMaxTwentyFiveAuctionsOnTwentyFiveScheduled(){
		
		c2.setAuctionsTotal(25);
		
		assertFalse(c2.addAuction(myAuctionRequest1));
	}
	
	/**
	 * Acceptance test for: maximum of 25 upcoming auctions with more than 25 scheduled
	 */
	
	
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
	
	/**
	 * Acceptance test for: checking date field 
	 */
	@Test
	public void testAllRequiredFieldsToSubmitRequestOnOneFieldMissingDate(){
	
		assertFalse(noDateRequest.checkRequiredField());
	}
	
	/**
	 *  Acceptance test for: checking time field
	 */
	@Test
	public void testAllRequiredFieldsToSubmitRequestOnOneFieldMissingTime(){
		
		assertFalse(noTimeRequest.checkRequiredField());
	}
	
	/**
	 *  Acceptance test for: checking all the required fields
	 */
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
				
		assertTrue(c1.checkWeek(today.plusDays(7).getDayOfMonth()));
	}
	
	/**
	 * Acceptance test for: auction date is greater than one week
	 */
	@Test
	public void testAtLeastOneWeekBeforeOnGreaterThanOneWeek(){
		
		assertTrue(c1.checkWeek(today.plusDays(10).getDayOfMonth()));
	}
	
	/**
	 * Acceptance test for: auction date is at least 6 days before
	 */
	@Test
	public void testAtLeastOneWeekBeforeOnSixDays(){
		
		assertFalse(c1.checkWeek(today.plusDays(6).getDayOfMonth()));
	}
	
	/**
	 * Acceptance test for: auction date is less than six days
	 */
	@Test
	public void testAtLeastOneWeekBeforeOnLessThanSixDays(){
		
		assertFalse(c1.checkWeek(today.plusDays(3).getDayOfMonth()));
	}
	
	/**
	 * Acceptance test for: auction date is at current date
	 */
	@Test
	public void testAtLeastOneWeekBeforeOnCurrentDate(){
		
		assertFalse(c1.checkWeek(today.getDayOfMonth()));
	}
	
	/**
	 * Acceptance test for: auction date is one day in past.
	 */
	@Test
	public void testAtLeastOneWeekBeforeOnDateInPast(){

		assertFalse(c1.checkWeek(today.minusDays(2).getDayOfMonth()));
	}
	
}
