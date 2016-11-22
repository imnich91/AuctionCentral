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

public class CalendarTest {
	
	NonProfit myNonProfit = new NonProfit("Bill Gates", "bill", "1234",
				"Bill", "1234 Mercer Island", "111-111-1111");
	Calendar c1;
	Calendar c2;
	Date d1 = new Date(8, "november", 2016);
	Time t1 = new Time(2, 40, "pm");
	Date d2 = new Date(25, "november", 2016);
	Time t2 = new Time(7, 40, "pm");
	Date d3 = new Date(35, "november", 2016);
	Time t3 = new Time(7, 40, "pm");
	Date d4 = new Date(5, "january", 2015);
	Time t4 = new Time(7, 40, "pm");
	Date d5 = new Date(1, "december", 2016);
	Time t5 = new Time(7, 40, "pm");
	Date d6 = new Date(26, "november", 2016);
	Time t6 = new Time(7, 40, "pm");
	
	AuctionRequest myRequest = new AuctionRequest(d1, t1, "Non-profit-1");
	AuctionRequest myRequestDup = new AuctionRequest(d2, t2, "Non-profit-1");
	AuctionRequest myRequestBadDay= new AuctionRequest(d3, t3, "Non-profit-1");
	AuctionRequest myRequestPast= new AuctionRequest(d4, t4, "Non-profit-1");
	AuctionRequest myRequestValid= new AuctionRequest(d5, t5, "Non-profit-1");
	AuctionRequest myRequestValid2= new AuctionRequest(d6, t6, "Non-profit-2");
	
	Day d = new Day("January", 5, 2016);
	
	
	@Before
	public void setup() {
		c1 = new Calendar();
		c2 = new Calendar();
		d.addAuction(myRequestPast);
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
	public void testCheckDayValidDay() {
		assertTrue("test case valid day", c2.checkDay(myRequest.getDate().getDay(), myRequest));
	}
	
	@Test
	public void testCheckDayInvalidDay() {
		assertFalse("test case invalid day", c2.checkDay(myRequestBadDay.getDate().getDay(), myRequestBadDay));
	}
	
	@Test
	public void testCheckAuctionExistOnNonProfitWithNoAuctions() {
		assertTrue("test case no other auctions", c2.checkAuctionExist(myRequest.getNonProfitName()));
	}

	@Test
	public void testCheckLastYearWithNoAuctionInPastYear() {
		assertTrue("test case no auction in last year", c2.checkLastYear(myRequest.getNonProfitName()));
	}

	@Test
	public void testCheckLastYearWithAnAuctionInPastYear() {
		for(int i = 0; i < 75; i++) {
			if(i == 45) {
				c2.getCalendar().get(i).addAuction(myRequestPast);
			}
		}
		assertFalse("test case auction in last year already", c2.checkLastYear(myRequest.getNonProfitName()));
	}
	
	
	@Test
	public void testCheckTotalAuctionsForLessThanAuctionLimit() {
		c2.setAuctionsAllowed(31);
		c2.setAuctionsTotal(30);
		assertTrue("test auction amount less than Auctions allowed passes", c2.checkTotalAuctions());		
	}
	
	@Test
	public void testCheckTotalAuctionsForGreaterThanAuctionLimit() {
		c2.setAuctionsAllowed(20);
		c2.setAuctionsTotal(25);
		assertFalse("test auction amount greater than Auctions allowed fails", c2.checkTotalAuctions());		
	}
	
	@Test
	public void testCheckWeekOutsideOfSevenDays() {
		assertTrue("test case check week true", c2.checkWeek(30));
	}
	
	@Test
	public void testCheckWeekInsideOfSevenDays() {
		assertFalse("test case check week false", c2.checkWeek(21));
	}
	
	@Test
	public void testAddAuctionForValidRequest() {
		assertTrue("test case add auction valid request", c2.addAuction(myRequestValid));
	}
	
	@Test
	public void testAddAuctionForInvalidRequest() {
		assertFalse("test case add auction valid request", c2.addAuction(myRequestPast));
	}
		
	@Test
	public void testGetAuctionForOrganizationExist() {	
		Calendar c = new Calendar();
		AuctionRequest myRequest = new AuctionRequest(new Date(30, "November", 2016), new Time(2, 40, "PM"), "Bill");
		
		c.addAuction(myRequest);		

		Auction testAuction = new Auction("Bill", new Date(30, "November", 2016), new Time(2, 40, "PM"));		
		assertEquals(testAuction.getName(), c.getAuctionForOrganization(myNonProfit).getName());
		
	}

}
