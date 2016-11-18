/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */

package userInterface;

import java.util.ArrayList;
import java.util.Scanner;

import model.Auction;
import model.AuctionRequest;
import model.Calendar;
import model.Date;
import model.Item;
import model.NonProfit;
import model.Time;


/**
 * 
 * Out Non profit user interface class.
 * @author Dmitriy Onishchekno, Ian Nicholas, Kyaw Si Thu
 *
 */
public class NonProfitUI {
	
	private NonProfit myCurrNonProfit;
	private Scanner myScanner;
	private Calendar myCalendar;
	
	private Auction myCurrAuction;
	private ArrayList<Item> myItems;

	
	public NonProfitUI (NonProfit theNonProfit, Scanner theScanner, Calendar theCalendar) {
		
		myCurrNonProfit = theNonProfit;
		myScanner = theScanner;
		myCalendar = theCalendar;
				
		
	}
	

	/**
	 * Displays the nonProft users menu. 
	 */
	public void displayNonProfitMenu() {
		
		displayNonProfitInfo();
		
		System.out.println();
		System.out.println("What would you like to do? (enter a number)");
		System.out.println("1. Submit an Auction Request");
		System.out.println("2. Add inventory to my Auction");
		System.out.println("3. Logout");
		System.out.print("  -> ");
		
		int choice = myScanner.nextInt();
		
		switch (choice) {
		
			case 1:
				displayAuctionRequestMenu();
				break;
			case 2:
				displayAuctionInventoryMenu();
				break;
			case 3:
				displayLogoutMessage();
				break;
			default :
				System.out.println("NOT AN OPTION");
				break;			
		}	
		
	}
	
	

	private void displayAuctionRequestMenu() {
		
		String orgName, month, period, requestDay, requestTime;
		int day, year, hour;
		
		Integer numToConvert; 
		
		myScanner.nextLine();		
		displayNonProfitInfo();
		
		System.out.println("Please enter your Organization Name");
		System.out.print("  -> ");
		orgName = myScanner.nextLine();
		
		
		// 12-November-2016
		System.out.println("Please enter the day, month and year for your request (example: 01/11/2016)");
		System.out.print("  -> ");
		requestDay = myScanner.nextLine();
		
		
		System.out.println();
		String[] dayy = requestDay.split("/");		
		month = Date.convertMonthToEquivalentInt(dayy[1]);
		
		// convert the day
		numToConvert = new Integer(dayy[0]);		
		day = numToConvert.intValue();
		
		// convert the year 
		numToConvert = new Integer(dayy[2]);		
		year = numToConvert.intValue();		

		System.out.println("Please enter the time for your auction, (example: HH AM/PM)");
		System.out.print("  -> ");
		requestDay = myScanner.nextLine();
		
		System.out.println();
		dayy = requestDay.split(" ");		
		// convert the Hour
		numToConvert = new Integer(dayy[0]);		
		hour = numToConvert.intValue();	
		
		period = dayy[1];
		
		Date date = new Date(day, month, year);
		Time time = new Time(hour, 0, period);		
		
		
		// submit auction request
		
		if (myCalendar.addAuction(new AuctionRequest(date, time, orgName))) {		
			System.out.println("Your request has been granted!");
			System.out.println("Your next auction is scheduled for " + date.toString() +" at " + time.toString());
			
			provideOptionToGoBack();			
			
		} else  {
			
			System.out.println("Your Auction request has been denied!");
			System.out.println("Try another time if you don't already have an auction scheduled");
			
			provideOptionToGoBack();			
		}
			
	}
	
	private void provideOptionToGoBack() {
		
		System.out.println();
		System.out.println("What would you like to do?");
		System.out.println("1. Go back to main menu");
		System.out.println("2. Logout");
		System.out.print("  -> ");
		
		int choice = myScanner.nextInt();
		
		if (choice == 1) {
			displayNonProfitMenu();
			
		} else  {
			displayLogoutMessage();				
		}
		
	}
	
