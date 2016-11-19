/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */


package model;

import java.io.Serializable;

/**
 * A date class used to represent the date of the auction
 * formatted in  DD, MM YY
 * @author Kyaw Si Thu
 *
 */
public class Date  implements Serializable{
	/**
	 * Serializable Id.
	 */
	private static final long serialVersionUID = 1L;

	/** An integer to represent the day of a date */
	private int myDay;
	
	/** A string to represent the month of a date */
	private String myMonth;
	
	/** An integer to represent the year of a date */
	private int myYear;
	
	/**
	 * A constructor to initialize all the day,month and year
	 * @param theDay an integer representation of a date to be initialized to
	 * @param theMonth a string representation of a month to be initialized to
	 * @param theYear an integer representation of a year to be initialized to
	 */
	public Date(int theDay, String theMonth, int theYear) {
		this.myDay = theDay;
		this.myMonth = theMonth;
		this.myYear = theYear;
	}
	

	
	/**
	 * A setter method to set the date
	 * @param theDay an integer for the day to be initialized to
	 * @param theMonth a string for the month to be initialized to
	 * @param theYear an integer for the year to be initialized to
	 */
	public void setDate(int theDay, String theMonth, int theYear) {
		this.myDay = theDay;
		this.myMonth = theMonth;
		this.myYear = theYear;
	}
	
	/**
	 * A getter for the day of the given date
	 * @return an integer that represents the day of a given date
	 */
	public int getDay() {
		return this.myDay;
	}
	
	/**
	 * A getter for the month of the given date
	 * @return a string that represents the month of a given date
	 */
	public String getMonth() {
		return this.myMonth;
	}
	
	/**
	 * A getter for the year of the given date
	 * @return an integer that represents year of a given date
	 */
	public int getYear() {
		return this.myYear;
	}
	
	/**
	 * This method returns the total number of days corresponding to the name of a given month
	 * @return an integer that represents the total number of days of a given month
	 */
	public int getDaysInMonth() {
		switch(this.myMonth.toLowerCase()) {
			case "january":
				return 31;
			case "february":
				return 28;
			case "march":
				return 31;
			case "april":
				return 30;
			case "may":
				return 31;
			case "june":
				return 30;
			case "july":
				return 31;
			case "august":
				return 31;
			case "september":
				return 30;
			case "october":
				return 31;
			case "november":
				return 30;
			case "december":
				return 31;
			default:
				return 0;
				
		}
	}
	
	/**
	 * Converts a month from a string to an int.
	 * @param theMonth the month to convert
	 * @return the month as an int.
	 */
	public static String convertMonthToEquivalentInt(String theMonth) {	
		
		switch (theMonth) {
			case "01":
				return "January";
			case "02":
				return "February";
			case "03":
				return "March";
			case "04":
				return "April";
			case "05":
				return "May";
			case "06":
				return "June";
			case "07":
				return "July";
			case "08":
				return "August";
			case "09":
				return "September";
			case "10":
				return "October";
			case "11":
				return "November";
			case "12":
				return "December";				
			default:
				System.out.println("NOT A MONTH");
				return "";		
		}	
	}
	
	/**
	 * {@inheritDoc}.
	 * Returns a representation of Date.
	 */
	public String toString() {
		String str = myMonth + " " + myDay + ", " + myYear;
		return str;
	}
}

