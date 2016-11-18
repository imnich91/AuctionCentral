/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */

package userInterface;


import java.util.ArrayList;
import java.util.Scanner;

import model.Auction;
import model.Bid;
import model.Bidder;
import model.Calendar;
import model.Item;


/**
 * 
 * Out Bidde user interface class.
 * @author Dmitriy Onishchekno
 *
 */
public class BidderUI {
	
	private Bidder myCurrBidder;
	private Scanner myScanner;
	private Calendar myCalendar;
	
	private Auction myCurrAuction;
	private ArrayList<Item> myItems;
	
	public BidderUI(Bidder theBidder, Scanner theScanner,  Calendar theCalendar) {
		
		myScanner = theScanner;
		myCurrBidder = theBidder;
		myCalendar = theCalendar;	
		
	}	
	
	
	/**
	 * Displays the bidders menu.
	 */
	public void displayBidderMenu() {
		
		displayBidderInfo();

		System.out.println("What would you like to do? (enter a number)");
		System.out.println("1. View Available Auctions");
		System.out.println("2. Logout");
		System.out.print("  -> ");
		
		int input = myScanner.nextInt();	
		
		if (input == 1) {			
			displayAuctionList();				
		} else {
			displayLogoutMessage();			
		}		
	}
	
	
	// displays the list of available auctions
	private void displayAuctionList() {
		
		ArrayList<Auction> theAuctions = (ArrayList<Auction>)myCalendar.getAuctions();
		
		System.out.println();
		displayBidderInfo();		
		
		System.out.println("ID    Auction Name");
		
		for(int i = 0; i < theAuctions.size(); i++) {			
			System.out.println(i + 1 + "     " + theAuctions.get(i).getName());				
		}	
		
		System.out.println();
		System.out.println("Please choose an acution by choosing an auction id");
		System.out.print("  -> ");		
		
		int auctionIndex = myScanner.nextInt();
	
		myCurrAuction = theAuctions.get(auctionIndex - 1 );
		
		displayAuctionScreen(myCurrAuction);		
	}

	
	// displays current items offered for the selected auction
	private void displayAuctionScreen(Auction myCurrAuction) {
		
		myItems = (ArrayList<Item>)myCurrAuction.getInventory();
		
		System.out.println();
		displayBidderInfo();
		
		System.out.println(myCurrAuction.toString());
		System.out.println("Items offered for sale");
		
		displayItemsForCurrAuction(myItems);
		
		System.out.println();
		System.out.println("What would you like to do? (choose an option)");
		System.out.println("1. Bid on an item.");
		System.out.println("2. Go back.");
		System.out.println("3. Logout.");
		System.out.print("  -> ");
		
		int choice = myScanner.nextInt();
		
		switch (choice) {
			case 1:
				displayItemsForSaleMenu(myItems);
				break;
			case 2: 
				displayAuctionList();
				break;
			case 3:
				displayLogoutMessage();
				break;
			default:
				System.out.println("NO OPTION TO SELECT");
				break;		
		}		
	}
	
	
	private void displayItemsForSaleMenu(ArrayList<Item> theItems) {
		
		System.out.println();
		displayBidderInfo();
		System.out.println(myCurrAuction.toString());
		System.out.println("Items offered for sale");
		displayItemsForCurrAuction(theItems);
		
		System.out.println();
		System.out.println("Type item ID to get more information and bid on the item");
		System.out.print("  -> ");
		
		int choice = myScanner.nextInt();
		
		Item itemChoosen = theItems.get(choice);
		
		// next screen
		displayItemMenu(itemChoosen);
		
	}
	
	
	
