/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */

package model;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to keep track of
 * all of the auctions in the Calendar.
 * 
 * @author Ian Nicholas
 * @version 1
 *
 */
public class Calendar implements Serializable {
	
	/**
	 * Serializable Id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Used to keep track of all of the days in the calendar.
	 * the current month plus the next three months
	 */
	private List<Day> myCalendar;
	
   /**
	 * Used to store the current month of the Calendar
	 */	
	private String myMonth;
   /**
	 * Used to store the current year of the Calendar
	 */
	private int myYear;
	
   /**
	 *  Used to store the current day of the Calendar
	 */
	private Day myCurrentDay;
	
	/**
	 * Holds current number of auctions.
	 */
	private int myAuctionsTotal;
	
	/**
	 * List of all non-profits that currently have auctions in the calendar. 
	 */
	private List<Auction> myAuctions;
	
	/**
	 * Holds the number of auctions allowed in a month. 
	 */
	private int myAuctionsAllowed;
		
	/**
	 * This is a constructor used to set up the whole class
	 * by initializing the fields of the Calendar.
	 */
	public Calendar() {
		myCalendar = new ArrayList<Day>();
		myAuctionsTotal = 0;
		myAuctionsAllowed = 25;
		myAuctions = new ArrayList<Auction>();
		makeCalendar();		
	}
	
	/**
	 * Used to give calendar to UI.
	 * 
	 * @return calendar
	 */
	public List<Day> getCalendar() {
		return myCalendar;
	}
	
	public int getAuctionsTotal() {
		return myAuctionsTotal;
		
	}
	
	public void setAuctionsTotal(int theTotal) {
		myAuctionsTotal = theTotal;
	}
	
 	public void setAuctionsAllowed(final int theNum) {
 		myAuctionsAllowed = theNum;
 	}
 	
 	public int getAuctionsAllowed() {
 		return myAuctionsAllowed;
 	}
 	
	
   /**
	 * Used to get all auctions in the
	 * calendar.
	 * 
	 * @return a list of all auctions in calendar
	 */
	public List<Auction> getAuctions() {
		return myAuctions;
	}
	
	/**
	* Used to get the current day.
	* 
	* @return Current day
	*/
	public Day getCurrentDay() {
		return myCurrentDay;
	}
	
	/**
	 * This is used to construct the calendar.
	 * The calendar generated contains the previous 
	 * year up to the current day, and then an additional
	 * 30 days for the current year. 
	 */    
	private void makeCalendar() {
		LocalDate theDate = LocalDate.now();
		myMonth = theDate.getMonth().toString().toLowerCase();
		myYear = theDate.getYear();
			
		LocalDate thePrevious = LocalDate.now().minusYears(1);
		
		while(!theDate.equals(thePrevious)) {
		    myCalendar.add(new Day(thePrevious.getMonth().toString(), thePrevious.getDayOfMonth(), thePrevious.getYear()));
		    thePrevious = thePrevious.plusDays(1);
		}
		myCurrentDay = new Day(thePrevious.getMonth().toString(), thePrevious.getDayOfMonth(), thePrevious.getYear());
		myCalendar.add(myCurrentDay);
		for(int i = 1; i < 30; i++) {
			myCalendar.add(new Day(thePrevious.getMonth().toString(), thePrevious.getDayOfMonth(), thePrevious.getYear()));
		    thePrevious = thePrevious.plusDays(1);
		}
	}

   /**
	 * This method takes an auction request object and returns
	 * whether the auction request is allowed or not
	 * by checking all the business rules.
	 * 
	 * @param theRequest an auction request submitted by the non-profit
	 * @return true if the auction request is allowed
	 */
	public boolean canAddAuction(AuctionRequest theRequest) {
		String month = theRequest.getDate().getMonth();
		int year = theRequest.getDate().getYear();
		int day = theRequest.getDate().getDay();
		String name = theRequest.getNonProfitName();	
		boolean canAdd = true;
		
		//Checking if right month and year.
		canAdd = canAdd && checkMonthYear(month, year);
		
		//Checking if day is valid.
		canAdd = canAdd && checkDay(day, theRequest);
		
		//Checking if already have existing auction in future.
		canAdd = canAdd && checkAuctionExist(name);
		
		//Checks if auction in previous year
		canAdd = canAdd && checkLastYear(name);
		
		//Checks if the total auctions are no more than the number of auctions allowed.
		canAdd = canAdd && checkTotalAuctions();

		//Checks if the auction date is at least one week from the day
		canAdd = canAdd && checkWeek(day);
		
		return canAdd;
	}
	
