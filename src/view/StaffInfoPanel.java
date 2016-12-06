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
import model.Staff;
	
public class StaffInfoPanel extends JPanel{
	
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
		private JLabel myAuctionInfo;
		
		/**
		 * A constructor to setup the Staff Info panel for the GUI
		 * @param theCalendar
		 */
		public StaffInfoPanel (Calendar theCalendar) {
			
			setLayout(new BorderLayout());
			setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
			myCalendar = theCalendar;	
			myAuctionInfo = new JLabel();
			
						
			myAuctionInfo.setFont(new Font("Sans Serif", Font.BOLD, 18));		
			myAuctionInfo.setHorizontalAlignment(SwingConstants.CENTER);
			myAuctionInfo.setVerticalAlignment(SwingConstants.CENTER);
			
			add(myAuctionInfo, BorderLayout.NORTH);			
		}
		
		/**
		 * Set the header with the logged in staff info
		 * @param theStaff the staff that is logged in
		 */
		public void setHeader(Staff theStaff) {
			
			myAuctionInfo.setText("");
			
			String text = "<html>Auction Central Staff<br> Logged in: " + theStaff.getName() 
							+ "<br>" + "Number of auctions in the system: " + myCalendar.getAuctionsTotal() + "</html>";
			

			myAuctionInfo.setText(text);
			
			
		}
}
