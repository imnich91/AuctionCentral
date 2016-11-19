/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */


package model;

import java.io.Serializable;

/**
 * A bid class that stores the name of the bidder and the bid price that he has bid on
 * @author Georgia Wade
 */

public class Bid implements Serializable{
	
	/**
	 * The serializable ID.
	 */
	private static final long serialVersionUID = 1L;

	/**  the string that holds the name of the bidder */
	private String myBidder;
	
	/** the double that stores the price of the bid that the bidder has bid on */
	private int myBidPrice;
	
	
	/**
	 * A constructor to initialize the bidder and his bid price
	 * @param theBidder the string for the name of the bidder
	 * @param theBidPrice the double for the price of the bid
	 */
	public Bid(String theBidder, int theBidPrice){
		this.myBidder = theBidder;
		this.myBidPrice = theBidPrice;
	}
	
	/**
	 * A getter that returns the name of the bidder
	 * @return a string that stores the name of the bidder
	 */
	public String getBidder(){
		return myBidder;
	}
	
	/**
	 * A getter that returns the bid price that the bidder has bid on
	 * @return a double that returns bid price of a bid
	 */
	public int getBidPrice(){
		return myBidPrice;
	}
}

