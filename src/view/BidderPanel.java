/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */

package view;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Auction;
import model.Bid;
import model.Calendar;
import model.Item;
import model.User;

/**
 * Used to build the bidder JPanel.
 * 
 * @author Colin Casey
 */
public class BidderPanel extends JPanel {
	
	/**
	 * Used to keep track of data.
	 * */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Used to hold all Buttons.
	 */
	private JPanel myButtons;
	
	/**
	 * Used to tell users information.
	 */
	private JPanel myTextPanel;
	
	/**
	 * Used to gain access to the frame.
	 */
	private JFrame myFrame;
	
	/**
	 * Used for formatting.
	 */
	private JPanel myMiddle;
	
	/**
	 * Used to give button access to whole class.
	 */
	private JButton myUpDateInfo;
	
	/**
	 * Used to give button access to whole class.
	 */
	private JButton myLogout;
	
	/**
	 * Used to give button access to whole class.
	 */
	private JButton myCancelBid;
	
	/**
	 * Used to give button access to whole class.
	 */
	private JButton myPlaceBid;
	
	/**
	 * Gives access to calendar object to the rest of the class.
	 */
	private Calendar myCalendar;
	
	/**
	 * Used to gave access to list of auctions.
	 */
	private ArrayList<Auction> myAuctions;
	
	/**
	 * Used to give access of login user to other classes.
	 */
	private User myUser;
	
	/**
	 * Used to keep track of non-profit
	 */
	private Auction myNonProfit;
	
	/**
	 * Used to keep track of item getting bid on.
	 */
	private Item myItem;
	
	/**
	 * Used keep track of all people who have bid.
	 */
	private Collection<Bid> myBids;
	
	/**
	 * Used to build the JPanel.
	 * @param theFrame the frame everything is loaded into
	 */
	public BidderPanel(final JFrame theFrame, final Calendar theCalendar) {
		setLayout(new BorderLayout());
		myFrame = theFrame;
		myItem = null;
		myBids = null;
		myNonProfit = null;
		myCalendar = theCalendar;
		myUser = null;
		myAuctions = (ArrayList<Auction>)myCalendar.getAuctions();
		myMiddle = new JPanel();
		myButtons = new JPanel();
		upDateInfoButton();
		cancelBidButton();
		placeBidButton();
		makeButtonLogout();
		add(myMiddle, BorderLayout.CENTER);
		add(myButtons, BorderLayout.PAGE_END);
	}
	
