/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */


package model;

import java.io.Serializable;

/**
 * A time class used to represent time of the auction
 * formatted in HH:MM AM/PM
 * @author Kyaw Si Thu
 */


public class Time implements Serializable {

	/**
	 * My serializable id.
	 */
	private static final long serialVersionUID = 1L;

	/** An integer representation of the Hour of a time */
	private int myHour;
	
	/** An integer representation of the Minute of a time */
	private int myMinute;
	
	/** An string representation of the Period of a time such as AM or PM */
	private String myPeriod;
	
	/**
	 * A constructor for the time Class to initialize all the fields
	 * @param theHour	an integer used to represent the Hour
	 * @param theMinute an integer used to represent the Minute
	 * @param thePeriod a string used to represent the Period of a day
	 */
	
	public Time(int theHour, int theMinute, String thePeriod) {
		this.myHour = theHour;
		this.myMinute = theMinute;
		this.myPeriod = thePeriod;
	}
	
	/**
	 * A setter method to edit the fields of a time
	 * @param theHour an integer representation of an Hour that need to be set to
	 * @param m an integer representation of a Minute that need to be set to
	 * @param pan a string representation of a Period that need to be set to
	 */
	public void setTime(int theHour, int theMinute, String thePeriod) {
		this.myHour = theHour;
		this.myMinute = theMinute;
		this.myPeriod = thePeriod;
	}
	
	/**
	 * A getter for the Hour of a given time
	 * @return an integer representation of an theHour 
	 */
	public int getHour() {
		return this.myHour;
	}
	
	/**
	 * A getter for the Minute of a given time
	 * @return an integer representation of a theMinute 
	 */
	public int getMinute() {
		return this.myMinute;
	}
	
	/**
	 * A getter for the Period of a given time
	 * @return a string that indicates the thePeriod of a given time
	 */
	public String getPeriod() {
		return this.myPeriod;
	}
	
	/**
	 * {@inheritDoc}
	 * Returns a representation of Time.
	 */
	public String toString() {
		String str = myHour + ":" + "00" + " " + myPeriod;		
		return str;
	}
}
