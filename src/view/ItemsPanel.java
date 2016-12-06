package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import model.Auction;
import model.Calendar;
import model.Item;
import model.NonProfit;

public class ItemsPanel extends JPanel implements Observer {
	
	/**
	 * Serializable Id.
	 */
	private static final long serialVersionUID = 1L;
	

	public static final String NEW_ITEM_SELECTED = "New Item has been selected";

	private Calendar myCalendar;
	
	private NonProfit myNonProfit;
	
	
	private ButtonGroup myItemsGroup;
	
	private JPanel myItems;
	
	
	public ItemsPanel(NonProfit theNonProfit, Calendar theCalendar) {
		
		myItems = new JPanel(new GridLayout(0, 1));
		setLayout(new BorderLayout());
		
		myNonProfit = theNonProfit;
		myCalendar = theCalendar;
		myItemsGroup = new ButtonGroup();		
		
	}

	public void displayItems () {	
				
		try {
			Auction ourAuction = myCalendar.getAuctionForOrganization(myNonProfit);		
			ArrayList<Item> items = (ArrayList)ourAuction.getInventory();
			
			
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


	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
		
	}
	
	private void fireChange(int oldValue, int newValue) {
		
		firePropertyChange(NEW_ITEM_SELECTED, oldValue, newValue);	
		
	}
	
	
	
	private class ItemsButton extends JToggleButton {

		private Item myItem;		
		private int myItemNumber;		
		
		public ItemsButton (Item theItem) {			
			
			myItem = theItem;
			
			setText(setItemText());	
			setFont(new Font("Sans Serif", Font.PLAIN, 18));
			
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
			str.append("<html><div style = 'text-align:left'>");
					
			Integer i = myItem.getItemNumber();			
			int numLen = i.toString().length();
			
			
			str.append(myItem.getItemNumber());
			
			while (numLen < 5) {
				str.append(" &nbsp ");
				numLen++;
			}
			
			str.append(myItem.getItemName());		
			numLen = myItem.getItemName().length();
			
			while (numLen < 30) {
							
				str.append(" &nbsp ");
				numLen++;
			}		
			
			str.append("$" + myItem.getItemMinBid());	
			str.append("</div></html>");
			
			return str.toString();
			
		}
				
		public int getItemNumber() {
			
			return myItem.getItemNumber();		
					
		}			
				
	}

}