	//Checking if right month and year.
	/**
	 * This method checks to see if the calendar is 
	 * currently on the same month and year
	 * as the month and year of the auction request.
	 * 
	 * @param theMonth the month of the auction request
	 * @param theYear the year of the auction request
	 * @return true if the auction request is currently on the same month and year as the calendar
	 */
	public boolean checkMonthYear(String month, int year) {
		if (!(myMonth.equals(month) || !myMonth.equals(LocalDate.now().getMonth().plus(1))) || myYear != year) {
			return false;
		} else
			return true;
	}
	
	//Checking if day is valid.
	/**
	 * This method checks if the day of the auction request is valid
	 * that is if the day of the request is between 1 and 30.
	 * @param theDay the day of the auction request
	 * @param theRequest the auction request that need to be checked
	 * @return true if the day of the auction request is valid
	 */
	public boolean checkDay(int day, AuctionRequest theRequest) {
		if(1 > day || day > LocalDate.now().lengthOfMonth()){
			return false;
		} else 
			return true;
	}
	
	//Checking if already have existing auction in future.
	/**
	 * This method checks if the non-profit already has an auction
	 * by checking the name of the non-profit in the Calendar
	 * 
	 * @param theName  the name of the non-profit that needs to be checked
	 * @return true if the non-profit name is not found in the future
	 */
	public boolean checkAuctionExist (String name) {
		boolean check = true;
		for(int i = 0; i < myAuctions.size(); i++) {
			if(myAuctions.get(i).getName().equals(name)) {				
				check = false;
			}
		}
		return check;
	}
	
	//Checks if auction in previous year
	/**
	 * This method check if the non-profit had an auction last year
	 * 
	 * @param theName the name of the non-profit that need to be checked
	 * @return true if there is no auction for that non-profit in previous year
	 */
	public boolean checkLastYear(String name) {
		for(int i = 0; i < myCalendar.size()-30; i++) {
			if(myCalendar.get(i).getNumAuctions() == 1) {
				if(myCalendar.get(i).getAuction().getName().equals(name)) {
					return false;
				}
			} else if (myCalendar.get(i).getNumAuctions() == 2) {
				if(myCalendar.get(i).getAuction2().getName().equals(name)) {
					return false;
				}
			}
		}
		return true;
	}
	
	//Checks if the total auctions is no more than 25
	/**
	 * This method checks if the total auction in the Calendar is no more than 25 before adding
	 * 
	 * @return true if the calendar has no more than 25 auctions
	 */
	public boolean checkTotalAuctions() {
		if(myAuctionsTotal >= myAuctionsAllowed) {
			return false;
		} else {
			return true;		
		}
	}
	
	//Checks if the auction date is at least one week from the day
	/**
	 * This method checks if the auction that is being added is
	 * at lease one week from the current day
	 * 
	 * @param theDay the day of the auction request that need to be checked
	 * @return true if the day of the auction being added is at least one week from today
	 */
	public boolean checkWeek(int day) {
		if(day >= myCurrentDay.getDay() && day - myCurrentDay.getDay() <= 7) {
			return false;
		}
		return true;
	}
   
	/**
	 * This method adds the auction to the auction list in the Calendar
	 * using the information from the auction request and return true if it has been added
	 * 
	 * @param theRequest the auction request to be added
	 * @return true if the request has been added and false otherwise
	 */
	public boolean addAuction(AuctionRequest theRequest) {		
		boolean added = false;
		Auction temp;
		if(canAddAuction(theRequest)) {				
			temp = new Auction(theRequest.getNonProfitName(), theRequest.getDate(), theRequest.getTime());
			myCalendar.get(theRequest.getDate().getDay()-1).addAuction(theRequest);									
			myAuctions.add(temp);
			added = true;
			myAuctionsTotal++;					
		}		
				
		return added;
	}
	
   /**
    *  This method return the auction that the given Non-profit currently has.
    *  
    * @param theNonProfit the name of the non-profit to be looked for
    * @return an auction that the given non-profit has in the calendar
    */
	public Auction getAuctionForOrganization(NonProfit theNonProfit) {		
		
		Auction theAuction = null;
		
		for (int i = 0; i < myAuctions.size(); i++) {			
			if (myAuctions.get(i).getName().equals(theNonProfit.getOrgName())) {				
				theAuction = myAuctions.get(i);
			}			
		}			
		
		return theAuction;
	}
	
	
}
