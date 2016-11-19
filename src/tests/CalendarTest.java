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
	Date d1 = new Date(8, "November", 2016);
	Time t1 = new Time(2, 40, "pm");
	Date d2 = new Date(25, "November", 2016);
	Time t2 = new Time(7, 40, "pm");
	Date d3 = new Date(35, "November", 2016);
	Time t3 = new Time(7, 40, "pm");
	Date d4 = new Date(5, "January", 2015);
	Time t4 = new Time(7, 40, "pm");
	Date d5 = new Date(25, "November", 2016);
	Time t5 = new Time(7, 40, "pm");
	Date d6 = new Date(26, "November", 2016);
	Time t6 = new Time(7, 40, "pm");
	
	AuctionRequest myRequest = new AuctionRequest(d1, t1, "Non-profit-1");
	AuctionRequest myRequestDup = new AuctionRequest(d2, t2, "Non-profit-1");
	AuctionRequest myRequestBadDay= new AuctionRequest(d3, t3, "Non-profit-1");
	AuctionRequest myRequestPast= new AuctionRequest(d4, t4, "Non-profit-1");
	AuctionRequest myRequestValid= new AuctionRequest(d5, t5, "Non-profit-1");
	AuctionRequest myRequestValid2= new AuctionRequest(d6, t6, "Non-profit-2");
	
	Day d = new Day("January", 5, 2015);
	
	
	@Before
	public void setup() {
		c1 = new Calendar();
		c2 = new Calendar();
		d.addAuction(myRequestPast);
	}

	@Test
	public void Verify_calendar_returns_correct_day() {
		Day day1 = c1.getCurrentDay();
		assertEquals("test case current day fails! -calendar", day1.getDay(), 1);
	}
	
	@Test
	public void Verify_calendar_returns_correct_month() {
		Day day1 = c1.getCurrentDay();
		assertEquals("test case current month fails! -calendar", day1.getMonth(), "November");
	}
	
	@Test
	public void Verify_calendar_returns_correct_amount_of_days() {
		assertEquals("test case amount of days fails! -calendar", c1.getCalendar().size(), 390);
	}
	
	@Test
	public void Verify_calendar_returns_right_year() {
		assertFalse("test case wrong year -calendar", c1.getCalendar().get(380).getYear() == 2015);
	}
	
	@Test
	public void Verify_check_month_year_passes_same_month_year() {
		assertTrue("test case same month", c2.checkMonthYear(myRequest.getDate().getMonth(), myRequest.getDate().getYear()));
	}
	
	@Test
	public void Verify_check_month_year_fails_wrong_month_year() {
		assertFalse("test case wrong month", c2.checkMonthYear("January", 2013));
	}

	@Test
	public void Verify_day_in_month_passes() {
		assertTrue("test case valid day", c2.checkDay(myRequest.getDate().getDay(), myRequest));
	}
	
	@Test
	public void Verify_day_in_month_fails() {
		assertFalse("test case invalid day", c2.checkDay(myRequestBadDay.getDate().getDay(), myRequestBadDay));
	}
	


	@Test
	public void testCheckAuctionExist() {
		
		assertTrue("test case no other auctions", c2.checkAuctionExist(myRequest.getNonProfitName()));
		c2.addAuction(myRequestValid);
		assertFalse("test case already have auction", c2.checkAuctionExist(myRequest.getNonProfitName()));
	}

	@Test
	public void testCheckLastYear() {
		assertTrue("test case no auction in last year", c2.checkLastYear(myRequest.getNonProfitName()));
		for(int i = 0; i < 75; i++) {
			if(i == 45) {
				c2.getCalendar().get(i).addAuction(myRequestPast);
			}
			
		}
		assertFalse("test case auction in last year already", c2.checkLastYear(myRequest.getNonProfitName()));
	}

	@Test
	public void testCheckTotalAuctions() {
		c2.setAuctionsTotal(24);
		assertTrue("test case 24 auctions add one more", c2.checkTotalAuctions());
		c2.setAuctionsTotal(25);
		assertFalse("test case 25 auctions add one more", c2.checkTotalAuctions());
		
	}

	@Test
	public void testCheckWeek() {
		assertTrue("test case check week true", c2.checkWeek(9));
		assertFalse("test case check week false", c2.checkWeek(3));
	}
	
	@Test
	public void testGetAuctionForOrganizationExist() {	
		
		
		Calendar c = new Calendar();
		AuctionRequest myRequest = new AuctionRequest(new Date(15, "November", 2016), new Time(2, 40, "PM"), "Bill");
		
		c.addAuction(myRequest);		

		Auction testAuction = new Auction("Bill", new Date(15, "November", 2016), new Time(2, 40, "PM"));		
		assertEquals(testAuction.getName(), c.getAuctionForOrganization(myNonProfit).getName());
		
	}

}
