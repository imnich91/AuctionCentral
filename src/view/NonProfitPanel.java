/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */

package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Used to build the non-profit JPanel.
 * 
 * @author Colin Casey
 */
public class NonProfitPanel extends JPanel {
	
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
	 * Used to build the JPanel.
	 * 
	 * @param theFrame the frame everything is loaded into
	 */
	public NonProfitPanel(final JFrame theFrame) {
		setLayout(new BorderLayout());
		myButtons = new JPanel();
		
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
}
