/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */


package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	
  AuctionTest.class,
  BidderTest.class,
  ItemTest.class,
  NonProfitTest.class,
  StaffTest.class,
  BidderUITest.class,
  TimeTest.class,
  DateTest.class,
  DayTest.class,
  CalendarTest.class,
  
  BidOnAnItemAcceptanceTest.class,
  CancelAuctionRequestAcceptanceTests.class,
  CancelBidOnAnItemAcceptanceTest.class,
  NPOAddInventoryItemAcceptanceTest.class,
  NPOSubmitAuctionRequestAcceptanceTest.class,
  RemoveInventoryItemAcceptanceTest.class
  
})

/**
 * Our test Suite class containing all of our test classes.
 * @author Dmitriy Onishchenko
 *
 */
public class TestSuiteForAuctionCentral {
  // the class remains empty,
  // used only as a holder for the above annotations
}

