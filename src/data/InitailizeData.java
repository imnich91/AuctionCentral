/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */

package data;

import java.util.List;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import model.Auction;
import model.AuctionRequest;
import model.Bidder;
import model.Calendar;
import model.Date;
import model.Day;
import model.Item;
import model.NonProfit;
import model.Staff;
import model.Time;
import model.User;

/**
 * Serializable Class
 * @author Dmitriy Onishchenko
 *
 */
public class InitailizeData {	
	
	public static void main(String [] args) {
		
		
		final Calendar myCalendar = new Calendar();
	
		final Day myDay = new Day("January", 5, 2016);
		
		final List<User> myUsers = new ArrayList<User>();
		final HashMap<String, String> myUsersLogins = new HashMap<String, String>();
		
		final NonProfit myNonProfitBill = new NonProfit("Bill Gates", "bill", "1234",
				"Bill", "1234 Mercer Island", "111-111-1111");
		
		final NonProfit myNonProfitAnn = new NonProfit("Ann Hopkins", "ann", "1111",
				"Hopkins", "2222 st", "222-222-2222");
		
		final NonProfit myNonProfitSteve = new NonProfit("Steve Jobs", "s", "1",
				"Steve Jobs", "2222 st", "222-222-2222");
	
		
		final Staff myStaffWade = new Staff("Wade Casey", "wc", "2345");
		
		final Bidder myBidderDave = new Bidder("Dave Mathews", "dave", "3456", 
				"d@gmail.com", "1111 11th st San Diego", "222-222-2222");
	    
		// create users and populate users collection
		myUsers.add(myNonProfitBill);	
		myUsers.add(myNonProfitAnn);
		myUsers.add(myStaffWade);		
		myUsers.add(myBidderDave);
		myUsers.add(myNonProfitSteve);
		
		// populate userNames and passwords
		for (User user: myUsers) {			
			myUsersLogins.put(user.getUserName(), user.getPassword());				
		}
		
		
		// populate calendar with Auctions and items.
		myCalendar.addAuction(new AuctionRequest(new Date(12, "November", 2016), 
				new Time(2, 00, "PM"), myNonProfitBill.getOrgName()));	
		

		
		
		for(int i = 0; i < 75; i++) {
			if(i == 45) {
				myCalendar.myCalendar.get(i).addAuction(new AuctionRequest(new Date(5, "January", 2016), new Time(7, 0, "PM"), "Steve Jobs"));
			}
			
		}
		
		Auction billsAuction = myCalendar.getAuctionForOrganization(myNonProfitBill);
		
		billsAuction.addItem(myNonProfitBill, new Item("Autographed Sounders Jersey", "Good", "medium",
				100, "Seattle Sounders", "Game worn jersy signed by Brad Evan.",
				"CenturyLink Field front office has the jersey"));
		
		billsAuction.addItem(myNonProfitBill, new Item("Surface Pro 3              ", "Good", "small",
				900, "Steve Jobs", "Like new surface pro 3 computer.",
				"Must sell"));	
		
		
	      
	      try {
	         FileOutputStream usersFile =
	         new FileOutputStream("./usersData/users.ser");
	         ObjectOutputStream out = new ObjectOutputStream(usersFile);
	         out.writeObject(myUsers);
	         out.close();
	         usersFile.close();
	         
	         FileOutputStream loginsFile =
	         new FileOutputStream("./usersData/usersLogins.ser");
	         ObjectOutputStream out2 = new ObjectOutputStream(loginsFile);
	         out2.writeObject(myUsersLogins);
	         out2.close();
	         loginsFile.close(); 
	         
	         
	         FileOutputStream CalendarFile =
	         new FileOutputStream("./usersData/calendar.ser");
	         ObjectOutputStream out3 = new ObjectOutputStream(CalendarFile);
	         out3.writeObject(myCalendar);
	         out3.close();
	         CalendarFile.close();
	         
	         System.out.println("Serialized data is saved in /users.ser");
	         System.out.println("Serialized data is saved in /usersLogins.ser");
	         System.out.println("Serialized data is saved in /calendar.ser");
	      }
	      catch(IOException i) {
	          i.printStackTrace();
	      }
	   }

}
