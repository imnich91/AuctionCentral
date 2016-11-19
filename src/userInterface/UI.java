/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */


package userInterface;

import java.util.Date;
/**
 * The UI Class.
 * @author Dmitriy Onishchenko
 *
 */
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import model.Bidder;
import model.Calendar;
import model.NonProfit;
import model.Staff;
import model.User;

public class UI {	
	
	/**
	 * The Welcoming message
	 */
	public static final String WELCOME = "AuctionCentral: the auctioneer for non-profit organizations.";

	
	private final List<User> myUsers;
	private HashMap<String, String> myUsersLogins;
	private BidderUI myBidderUI;
	private StaffUI myStaffUI;
	private NonProfitUI  myNonProfitUI;
	private Scanner myInput;
	private Calendar myCalendar;
	

	
	
	
	public UI(List<User> theUsers, HashMap<String, String> theUsersLogin, Calendar theCalendar) {
		
		myUsers = theUsers;
		myUsersLogins = theUsersLogin;
		myCalendar = theCalendar;
		myInput = new Scanner(System.in);
		myBidderUI = null;
		myStaffUI = null;
		myNonProfitUI = null;		
	}
	
	public void runUI() {
		
		User currentUser = login();
		System.out.println();
		
		if (currentUser instanceof Bidder) {			
			myBidderUI = new BidderUI((Bidder)currentUser, myInput, myCalendar);
			
			myBidderUI.displayBidderMenu();
			
			
		} else if (currentUser instanceof Staff) {			
			myStaffUI = new StaffUI((Staff)currentUser, myInput, myCalendar);
			myStaffUI.displayStaffMenu();
			
			
		} else {			
			myNonProfitUI = new NonProfitUI((NonProfit)currentUser, myInput, myCalendar);			
			
			myNonProfitUI.displayNonProfitMenu();
		}
		
	}
	
	public User login() {		

		String username;
		String password;
		User theUser = null;	
		
		boolean loggingIn = true;
		
		while (loggingIn) {
			
			System.out.println("Welcome to AuctionCentral!!\n");
			System.out.print("Please enter your username: ");
			username = myInput.nextLine();
			System.out.print("Please enter your password: ");
			password = myInput.nextLine();			
			
			if (password.equals(myUsersLogins.get(username))) {
				
				// return the type of user
				for (User user: myUsers) {					
					if (user.getUserName().equals(username)) {						
						theUser = user;
					}					
				}				
				loggingIn = false;				
			} 				
		}		
		return theUser;		
	}
}
