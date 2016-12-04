/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */
 
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;


/**
 * The Item class manages creates and manages an individual item for an auction.
 * Also maintains a collection of bids placed for that particular item.
 * @author Georgia Wade
 * @author Colin Casey some Java doc
 */
public class Item implements Serializable{
	
	/**
	 * Used to save data in item
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Used to tell items apart.
	 */
	private int theItemNumber = -1;
	
	/**
	 * Name of the item.
	 */
	private String theItemName;
	
	/**
	 * Used to keep track of Condition of the item.
	 */
	private String theItemCondition;
	
	/**
	 * Used to keep track of item size.
	 */
	private String theItemSize;
	
	/**
	 * The lowest bid a bidder can place on an item.
	 */
	private int theItemMinBid;
	
	/**
	 * The donor for the item.
	 */
	private String theItemDonor;
	
	/**
	 * Short description of the item.
	 */
	private String theItemDescript;
	
	/**
	 * Used for addition comment.
	 */
	private String theItemAdditComment;	
	
	/**
	 * Used to keep track of all bids placed on an item.
	 */
	private Collection <Bid> theBunchObids;
	
	/**
	 * Used to create an item.
	 * 
	 * @param theName item  name
	 * @param theCond item condition
	 * @param theSize item size
	 * @param theMinBid lowest bid that can be placed on item
	 * @param theDonor item donor
	 * @param theDescript item description
	 * @param theAddCom item addition comment
	 */
	public Item (String theName,  String theCond, String theSize, int theMinBid,
			String theDonor, String theDescript, String theAddCom) {
		
		theItemName = theName;
		theItemCondition = theCond;
		theItemSize = theSize;
		theItemMinBid = theMinBid;
		theItemDonor = theDonor;
		theItemDescript = theDescript;
		theItemAdditComment = theAddCom;
		
		theBunchObids = new ArrayList<Bid>();
	}
	
	/**
	 * Used to set every items number.
	 * 
	 * @param theNumber number to be set
	 */
	public void setItemNumber(int theNumber)
	{
		theItemNumber = theNumber;
	}
	
	/**
	 * This method is used to check if bid is a
	 * valid price.
	 * @param theBidPrice bid
	 * @return true bid is valid/false bid is invalid
	 */
	public boolean isValidBidPrice(int theBidPrice){
		if(theBidPrice < theItemMinBid){
			return false;
		}
		else{
			return true;
		}
	}
	

	/**
	 * Creates a new Bid based on the parameters and adds it to the collection of bids
	 * @param theUser the user trying to make a bid
	 * @param theBidPrice amount bidder is bidding on item
	 * @return true if bid was made false otherwise
	 */
	public boolean makeBid(User theUser, int theBidPrice){
	
		boolean validBid = isValidBidPrice(theBidPrice);		
	
	
		if (isValidBidder(theUser)) {		
			
			for(int i=0; i < theBunchObids.size(); i++) {
				
				Bid currBid = ((ArrayList<Bid>)theBunchObids).get(i);
				
				// check to see if bidder already has a bid on the item
				if (currBid.getBidder().equals(theUser.getName())){				
					validBid = false;
				}				
			}
			
			if(validBid){
				theBunchObids.add(new Bid(theUser.getName(), theBidPrice));
			}			
		} else 
			validBid = false;
		
		return validBid;
	}
	
	
	
	/**
	 * Cancels a bid for the specified user.
	 * 
	 * @param theUser the bidder that wants to cancel a bid
	 * @param theAuctionDate the auction start date
	 * @param theCurrentDate the current date
	 * @return true if bid has been successfully canceled otherwise false.
	 */
	public boolean cancelBid(User theUser, Date theAuctionDate, Date theCurrentDate) {
		
		// make sure not canceling less than 2 days prior to auciton date
		boolean canceled = Calendar.isMoreThanTwoDays(theAuctionDate, theCurrentDate);
			
	
		if (canceled && isValidBidder(theUser)) {			
			
			boolean found = false;
			for(int i=0; i < theBunchObids.size(); i++) {
				Bid currBid = ((ArrayList<Bid>)theBunchObids).get(i);
				
				if (currBid.getBidder().equals(theUser.getName())) {
					theBunchObids.remove(currBid);
					found = true;
				}							
			}			
			canceled = found;			
		} 
		else  {
			canceled = false;
		}
		
		return canceled;
	}
	
	
	/**
	 * Checks to see if current user is a registered bidder.
	 * @param theUser the current user of the system. 
	 * @return returns true if user is a registered bidder otherwise returns false.
	 */
	public boolean isValidBidder(User theUser) {
		
		return !(theUser.getUserName().equals("") || theUser.getPassword().equals("") ||
				theUser instanceof Staff || theUser instanceof NonProfit);			
	}
	
		
	/**
	 * @return item's auction inventory number
	 */
	public int getItemNumber(){
		return theItemNumber;
	}
	
	/**
	 * @return item's official title
	 */
	public String getItemName(){
		return theItemName;
	}
	
	/**
	 * @return the condition of the item
	 */
	public String getItemCondition(){
		return theItemCondition;
	}
	
	/**
	 * @return the condition of the item
	 */
	public String getItemSize(){
		return theItemSize;
	}
	
	/**
	 * @return item's initial bidding price
	 */
	public int getItemMinBid(){
		return theItemMinBid;
	}
	
	/**
	 * @return the name of the person who donated the item
	 */
	public String getItemDonor(){
		return theItemDonor;
	}
	
	/**
	 * @return the storage location of the item
	 */
	public String getItemDescrpit(){
		return theItemDescript;
	}
	
	/**
	 * @return description/addition comments about the item
	 */
	public String getItemComments(){
		return theItemAdditComment;
	}
	
	/**
	 * @return collection of bids for the item
	 */
	public Collection<Bid> getBunchObids(){
		return theBunchObids;
	}
	

}
