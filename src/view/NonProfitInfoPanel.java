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
 * 
 * @author Dmitriy Onishchenko
 *
 */
public class NonProfitInfoPanel extends JPanel {
	
	
	/**
	 * Serializable Id.
	 */
	private static final long serialVersionUID = 1L;

	private Calendar myCalendar;
	
	private NonProfit myNonProfit;
	
	private JLabel myAuctionInfo;
	
	private JLabel myUser;
	
	
	private JLabel myInventory;
	
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
