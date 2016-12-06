/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */

package view;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Calendar;
import model.User;

/**
 * This is the main class of this
 * program it starts the program.
 * 
 * @author Colin Casey
 */
public class AuctionCentralMain {
	/**
     * Private constructor, to prevent instantiation of this class.
     */
    private AuctionCentralMain() {
        throw new IllegalStateException();
    }

    
    private static List<User> myUsers;
    private static HashMap<String, String> myUsersLogins;
	private static Calendar myCalendar;
    
    
    /**
     * The main method, invokes the Auction central GUI. 
     * Command line arguments are ignored.
     * 
     * @param theArgs Command line arguments.
     */
    public static void main(final String[] theArgs) {
    			
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
	         FileInputStream calendarFile = new FileInputStream("./usersData/calendar24.ser");
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
    	
    	
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AuctionCentralGUI(myUsers, myUsersLogins, myCalendar).start();
            }
        });
    }
}
