package tests;



import  org.junit.Assert.*;

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
 * Test methods for calendar class. 
 * @author Ian Nicholas
 *
 */
public class CalendarTest {
	
	private NonProfit myNonProfit = new NonProfit("Bill Gates", "bill", "1234",
				"Bill", "1234 Mercer Island", "111-111-1111");
	private Calendar c1;
	private Calendar c2;
	private Date d1 = new Date(8, "november", 2016);
	private Time t1 = new Time(2, 40, "pm");
	private Date d2 = new Date(25, "november", 2016);
	private Time t2 = new Time(7, 40, "pm");
	private Date d3 = new Date(35, "november", 2016);
	private Time t3 = new Time(7, 40, "pm");
	private Date d4 = new Date(5, "december", 2015);
	private Time t4 = new Time(7, 40, "pm");
	private Date d5 = new Date(1, "december", 2016);
	private Time t5 = new Time(7, 40, "pm");
	private Date d6 = new Date(26, "november", 2016);
	private Time t6 = new Time(7, 40, "pm");
	
	private AuctionRequest myRequest = new AuctionRequest(d1, t1, "Non-profit-1");
	private AuctionRequest myRequestDup = new AuctionRequest(d2, t2, "Non-profit-1");
	private AuctionRequest myRequestBadDay= new AuctionRequest(d3, t3, "Non-profit-2");
	private AuctionRequest myRequestPast1= new AuctionRequest(d4, t4, "Non-profit-1");
	private AuctionRequest myRequestPast2= new AuctionRequest(d4, t4, "Non-profit-2");
	private AuctionRequest myRequestValid= new AuctionRequest(d5, t5, "Non-profit-4");
	private AuctionRequest myRequestValid2= new AuctionRequest(d6, t6, "Non-profit-3");
	
	private Day day1 = new Day("december", 5, 2015);
	private Day oneYearAgoToday = new Day(LocalDate.now().minusMonths(12).getMonth().name(), 
					   LocalDate.now().minusMonths(12).getDayOfMonth(), 
					   LocalDate.now().minusMonths(12).getYear());
	
	
	private LocalDate myLocalDate;	
	private Date myCurrDate;
	private Date myDateMoreThan2Days;
	private Date myDateEqualTo2Days;
	private Date myDateLessThan2Days;
	
	
	@Before
	public void setup() {
		c1 = new Calendar();
		c2 = new Calendar();
		day1.addAuction(myRequestPast1);
		day1.addAuction(myRequestPast2);
		oneYearAgoToday.addAuction(myRequestValid2);
		
		
		myLocalDate = LocalDate.now();		
		myCurrDate = new Date(myLocalDate.getDayOfMonth(), myLocalDate.getMonth().toString(), myLocalDate.getYear());
		
		myDateMoreThan2Days = new Date(myCurrDate.getDay() - 5, myCurrDate.getMonth(), myCurrDate.getYear());
		myDateEqualTo2Days = new Date(myCurrDate.getDay() - 2, myCurrDate.getMonth(), myCurrDate.getYear());
		myDateLessThan2Days = new Date(myCurrDate.getDay() - 1, myCurrDate.getMonth(), myCurrDate.getYear());
		
		
	}
	
	@Test
	public void testCheckMonthYearOnSameMonthYear() {
		assertTrue("test case same month", c2.checkMonthYear(myRequest.getDate().getMonth(), myRequest.getDate().getYear()));
	}
	
	@Test
	public void testCheckMonthYearOnWrongMonthYear() {
		assertFalse("test case wrong month", c2.checkMonthYear("January", 2013));
	}

	@Test
	public void testCheckDayOnValidDay() {
		assertTrue("test case valid day", c2.checkDay(myRequest.getDate().getDay(), myRequest));
	}
	
	@Test
	public void testCheckDayOnInvalidDay() {
		assertFalse("test case invalid day", c2.checkDay(myRequestBadDay.getDate().getDay(), myRequestBadDay));
	}
	
	@Test
	public void testCheckAuctionExistOnNonProfitWithNoAuctions() {
		assertTrue("test case no other auctions", c2.checkAuctionExist(myRequest.getNonProfitName()));
	}

	@Test
	public void testCheckLastYearOnNoAuctionInPastYear() {
		assertTrue("test case no auction in last year", c2.checkLastYear(myRequest.getNonProfitName()));
	}
	
	@Test
	public void testCheckLastYearOnAnAuctionInPastYearAndOnlyOneAuctionOnDay() {
		for(int i = 0; i < c2.getCalendar().size(); i++) {
			if(c2.getCalendar().get(i).getDay() == day1.getDay() && 
				c2.getCalendar().get(i).getMonth().equals(day1.getMonth()) && 
				c2.getCalendar().get(i).getYear() == (day1.getYear())) {
					c2.getCalendar().get(i).addAuction(myRequestPast1);
			}
		}
		assertFalse("test case auction in last year already", c2.checkLastYear(myRequest.getNonProfitName()));
	}
	
