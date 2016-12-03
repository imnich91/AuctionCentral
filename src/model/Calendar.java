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
	 * Number of days before we can cancel auction.
	 */
	private static final int DAYS_BEFORE = 2;

	/**
	 * List of days in the calendar. 
	 */
	private List<Day> myCalendar;
	
   /**
	 * The current month.
	 */	
	private String myMonth;
   /**
	 * The current year.
	 */
	private int myYear;
	
   /**
	 * The current day. 
	 */
	private Day myCurrentDay;
	
	/**
	 * The number of auctions registered.
	 */
	private int myAuctionsTotal;
	
	/**
	 * List of auctions registered. 
	 */
	private List<Auction> myAuctions;
	
	/**
	 * The number of registered auctions allowed.  
	 */
	private int myAuctionsAllowed;
		
	/**
	 * Constructs the calendar. 
	 */
	public Calendar() {
		myCalendar = new ArrayList<Day>();
		myAuctionsTotal = 0;
		myAuctionsAllowed = 25;
		myAuctions = new ArrayList<Auction>();
		makeCalendar();		
	}
	
	/**
	 * Helper method to construct the calendar.
	 * Range from one year in the past to thirty days in the future.
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
	 * @return list of days in the calendar. 
	 */
	public List<Day> getCalendar() {
		return myCalendar;
	}
	
	/**
	 * @return the number of upcoming auctions. 
	 */
	public int getAuctionsTotal() {
		return myAuctionsTotal;
	}
	
	/**
	 * @param theTotal number of auctions to set. 
	 */
	public void setAuctionsTotal(int theTotal) {
		myAuctionsTotal = theTotal;
	}
	
	/**
	 * @param theNum of auctions allowed to set. 
	 */
 	public void setAuctionsAllowed(final int theNum) {
 		myAuctionsAllowed = theNum;
 	}
 	
 	/**
 	 * @return the number of auctions allowed.
 	 */
 	public int getAuctionsAllowed() {
 		return myAuctionsAllowed;
 	}
 	
	
   /**
	 * @return a list of auctions in the calendar. 
	 */
	public List<Auction> getAuctions() {
		return myAuctions;
	}
	
	/**
	 * @return the current day. 
	 */
	public Day getCurrentDay() {
		return myCurrentDay;
	}

   /**
	 * Takes an auction request object and returns
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
	
	/**
	 * Checks to see if the calendar is 
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
	
	/**
	 * Checks if the day of the auction request is valid.
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
	
	/**
	 * Checks if the non-profit already has an auction
	 * by checking the name of the non-profit in the Calendar.
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
	
	/**
	 * Checks if the non-profit had an auction in the last year.
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
				if(myCalendar.get(i).getAuction().getName().equals(name)) {
					return false;
				}
				if(myCalendar.get(i).getAuction2().getName().equals(name)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Checks if the total auction in the Calendar is no more than the max allowed.
	 * 
	 * @return true if the calendar has no more than the max auctions allowed.
	 */
	public boolean checkTotalAuctions() {
		if(myAuctionsTotal >= myAuctionsAllowed) {
			return false;
		} else {
			return true;		
		}
	}
	
	/**
	 * Checks if the auction that is being added is
	 * at lease one week from the current day.
	 * 
	 * @param theDay the day of the auction request that need to be checked
	 * @return true if the day of the auction being added is at least one week from today
	 */
	public boolean checkWeek(int day) {
		
		if(day > myCurrentDay.getDay() && day - myCurrentDay.getDay() <= 7) {
			return false;
		} else if(day <= myCurrentDay.getDay() && LocalDate.now().plusDays(day).getDayOfMonth() < 7) {
			return false;
		}
		return true;
	}
   
	/**
	 * Adds the auction to the auction list in the Calendar
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
    *  Returns the auction that the given Non-profit currently has.
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
	
	
	
	/**
	 * @author Dmitriy Onishchenko 
	 * Method that checks to see if the current date is more that two days before the 
	 * auction date. 
	 * @param theAuctionDate the auction date
	 * @param theCurrentDate current day
	 * @return true if current date 2 or more days before the auciton date
	 * false otherwise
	 */
	public static boolean isMoreThanTwoDays(Date theAuctionDate, Date theCurrentDate) {
		
		return theAuctionDate.getYear() <= theCurrentDate.getYear() && 
				theAuctionDate.getMonthAsNumber() <= theCurrentDate.getMonthAsNumber() &&
						theAuctionDate.getDay() <= theCurrentDate.getDay() - DAYS_BEFORE;
	}
	
	
}
