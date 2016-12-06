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

		private Calendar myCalendar;
		
		private JLabel myAuctionInfo;
		
		
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
		
		public void setHeader(Staff theStaff) {
			
			myAuctionInfo.setText("");
			
			String text = "<html>Auction Central Staff<br> Logged in: ";

			myAuctionInfo.setText(text + theStaff.getName() +"</html>");
			
			
		}
}
