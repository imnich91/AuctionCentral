package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import model.Item;

public class ItemsButtons extends JToggleButton {

	public static final String NEW_ITEM_SELECTED = "New Item has been selected";

	private Item myItem;
	
	private int myItemNumber;
	
	
	
	public ItemsButtons (Item theItem) {
		
		
		myItem = theItem;
		
		setText(setItemText());	
		setFont(new Font("Sans Serif", Font.PLAIN, 18));
		
		setHorizontalAlignment(SwingConstants.LEFT);
		
		
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				
				int num = ((ItemsButtons)e.getSource()).getItemNumber();
				
				firePropertyChange(NEW_ITEM_SELECTED, myItem.getItemNumber(), num);	
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
