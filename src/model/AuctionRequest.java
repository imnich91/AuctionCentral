/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */


package model;

import java.io.Serializable;

/**
 * A class containing the name of the non profit and the date and time of the auction request
 * @author Kyaw Si Thu, Ian Nicholas
 */
public class AuctionRequest implements Serializable {
	/*Hi*/
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** A string that stores the name of the non-profit of the request */
	
	private String myNonProfitName;
	
	/** A date object that contains the information of a date of the request */
	private Date myDate;
	
	/** A time object that contains the information of a time of the request */
	private Time myTime;
	
	
	/**
	 * A constructor that initializes all the fields of a request
	 * @param theDate the date of the request that will be initialized to
	 * @param theTime the time of the request that will be initialized to
	 * @param theNonProfitName the name of the non-profit that will be initialized to
	 */
	public AuctionRequest(Date theDate, Time theTime, String theNonProfitName) {
		myDate = theDate;
		myTime = theTime;
		myNonProfitName = theNonProfitName;
	}
	
	
	/**
	 * A setter to change the date and time of a given auction request
	 * @param theDate the date of the request that will be changed to
	 * @param theTime the time of the request that will be changed to
	 */
	public void setRequest(Date theDate, Time theTime) {
		myDate = theDate;
		myTime = theTime;
	}
	
	/**
	 * A getter that returns the name of non profit of an auction request
	 * @return a string that represents the name of the non profit
	 */
	public String getNonProfitName() {
		return myNonProfitName;
	}
	
	/**
	 * A getter that returns the date of an auction request
	 * @return the date of an auction request
	 */
	public Date getDate() {
		return myDate;
	}
	
	/**
	 * A getter that returns the time of an auction request
	 * @return the time of an auction request
	 */
	public Time getTime() {
		return myTime;
	}
	
	/**
	 * @author Georgia Wade
	 * this method checks if the time and date fields are null
	 * for testing pruposes
	 * @return false if a field is empty else true
	 */
	public boolean checkRequiredField(){
		
		if(myDate == null || myTime == null)
			return false;
		else
			return true;
	}

}
