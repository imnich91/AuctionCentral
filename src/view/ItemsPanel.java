/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */

package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import model.Auction;
import model.Calendar;
import model.Item;
import model.NonProfit;


/** 
 * Displays all item available for a particular auction.
 * @author Dmitriy Onishchenko
 *
 */
public class ItemsPanel extends JPanel {
	
	/**
	 * Serializable Id.
	 */
	private static final long serialVersionUID = 1L;
	

	/**
	 * The constant string that is used to display when a new item has been selected.
	 */
	public static final String NEW_ITEM_SELECTED = "New Item has been selected";

	/**
	 * A calendar with the current date.
	 */
	private Calendar myCalendar;
	
	/**
	 * The non profit that has an auction with items
	 */
	private NonProfit myNonProfit;
	
	/**
	 * A button group for the items in an inventory
	 */
	private ButtonGroup myItemsGroup;
	
	/**
	 * A JPanel that display all the items
	 */
	private JPanel myItems;
	
	/**
	 * The constructor used to setup the items panel for the non profit
	 * @param theNonProfit the non profit who has an auction containing items in it
	 * @param theCalendar the calendar with the current day
	 */
	public ItemsPanel(NonProfit theNonProfit, Calendar theCalendar) {
		
		myItems = new JPanel(new GridLayout(0, 1));
		setLayout(new BorderLayout());
		
		myNonProfit = theNonProfit;
		myCalendar = theCalendar;
		myItemsGroup = new ButtonGroup();		
	
		
	}

	/**
	 * A method that display all the items in an auction
	 */
	public void displayItems () {	
				
		try {
			Auction ourAuction = myCalendar.getAuctionForOrganization(myNonProfit);		
			ArrayList<Item> items = (ArrayList<Item>)ourAuction.getInventory();
			
			
			for (int i = 0; i < items.size(); i++) {
				
				ItemsButton itemB = new ItemsButton(items.get(i));				
				myItemsGroup.add(itemB);
				
				JPanel p = new JPanel(new BorderLayout());
				p.add(itemB);			
				
				myItems.add(p);			
				
			}
			
			this.add(myItems, BorderLayout.NORTH);
			
						
		} catch (NullPointerException e) {
			// display nothing
		}
	}

	
	private void fireChange(int oldValue, int newValue) {
		
		firePropertyChange(NEW_ITEM_SELECTED, oldValue, newValue);	
		
	}
	
	
	
	/**
	 * Inner class for making item buttons
	 * @author Dmitriy Onishchenko
	 *
	 */
	private class ItemsButton extends JToggleButton {

		/**
		 *  serializable id
		 */
		private static final long serialVersionUID = 1L;
		
		/**
		 * the item.
		 */
		private Item myItem;
		
		public ItemsButton (Item theItem) {			
			
			myItem = theItem;
			
			setText(setItemText());	
			setFont(new Font( Font.MONOSPACED, Font.PLAIN, 18));
			
			setHorizontalAlignment(SwingConstants.LEFT);			
			
			addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {				
					
					int num = ((ItemsButton)e.getSource()).getItemNumber();					
					fireChange(-1, num);					
					
				}
			});
			
		}
		
		
		private String setItemText() {
			
			StringBuilder str = new StringBuilder();
			str.append(String.format("%-5s", myItem.getItemNumber()));
			str.append(String.format("%-30s %s" , myItem.getItemName(), "$" + myItem.getItemMinBid()));	
	
			return str.toString();
			
		}
				
		public int getItemNumber() {
			
			return myItem.getItemNumber();		
					
		}			
	}

}
