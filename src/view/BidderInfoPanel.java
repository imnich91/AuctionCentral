package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Bidder;
import model.Calendar;
import model.Staff;

public class BidderInfoPanel extends JPanel{

	/**
	 * Serializable Id.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The calendar the has the current date
	 */
	private Calendar myCalendar;
	
	/**
	 * A JLabel that stores displays all the auction info
	 */
	private JLabel myBidderInfo;
	
	/**
	 * A constructor to setup the Staff Info panel for the GUI
	 * @param theCalendar
	 */
	public BidderInfoPanel (Calendar theCalendar) {
		
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		myCalendar = theCalendar;	
		myBidderInfo = new JLabel();
		
					
		myBidderInfo.setFont(new Font("Sans Serif", Font.BOLD, 18));		
		myBidderInfo.setHorizontalAlignment(SwingConstants.CENTER);
		myBidderInfo.setVerticalAlignment(SwingConstants.CENTER);
		
		add(myBidderInfo, BorderLayout.NORTH);			
	}
	
	/**
	 * Set the header with the logged in staff info
	 * @param theStaff the staff that is logged in
	 */
	public void setHeader(Bidder theBidder) {
		
		myBidderInfo.setText("");
		
		String text = "<html>Currently viewing auctions as Bidder<br> Logged in: " + theBidder.getName() + "</html>";
		

		myBidderInfo.setText(text);
		
		
	}
}
