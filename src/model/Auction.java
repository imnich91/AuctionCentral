/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */

package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import model.Item;

/**
 * This class is used to make the
 * auctions.
 * 
 * @author Ian Nicholas, Dmtriy Onishchenko (method add item),
 * Colin Casey some java doc 
 */
public class Auction implements Serializable{
	/**
	 * Used to Save data.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Collection of all items.
	 */
	private Collection<Item> myInventory;
	
	 /**
	  * The non-profit name.
	  */
	private String myNonProfit;
	
	/**
	 * The time of auction.
	 */
	private Time myTime;
	
	/**
	 * The date of auction.
	 */
	private Date myDate;
	
	/**
	 * Used to make auction.
	 * 
	 * @param theNonProfit the name of non-profit
	 * @param theDate the date of auction
	 * @param theTime the time of auction
	 */
	public Auction(String theNonProfit, Date theDate, Time theTime) {
		myInventory = new ArrayList<Item>();	
		myNonProfit = theNonProfit;
		myDate = theDate;
		myTime = theTime;
	}
	
	/**
	 * Used to get the inventory from an auction.
	 * 
	 * @return inventory
	 */
	public Collection<Item> getInventory() {
		return myInventory;
	}
	
	/**
	 * Used to get the name of non-profit
	 * 
	 * @return the name of non-profit
	 */
	public String getName() {
		return myNonProfit;
	}
	
	/**
	 * The time of the auction.
	 * 
	 * @return time of auction
	 */
	public Time getTime() {
		return myTime;
	}
	
	/**
	 * The date of auction.
	 * 
	 * @return date of auction
	 */
	public Date getDate() {
		return myDate;
	}
	
	
	
	/**
	 * Checks to see that all of the required fields are specified when entering an
	 * inventory item. 
	 * @param theItem the item to be added to the inventory
	 * @return true if all required fields are specified 
	 * false otherwise. 
	 */
	public boolean checkRequiredItemFields(Item theItem) {
			
		return !(theItem.getItemName().equals("") || theItem.getItemCondition().equals("") ||
				theItem.getItemSize().equals("") || theItem.getItemMinBid() <= 0);
	}
	
	
	
	/** 
	 * Checks to see that the contact person for this non-profit organization 
	 * associated is associated with this auction. 
	 * @param theCurrUser the current NonProfit user
	 * @return true if current user is the contact person for the current auction
	 * false otherwise
	 */
	public boolean checkContactPerson(NonProfit theCurrUser) {
		
		return theCurrUser.getOrgName().equals(myNonProfit);	
	}
	
	/**
	 * Checks for duplicates of an item. 
	 * User story #3 business rule b.
	 * @param theItem the item we user wants to add
	 * @return true if added false otherwise
	 */
	public boolean checkForDuplicates(Item theItem) {
		
		boolean added = true;
		
		for (Item Item: myInventory) {
			
			if (Item.getItemName().equals(theItem.getItemName()) &&
					Item.getItemDonor().equals(theItem.getItemDonor())) {
						
						added = false;											
					}		
		}			
		return added;		
	}
	
	/**
	 * Used to add an item to the auction.
	 * 
	 * @param theCurrUser User making request
	 * @param theItem the item to be added
	 * 
	 * @return true it worked/ false it failed
	 */
	public boolean addItem(NonProfit theCurrUser, Item theItem) {		
		
		boolean added = true;
		
		added = checkForDuplicates(theItem);		
		added = checkContactPerson(theCurrUser);
		added = checkRequiredItemFields(theItem);
				
		if (added) {			
			myInventory.add(theItem);			

			// assign the item number to the index of the arrayList.
			theItem.setItemNumber(((ArrayList<Item>)myInventory).indexOf(theItem));
		}		
		
		return added;

	}
	
	
	/**
	 * @author Dmitriy Onishchenko
	 * Removes an inventory item from the auction.
	 * @param theUser the non profit contac person
	 * @param itemNumber the Item number
	 * @param theAuctionDate the auction date
	 * @param theCurrentDate the current date
	 * @return true if removed fale otherwise
	 */
	public boolean removeItem(NonProfit theUser, int itemNumber, Date theAuctionDate, Date theCurrentDate) {
		
		boolean removed = Calendar.isMoreThanTwoDays(theAuctionDate, theCurrentDate);		
		
		// if valid date to remove and correct user
		if(removed && checkContactPerson(theUser)) {			
			
			try{
				((ArrayList)myInventory).remove(itemNumber);
				removed = true;
			}
			catch (ArrayIndexOutOfBoundsException e) {					
				removed = false;
			}		
			
			if(itemNumber >= 0) {			
				// fix item numbers 
				for(int i = itemNumber; i < myInventory.size(); i++) {			
					((Item)((ArrayList)myInventory).get(i)).setItemNumber(i);			
				}
			}			
		}
		
		return removed;
	}
	
	
	
	
	/**
	 * {@inheritDoc}.
	 * A representation of the Auction.	
	 */
	public String toString() {
		StringBuilder str = new StringBuilder(myNonProfit);
		str.append(", ");
		str.append(myDate.toString());
		str.append(", ");
		str.append(myTime.toString());		
		
		return str.toString();
	}
}
