/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */


package userInterface;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Calendar;
import model.User;


/**
 * The UI Engine class that deserializes our data and runs our UI.
 * @author Dmitriy Onishchenko
 *
 */
public class UIEngine {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		List<User> myUsers = null;
		HashMap<String, String> myUsersLogins = null;
		Calendar myCalendar = null;
		
	      try
	      {
	    	 // read in the users file 
	         FileInputStream usersFile = new FileInputStream("./usersData/users.ser");
	         ObjectInputStream in = new ObjectInputStream(usersFile);	         
	         myUsers = (ArrayList<User>) in.readObject();
	         in.close();
	         usersFile.close();
	        
	         // read in the users user names file
	         FileInputStream usersLoginFile = new FileInputStream("./usersData/usersLogins.ser");
	         in = new ObjectInputStream(usersLoginFile);	         
	         myUsersLogins = (HashMap<String, String>) in.readObject();
	         in.close();
	         usersLoginFile.close();	     
	         
	         // read in the users user names file
	         FileInputStream calendarFile = new FileInputStream("./usersData/calendar.ser");
	         in = new ObjectInputStream(calendarFile);	         
	         myCalendar = (Calendar) in.readObject();
	         in.close();
	         calendarFile.close();	   
	         
	         
	      } catch(IOException i) {
	         i.printStackTrace();
	         return;
	         
	      } catch(ClassNotFoundException c) {
	         
	         c.printStackTrace();
	         return;
	      }
		
	    UI myUI = new UI(myUsers, myUsersLogins, myCalendar);		
		myUI.runUI();	
		
	   
//	      try {	         
//	         
//	         FileOutputStream CalendarFile =
//	         new FileOutputStream("./usersData/calendar24.ser");
//	         ObjectOutputStream out3 = new ObjectOutputStream(CalendarFile);
//	         out3.writeObject(myCalendar);
//	         out3.close();
//	         CalendarFile.close();		    
//	         
//	      }
//	      catch(IOException i) {
//	          i.printStackTrace();
//	      }
	   
	
	}

}