	@Test
	public void testCheckLastYearOnAnAuctionInPastYearAnTwoAuctionsOnDay() {
		for(int i = 0; i < c2.getCalendar().size(); i++) {
			if(c2.getCalendar().get(i).getDay() == day1.getDay() && 
				c2.getCalendar().get(i).getMonth().equals(day1.getMonth()) && 
				c2.getCalendar().get(i).getYear() == (day1.getYear())) {
					c2.getCalendar().get(i).addAuction(myRequestPast1);
					c2.getCalendar().get(i).addAuction(myRequestPast2);
			}
		}
		assertFalse("test case auction in last year already", c2.checkLastYear(myRequestPast2.getNonProfitName()));
	}
	
	@Test
	public void testCheckLastYearOnAnAuctionExactlyOneYearAgo() {
		for(int i = 0; i < c2.getCalendar().size(); i++) {
			if(c2.getCalendar().get(i).getDay() == day1.getDay() && 
				c2.getCalendar().get(i).getMonth().equals(day1.getMonth()) && 
				c2.getCalendar().get(i).getYear() == (day1.getYear())) {
					c2.getCalendar().get(i).addAuction(myRequestPast1);
			}
		}
		assertFalse("test case auction in last year already", c2.checkLastYear(myRequest.getNonProfitName()));
	}
	
	
	@Test
	public void testCheckTotalAuctionsOnLessThanAuctionLimit() {
		c2.setAuctionsAllowed(31);
		c2.setAuctionsTotal(30);
		assertTrue("test auction amount less than Auctions allowed passes", c2.checkTotalAuctions());		
	}
	
	@Test
	public void testCheckTotalAuctionsOnGreaterThanAuctionLimit() {
		c2.setAuctionsAllowed(20);
		c2.setAuctionsTotal(25);
		assertFalse("test auction amount greater than Auctions allowed fails", c2.checkTotalAuctions());		
	}
	
	@Test
	public void testCheckWeekOnOutsideOfSevenDays() {
		assertTrue("test case check week true", c2.checkWeek(6));
	}
	
	@Test
	public void testCheckWeekOnInsideOfSevenDays() {
		assertFalse("test case check week false", c2.checkWeek(25));
	}
	
	@Test
	public void testAddAuctionOnValidRequest() {
		assertTrue("test case add auction valid request", c2.addAuction(myRequestValid));
	}
	
	@Test
	public void testAddAuctionOnInvalidRequest() {
		assertFalse("test case add auction valid request", c2.addAuction(myRequestPast1));
	}
		
	@Test
	public void testGetAuctionOnOrganizationExist() {	
		Calendar c = new Calendar();
		AuctionRequest myRequest = new AuctionRequest(new Date(1, "december", 2016), new Time(2, 40, "PM"), "Bill");
		
		c.addAuction(myRequest);		

		Auction testAuction = new Auction("Bill", new Date(1, "december", 2016), new Time(2, 40, "PM"));		
		assertEquals(testAuction.getName(), c.getAuctionForOrganization(myNonProfit).getName());
		
	}
	
	
	@Test 
	public void testIsMoreThanTwoDaysOnLessThan2Days() {
		
		assertFalse(Calendar.isMoreThanTwoDays(myDateLessThan2Days, myCurrDate));	
		
	}
	
	@Test 
	public void testIsMoreThanTwoDaysOnMoreThan2Days() {
		
		assertTrue(Calendar.isMoreThanTwoDays(myDateMoreThan2Days, myCurrDate));	
		
	}
	
	@Test 
	public void testIsMoreThanTwoDaysOnEqualTo2Days() {
		
		assertTrue(Calendar.isMoreThanTwoDays(myDateEqualTo2Days, myCurrDate));	
		
	}
	
	
	public void testCancelAuctionOnAuctionExsistsForNonProfitMoreThan2Days(){
		
		Calendar c = new Calendar();		
		c.addAuction(new AuctionRequest(d1, t1, myNonProfit.getOrgName()));		
		assertTrue(c.cancelAuction(myNonProfit, myDateMoreThan2Days, myCurrDate));
		
	}
	
	
	public void testCancelAuctionOnAuctionExsistsForNonProfitLessThan2Days(){
		
		Calendar c = new Calendar();		
		c.addAuction(new AuctionRequest(d1, t1, myNonProfit.getOrgName()));		
		assertFalse(c.cancelAuction(myNonProfit, myDateLessThan2Days, myCurrDate));
		
	}
	
	public void testCancelAuctionOnAuctionExsistsForNonProfitEqualTo2Days(){
		
		Calendar c = new Calendar();		
		c.addAuction(new AuctionRequest(d1, t1, myNonProfit.getOrgName()));		
		assertTrue(c.cancelAuction(myNonProfit, myDateEqualTo2Days, myCurrDate));
		
	}
	
	public void testCancelAuctionOnAuctionDoesntExistForNonProfit(){
		Calendar c = new Calendar();	
		assertFalse(c.cancelAuction(myNonProfit, myDateMoreThan2Days, myCurrDate));		
	}
	
	

}
