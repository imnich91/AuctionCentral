/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */

package userInterface;


import java.util.Scanner;

import model.Calendar;
import model.Staff;


/**
 * Our staff user interface class.
 * @author Ian Nicholas
 *
 */
public class StaffUI {
	
	private Staff myCurrStaff;
	private Scanner myScanner;
	private Calendar myCalendar;

	
	public StaffUI (Staff theStaff, Scanner theScanner , Calendar theCalendar ) {
		
		myCurrStaff = theStaff;
		myScanner = theScanner;
		myCalendar = theCalendar;
				
		
	}
	
	
	public void displayStaffMenu() {
		
		displayStaffInfo();
		System.out.print("\nNovember 1, 2016. ");
		System.out.println("Total number of upcoming auctions: " + myCalendar.getAuctionsTotal());
		
		System.out.println("\nWhat would you like to do? (enter a number)");
		System.out.println("1. View Calendar of upcoming auctions");
		System.out.println("2. Administrative functions");
		System.out.println("3. Logout");
		
		System.out.print("  -> ");
		int input = myScanner.nextInt();	
		System.out.println();
		
		if(input == 1) {
			displayCalendar();
		} else if(input == 3){
			displayLogoutMessage();
		}
		
	}
	
	private void displayCalendar(){
		displayStaffInfo();
		System.out.print("\nNovember 1, 2016. ");
		System.out.println("Total number of upcoming auctions: " + myCalendar.getAuctionsTotal());
		
		StringBuilder b = new StringBuilder(); 
		b.append("\n     Su          M          T          W         Th          F          S\n");
		
		
		b.append("                            [November]                    \n");
		b.append("|          |       ");
		for(int i = 1; i < 6; i++) {
			b.append("   |    " + i + ":" + myCalendar.getCalendar().get(i -1).getNumAuctions());
		}
		b.append("   |\n");
		b.append("|    ");
		for(int i = 6; i < 10; i++) {			
			b.append(i + ":" + myCalendar.getCalendar().get(i -1).getNumAuctions() +"   |    ");
		}
		
		b.append(10 + ":" + myCalendar.getCalendar().get(10-1).getNumAuctions() +"  |   ");
		b.append(11 + ":" + myCalendar.getCalendar().get(11-1).getNumAuctions() +"   |   ");
		b.append(12 + ":" + myCalendar.getCalendar().get(12-1).getNumAuctions() +"   |   ");
		b.append("\n|   ");
		for(int i = 13; i < 20; i++) {
			b.append(i + ":" + myCalendar.getCalendar().get(i -1).getNumAuctions() +"   |   ");
		}
		b.append("\n|   ");
		for(int i = 20; i < 27; i++) {
			b.append(i + ":" + myCalendar.getCalendar().get(i -1).getNumAuctions() +"   |   ");
		}
		b.append("\n|   ");
		for(int i = 27; i < 31; i++) {
			b.append(i + ":" + myCalendar.getCalendar().get(i -1).getNumAuctions() +"   |   ");
		}
		b.append("       |          |          |");
		b.append("\n\n");
		b.append("Press 1 to go back.");
		System.out.println(b.toString());
		
		System.out.print("  -> ");
		int input = myScanner.nextInt();	
		System.out.println();
		
		if(input == 1) {
			displayStaffMenu();
		}
	}

	private void displayStaffInfo() {
		System.out.println(UI.WELCOME);
		System.out.println(myCurrStaff.toString());
	}
	
	private void displayLogoutMessage() {		
		System.out.println("\nThank you for choosing AuctionCentral!");
		System.out.println("Have a nice day!");		
	}
}