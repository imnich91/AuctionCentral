/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */

package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import model.Calendar;
import model.NonProfit;

/**
 * Used to build the non-profit JPanel.
 * 
 * @author Colin Casey
 */
public class NonProfitPanel extends JPanel implements Observer {
	
	/**
	 * Used to save data.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Used to hold all Buttons.
	 */
	private JPanel myButtons;
	
	/**
	 * Used to give button access to all of the class.
	 */
	private JButton myCancelAuction;
	
	/**
	 * Used to give button access to all of the class.
	 */
	private JButton myAddAuction;
	
	/**
	 * Used to give button access to all of the class.
	 */
	private JButton myRemoveItem;
	
	/**
	 * Used to give button access to all of the class.
	 */
	private JButton myAddItem;
	
	/**
	 * Used to give button access to whole class.
	 */
	private JButton myLogout;
	
	/**
	 * The calendar for being currently used. 
	 */
	private Calendar myCalendar; 
	
	
	/**
	 * Label used to display the the current non profit information.
	 */
	private JLabel myAuctionInfo;

	/**
	 * The current non profit user.
	 */
	private NonProfit myCurrNonProfit;
	
	/**
	 * Used to build the JPanel.
	 * 
	 * @param theFrame the frame everything is loaded into
	 */
	public NonProfitPanel(final JFrame theFrame, final Calendar theCalendar) {
		setLayout(new BorderLayout());
		myButtons = new JPanel();
		myCalendar = theCalendar;
		
		
		//Makes all buttons
		
		makeButtonCancelAuction();
		makeButtonAddAuction();
		makeButtonRemoveItem();
		makeButtonAddItem();
		makeButtonLogout();
		
		
		
		//Adds all buttons to button of JPanel
		add(myButtons, BorderLayout.PAGE_END);
	}

	/**
	 * This method is used to cancel an auction request.
	 */
	private void makeButtonCancelAuction() {
		myCancelAuction = new JButton("Cancel Auction Request");
		myCancelAuction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				
			}
		});
		myButtons.add(myCancelAuction);
	}
	
	/**
	 * This method is used to add an auction request.
	 */
	private void makeButtonAddAuction() {
		myAddAuction = new JButton("Auction Request");
		myAddAuction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				
			}
		});
		myButtons.add(myAddAuction);
	}
	
	/**
	 * This method is used to remove an inventory item.
	 */
	private void makeButtonRemoveItem() {
		myRemoveItem = new JButton("Remove Item");
		myRemoveItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				
			}
		});
		myButtons.add(myRemoveItem);
	}
	
	/**
	 * This method is used to add an inventory item.
	 */
	private void makeButtonAddItem() {
		myAddItem = new JButton("Add Item");
		myAddItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				
			}
		});
		myButtons.add(myAddItem);
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

	
	private void makeJLabelAuctionInfo() {
		
		myAuctionInfo = new JLabel();
		myAuctionInfo.setFont(new Font("Sans Serif", Font.BOLD, 18));
		
		String text = "<html>Your Auction Details......<br><html>";
		
		
		if (myCalendar.getAuctionForOrganization(myCurrNonProfit) != null) {
			myAuctionInfo.setText(text + (myCalendar.getAuctionForOrganization(myCurrNonProfit).toString()));

		} else {			
			myAuctionInfo.setText(text + "<html>You currently do not have a sheduled Auction, "
					+ "<br>Submit an Auction request to schedule one.</html>");
		}		
		
		this.add(myAuctionInfo, BorderLayout.NORTH);	
		
	}
	
	
	
	public void setUpNonProfitInfo() {
		makeJLabelAuctionInfo();
	}
	
	public void setUser(NonProfit theNonProfit) {
		
		myCurrNonProfit = theNonProfit;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
}