	private void displayAuctionInventoryMenu() {


		Auction myCurrAuction = myCalendar.getAuctionForOrganization(myCurrNonProfit);
		
		
		if (myCurrAuction == null) {	
			
			System.out.println();
			System.out.println("You currently have no Acution scheduled");
			System.out.println("Please first submit an Auction Request");
			
			displayNonProfitMenu();	
		} 
		else {
			
			myItems = (ArrayList<Item>)myCurrAuction.getInventory();
			
			displayNonProfitInfo();
			displayItemsForCurrAuction(myItems);
			System.out.println();
			System.out.println();

			System.out.println("What would you like to do? (enter a number)");
			System.out.println("1. Add an item to my Inventory");
			System.out.println("2. Go back");
			System.out.println("3. Logout");
			System.out.print("  -> ");

			int input = myScanner.nextInt();

			switch (input) {

			case 1:
				displayAddInventory();
				break;
			case 2:
				displayNonProfitMenu();
				break;
			case 3:
				displayLogoutMessage();
				break;
			default :
				System.out.println("NOT AN OPTION");
				break;	

			}			
		}

	
	}

	private void displayAddInventory() {
		Item temp;
		String itemName = "";
		String itemCond = "";
		String itemSize = "";
		int itemMinBid;
		String itemDonor = "";
		String itemDescription = "";
		String addComments = "";

		displayNonProfitInfo();
		System.out.println();

		System.out.println("Please enter the item name:");
		System.out.print("  -> ");
		while(itemName.equals("")) {
			itemName = myScanner.nextLine();
		}
		System.out.println();

		displayNonProfitInfo();
		System.out.println();

		System.out.println("Please enter the item condition:");
		System.out.print("  -> ");
		while(itemCond.equals("")) {
			itemCond = myScanner.nextLine();
		}
		System.out.println();

		displayNonProfitInfo();
		System.out.println();

		System.out.println("Please enter the item size (small, medium, large):");
		System.out.print("  -> ");
		while(itemSize.equals("")) {
			itemSize = myScanner.nextLine();
		}
		System.out.println();

		displayNonProfitInfo();
		System.out.println();

		System.out.println("Please enter the minimum bid:");
		System.out.print("  -> ");
		itemMinBid = myScanner.nextInt();
		System.out.println();

		displayNonProfitInfo();
		System.out.println();

		System.out.println("Please enter the item donor:");
		System.out.print("  -> ");
		while(itemDonor.equals("")) {
			itemDonor = myScanner.nextLine();
		}
		System.out.println();

		displayNonProfitInfo();
		System.out.println();

		System.out.println("Please enter the item description:");
		System.out.print("  -> ");
		while(itemDescription.equals("")) {
			itemDescription = myScanner.nextLine();
		}
		System.out.println();

		displayNonProfitInfo();
		System.out.println();

		System.out.println("Please enter additional comments:");
		System.out.print("  -> ");
		while(addComments.equals("")) {
			addComments = myScanner.nextLine();
		}
		System.out.println();

		displayConfirmation(itemName, itemCond, itemSize, itemMinBid, itemDonor, itemDescription, addComments);

		System.out.println("What would you like to do? (enter a number)");
		System.out.println("1. Confirm item.");
		System.out.println("2. Go back (will lose info)");

		int input = myScanner.nextInt();

		switch (input) {

		case 1:
			System.out.println();
			System.out.println("Your item has been added to your inventory!");
			temp = new Item(itemName, itemCond, itemSize, itemMinBid, itemDonor, itemDescription, addComments);
			myItems.add(temp);
			displayAuctionInventoryMenu();
			break;
		case 2:
			displayAuctionInventoryMenu();
			break;
		default :
			System.out.println("NOT AN OPTION");
			break;	

		}
	}

	private void displayConfirmation(String itemName, String itemCond, String itemSize, int itemMinBid, String itemDonor, String itemDescription, String addComments) {
		displayNonProfitInfo();
		System.out.println();

		System.out.println("Item name: " + itemName);
		System.out.println("Item condition: " + itemCond);
		System.out.println("Item size: " + itemSize);
		System.out.println("Minimum Bid: " + itemMinBid);
		System.out.println("Item donor: " + itemDonor);
		System.out.println("Item description: " + itemDescription);
		System.out.println("Additional Comments: " + addComments);
		System.out.println();
	}

	private void displayItemsForCurrAuction(ArrayList<Item> theItems) {

		System.out.println("\nItem #   Item Name");

		for (int i = 0; i < theItems.size(); i++) {

			System.out.print(i + "    " + theItems.get(i).getItemName() + "  ");

			System.out.println();	

		}		
	}
	
	private void displayNonProfitInfo() {
		System.out.println();
		System.out.println(UI.WELCOME);
		System.out.println(myCurrNonProfit.toString());
	}
	
	private void displayLogoutMessage() {		
		System.out.println("\nThank you for choosing AuctionCentral!");
		System.out.println("Have a nice day!");		
	}
}
