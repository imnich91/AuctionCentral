/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */


package model;

import java.io.Serializable;

/**
 * Used to keep track of auctions happening
 * every day.
 * 
 * @author caseycao, Ian Nicholas
 * @version 1
 *
 */
public class Day implements Serializable {
	
	/**
	 * Serializable Id.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * This information is shared by both auction
	 */
	private String myMonth;
	private int myDay;
	private int myYear;
	/**
	 * auctions going on today.
	 */
	private int myNumAuctions;
	
	/**
	 * This is for the first auction made.
	 */

	private Auction myAuction;
	
	
	
	
	/**
	 * This is for the second auction made.
	 */
	private Auction myAuction2;
	
	/**
	 * This is used to fill out default information shared by
	 * both auctions.
	 * 
	 * @param theMonth int
	 * @param theDay int
	 * @param theYear int
	 */
	public Day(final String theMonth, final int theDay, final int theYear) {
		myMonth = theMonth.toLowerCase();
		myDay = theDay;
		myYear = theYear;
		myNumAuctions = 0;
	}
	
	/**
	 * Used to get the day.
	 * 
	 * @return day
	 */
	public int getDay (){
		return myDay;
	}
	
	/**
	 * Used to get month
	 * 
	 * @return month
	 */
	public String getMonth() {
		return myMonth;
	}
	
	public Auction getAuction() {
		return myAuction;
	}
	
	public Auction getAuction2() {
		return myAuction2;
	}
	
	/**
	 * Used to get year
	 * 
	 * @return year
	 */
	public int getYear() {
		return myYear;
	}
	
	/**
	 * Used to get number of
	 * auctions this day.
	 * 
	 * @return year
	 */
	public int getNumAuctions() {
		return myNumAuctions;
	}
	
	public boolean canAddAuction(AuctionRequest theRequest) {
		if(myNumAuctions == 2) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Adds the auction request to the day. 
	 * @param theRequest
	 */
	public void addAuction(AuctionRequest theRequest) {
		Date date = theRequest.getDate();
		Time time = theRequest.getTime();
		String name = theRequest.getNonProfitName();
		if(myNumAuctions == 0) {
			myAuction = new Auction(name, date, time);
			myNumAuctions++;
		} else if (myNumAuctions == 1) {
			myAuction2 = new Auction(name, date, time);
			myNumAuctions++;
		}
		
	}
	
	
	/**
	 * Removes the first auction
	 */
	public void removeAuction() {
		myAuction = null;
		myNumAuctions--;
	} 
	
	/**
	 * Removes the second auction
	 */
	public void removeAuction2() {
		myAuction = null;
		myNumAuctions--;
	}
	
	/**
	 * @author Georgia Wade
	 * added for acceptance test purposes
	 * @param number of desired auctions to be set
	 */
	public void setMyNumAuctions(int number){
		myNumAuctions = number;
	}

}