	/**
	 * Used to make a button.
	 */
	private void upDateInfoButton() {
		myUpDateInfo = new JButton("Update Information");
		myUpDateInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				//System.exit(1);
			}
		});
		myButtons.add(myUpDateInfo);
	}
	
	/**
	 * Used to make logout button.
	 */
	private void makeButtonLogout() {
		myLogout = new JButton("Logout");
		myLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				firePropertyChange("LOGIN", "Bidder", "Login");
			}
		});
		myButtons.add(myLogout);
	}
	
	/**
	 * Used to make a button.
	 */
	private void cancelBidButton() {
		myCancelBid = new JButton("Cancel Bid");
		myCancelBid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
			}
		});
		myButtons.add(myCancelBid);
	}
	
	/**
	 * Used to make a button.
	 */
	private void placeBidButton() {
		myPlaceBid = new JButton("Place Bid");
		myPlaceBid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				//ask user for auction they want to bid on.
				List<String> list = new ArrayList<String>();
				List<Auction> auctions =(myCalendar.getAuctions());
				for(Auction a : auctions) {
					list.add(a.getName());
				}
				String[] choices = (list).toArray(new String[0]);
				String input ="";
			    input = (String) JOptionPane.showInputDialog(null, "Choose now...",
			        "Non-Profit List", JOptionPane.QUESTION_MESSAGE, null,
			        choices, choices[0]);
			    
			    String item ="";
			    item = askForItem(input);
			    findItem(item);
			    if(alreadyBid()) {
			    	askForBid();
			    } else {
			    	popUpBidTwice();
			    }
			}

		});
		myButtons.add(myPlaceBid);
	}
	
	/**
	 * Tells user if bid passed or failed.
	 * @param theInt pass 1/ fail 0
	 */
	private void bidPassFail(int theInt) {
		if(theInt == 0) {
			JOptionPane.showMessageDialog(myFrame, "The bid you tried to place is an invalid"
					+ " number"
					, "Bid Error", JOptionPane.ERROR_MESSAGE);
		} if (theInt == 1) {
			JOptionPane.showMessageDialog(myFrame, "Your bid has been placed");
		}
	}
	
	/**
	 * Used to tell the bidder that they have already placed
	 * a bid on this item.
	 */
	private void popUpBidTwice() {
		JOptionPane.showMessageDialog(myFrame, "You have already Bid on this item once"
				, "Bid Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Makes a pop up telling the user that they
	 * already have a bid on this item.
	 * 
	 * @return if user is trying to make two bids
	 */
	private boolean alreadyBid() {
		boolean flag = true;
		myBids = myItem.getBunchObids();
		for(Bid a: myBids) {
			if((a.getBidder()).equals(myUser.getName())) {
				flag = false;
			}
		}
		return flag;
	}
	
	/**
	 * Used to ask user how much they want to bid
	 * on an item
	 * @param theItem item to bid on
	 */
	private void askForBid() {
		double Bid = Double.parseDouble(
	            JOptionPane.showInputDialog(this, itemToString()));
		if(!myItem.isValidBidPrice((int) Bid)) {
			bidPassFail(0);
		} else {
			placeBid((int) Bid);
			bidPassFail(1);
		}
	}
	
	/**
	 * Used to place bid
	 * 
	 * @param theBid the bid amount
	 */
	private void placeBid(final int theBid) {
		boolean flag = myItem.makeBid(myUser, theBid);
		myBids = myItem.getBunchObids();
		System.out.println(flag);
	}
	
	/**
	 * A method that turns all of the
	 * useful information into a string.
	 * 
	 * @return Item info
	 */
	public String itemToString() {
		String flag = "";
		flag += "Item Name: " + myItem.getItemName()+ "\n";
		flag += "Item Min Bid: " + myItem.getItemMinBid() + "\n";
		flag += "Item Descrpit: " + myItem.getItemDescrpit()+ "\n";
		flag += "\n Enter Your Bid";
		return flag;
	}
	
	/**
	 * Used to find item person wants to bid
	 * on.
	 * 
	 * @param theItem the name of the item
	 */
	private void findItem(String theItem) {
		if(!(myNonProfit).equals(null)) {
			Collection<Item> auctions = (myNonProfit.getInventory());
			for(Item a : auctions) {
				if(theItem == a.getItemName()){
					myItem = a;
				}
			}
		}
	}
	
	/**
	 * Used to make a pop up that will
	 * ask the user what item the want to bid on.
	 * 
	 * @param theNonProfit Non-profit name
	 * @return item to bid on
	 */
	private String askForItem(String theNonProfit) {
		String biddingItem = "";
		if (myCalendar != null) {
			List<Auction> auctions =(myCalendar.getAuctions());
			for(Auction a : auctions) {
				if(theNonProfit == a.getName()){
					myNonProfit = a;
				}
			}
			List<String> item = new ArrayList<String>();
			Collection<Item> inventory = myNonProfit.getInventory();
			for(Item b : inventory) {
				item.add(b.getItemName());
			}
			String[] items = (item).toArray(new String[0]);
			biddingItem = (String) JOptionPane.showInputDialog(null, "Choose now...",
					"Item List", JOptionPane.QUESTION_MESSAGE, null,
					items, items[0]);
		}
		return biddingItem;
		
	}
	
	/**
	 * Used to name current bidder.
	 */
	private void makeTextPanel() {
		myTextPanel = new JPanel();
		String name = myUser.getUserName();
		JLabel Jlabel = new JLabel("Login as: "+ name);
		myTextPanel.add(Jlabel);
		add(myTextPanel, BorderLayout.PAGE_START);
	}
	
	/**
	 * Used to set who is currently login.
	 * @param theUser the user
	 */
	public void setUser(User theUser) {
		myUser = theUser;
		makeTextPanel();
	}
}