	private void displayItemMenu(Item theItem) {
		
		System.out.println();
		displayBidderInfo();
		System.out.println(myCurrAuction.toString());
		
		System.out.print(theItem.getItemName().trim() + ", " + theItem.getItemCondition());
		System.out.println(" condition, $" + theItem.getItemMinBid());
		System.out.println(theItem.getItemDescrpit());
		
		System.out.println();
		System.out.println("What would you like to do?");
		System.out.println("1. Place bid on this item");
		System.out.println("2. Go back");
		System.out.println("3. Logout");
		System.out.print("  -> ");
		
		
		int choice = myScanner.nextInt();
		
		System.out.println();
		
		switch (choice) {
			case 1: 
				
				ArrayList<Bid> bids = (ArrayList<Bid>)theItem.getBunchObids();
				
				boolean madeBid = false;
				for (int i = 0; i < bids.size(); i++) {					
					
					if (bids.get(i).getBidder().equals(myCurrBidder.getName())) {
						madeBid = true;
					}
				}
					
				if (madeBid) {
					System.out.println("You already made a bid before");
					System.out.println("You cannot make another bid on this item.");
					System.out.println("Choose another item.");
					displayItemsForSaleMenu(myItems);

					
				} else {
					System.out.println("Enter bid of at least $" +
							theItem.getItemMinBid() + " (just enter a number): ");
					System.out.print("  -> ");
					int bidMade = myScanner.nextInt();	
					
					System.out.println();
					displayPlaceBidMenu(theItem, bidMade);
												
				}				
			
				break;
			case 2:
				displayItemsForSaleMenu(myItems);
				break;
			case 3:
				displayLogoutMessage();
				break;
			default :
				System.out.println("NO OPTION TO SELECT");
				break;
					
		}		
	}
	
	
	private void displayPlaceBidMenu(Item theItem, int theBid) {
		
		displayBidderInfo();
		System.out.println(myCurrAuction.toString());
		
		System.out.print(theItem.getItemName().trim() + ", " + theItem.getItemCondition());
		System.out.println(" condition $" + theItem.getItemMinBid());
		System.out.println(theItem.getItemDescrpit());
		
		System.out.println();
		System.out.println("What would you like to do?");
		System.out.println("1. Place bid of $" + theBid + " on " + theItem.getItemName());
		System.out.println("2. Go back to items list without bidding");
		System.out.println("3. Logout");
		System.out.print("  -> ");
		
		int choice = myScanner.nextInt();
		
		System.out.println();
		
		switch (choice) {
			case 1:
				
				if (theItem.makeBid(myCurrBidder, theBid)) {
					
					System.out.println("You have just placed a bid of $" + theBid +
							" on " + theItem.getItemName().trim());
					System.out.println(" AuctionCentral will notify "
									+ "you after " + myCurrAuction.getDate().toString() +
									" to let you know if you won the item.");
					
					displayItemsForSaleMenu(myItems);
					
				} else { // the bid that they placed is not valid.				
									
					System.out.println("Your bid was not valid");
					System.out.println("Enter bid of at least $" +
							theItem.getItemMinBid() + " (just enter a number): ");
					System.out.print("  -> ");
					
					int bidMade = myScanner.nextInt();				
					displayPlaceBidMenu(theItem, bidMade);					
														
				}				
				break;
			case 2: 
				displayItemsForSaleMenu(myItems);
				break;
			case 3:
				displayLogoutMessage();
				break;
			default:
				System.out.println("NO OPTION TO SELECT");
				break;	
		}	
		
	}
	
	
	// test this method
	public int getCurrentBiddersBid(Bidder theCurrBidder, ArrayList<Bid> theBids) {
		
		int biddersBid = -1;
		
		for (int i = 0; i < theBids.size(); i++) {
			
			if (theBids.get(i).getBidder().equals(theCurrBidder.getName())) {
				biddersBid = theBids.get(i).getBidPrice();
			}	
		}	
		
		return biddersBid;
	}
	
	@SuppressWarnings("unchecked")
	private void displayItemsForCurrAuction(ArrayList<Item> theItems) {
		
		System.out.println("Item #  Item Name                    Condition  Min Bid  My Bid");
		
		for (int i = 0; i < theItems.size(); i++) {
			
			System.out.print(i + "       " + theItems.get(i).getItemName() + "  ");
			System.out.print(theItems.get(i).getItemCondition() + "       ");
			System.out.print("$" + theItems.get(i).getItemMinBid() + "   ");
			
			int biddersBid =  getCurrentBiddersBid(myCurrBidder, (ArrayList<Bid>)theItems.get(i).getBunchObids());
			
			if (biddersBid > - 1) {
				System.out.print("  $" + biddersBid);				
			}
			
			System.out.println();	
			
		}		
	}
	
	private void displayBidderInfo() {
		System.out.println(UI.WELCOME);
		System.out.println(myCurrBidder.toString());
		System.out.println();
	}
	
	private void displayLogoutMessage() {		
		System.out.println("\nThank you for choosing AuctionCentral!");
		System.out.println("Have a nice day!");		
	}

}
