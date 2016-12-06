/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Calendar;
import model.NonProfit;


/**
 * A non profit information panel that display all the information for the non profit.
 * @author Dmitriy Onishchenko
 *
 */
public class NonProfitInfoPanel extends JPanel {
	
	
	/**
	 * Serializable Id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The calendar that starts with the current date.
	 */
	private Calendar myCalendar;
	
	/**
	 * The logged in non profit.
	 */
	private NonProfit myNonProfit;
	
	/**
	 * A JLabel that display all the auction information that the non profit has.
	 */
	private JLabel myAuctionInfo;
	
	/**
	 * The JLabel for the non profit user.
	 */
	private JLabel myUser;
	
	/**
	 * The JLabel that displays the inventory of an auction
	 */
	private JLabel myInventory;
	
	/**
	 * A constructor for the non profit user info panel
	 * @param theCalendar 
	 */
	public NonProfitInfoPanel (Calendar theCalendar) {
		
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		myCalendar = theCalendar;	
		myAuctionInfo = new JLabel();
		myInventory = new JLabel();
		myUser = new JLabel();
		
		
		myAuctionInfo.setFont(new Font("Sans Serif", Font.BOLD, 18));		
		myAuctionInfo.setHorizontalAlignment(SwingConstants.CENTER);
		myAuctionInfo.setVerticalAlignment(SwingConstants.CENTER);
		
		myInventory.setFont(new Font("Sans Serif", Font.BOLD, 18));			
		myInventory.setHorizontalAlignment(SwingConstants.LEFT);
		
		myUser.setFont(new Font("Sans Serif", Font.BOLD, 18));
		myUser.setHorizontalAlignment(SwingConstants.LEFT);
		
		
		add(myUser, BorderLayout.NORTH);
		add(myAuctionInfo, BorderLayout.CENTER);
		add(myInventory, BorderLayout.SOUTH);
		
	}
	
	
	public void setNonProfit(NonProfit theNonProfit) {
		
		myNonProfit = theNonProfit;
	}
	
	/**
	 * A method that changes the text for the non profit if he has an auction
	 */
	public void setTextHasAuction() {
		
		myUser.setText("");
		myAuctionInfo.setText("");
		myInventory.setText("");
		
//		myUser.setText("Logged in as: " + myNonProfit.getName() + " (Non Profit)");
		
		String text = "<html><div style='text-align: center;'>Your Auction Details......<br></div><html>";

		String item = "<html><br><div style = 'text-align:left'>Inventory<br>Item # &nbsp Item Name&nbsp&nbsp"
				+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
				+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
				+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
				+ "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"
				+ "&nbsp&nbsp&nbsp&nbsp Min bid</div><html>";
		
		
		myAuctionInfo.setText(text + (myCalendar.getAuctionForOrganization(myNonProfit).toString() + "<html><hr></html>"));
		myInventory.setText(item);
		
		
	}
	
	/**
	 * A method that changes the text for the non profit if he does not have an auction
	 */
	public void setTextNoAuction() {
		
		myUser.setText("");
		myAuctionInfo.setText("");
		myInventory.setText("");
		
		
		myUser.setText("Logged in as: " + myNonProfit.getName());
		String text = "<html><div style='text-align: center;'>Your Auction Details......<br></div><html>";

		myInventory.setText(text + "<html>You currently do not have a sheduled Auction, "
				+ "<br>Submit an Auction request to schedule one.</html>");
		
	}
	

}
