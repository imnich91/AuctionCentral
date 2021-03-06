/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import model.Calendar;
import model.Staff;
import model.User;

/**
 *  A staff panel GUI that displays the user stories that a staff can do.
 * @author imnic
 * @author Colin Casey java doc
 */
public class StaffPanel extends JPanel implements Observer {
	/**
	 * The Center Panel for the staff
	 */
	private JPanel myCenterPanel;
	
	/**
	 * The calendar with today's date.
	 */
	private Calendar myCalendar;
	
	/**
	 * The panel that contains all the buttons for Staff
	 */
	private JPanel myButtonPanel;
	
	/**
	 * The information panel about the staff
	 */
	private StaffInfoPanel myStaffInfo;
	
	/**
	 * The logged in staff user.
	 */
	private Staff myStaff;
	
	/**
	 * The Frame that holds all the panels.
	 */
	private final JFrame myFrame;
	
	/**
	 * An array with the names of the each day
	 */
	private static final String[] DAYNAMES = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	
	/**
	 * The font size for the title
	 */
	private static final int TITLE_FONT_SIZE = 24;
	
	/**
	 * A list with all the days
	 */
	private List<Integer> myDays;
	
	/**
	 * The width of the panel.
	 */
	private static final int BORDER_WIDTH = 4;
	
	/**
	 * Panel for displaying the Staff view
	 * @param theFrame AuctionCentralGui
	 * @param theCalendar holding auctions. 
	 */
	public StaffPanel(final JFrame theFrame, Calendar theCalendar) {
		myFrame = theFrame;
		setLayout(new BorderLayout());
		myCalendar = theCalendar;
		setupNorthPanel();
		setupCenterPanel();
		setupSouthPanel();
		myCalendar.addObserver(this);
		
	}
	
	/**
	 * helper method to setup north panel. 
	 */
	private void setupNorthPanel() {
		myStaffInfo = new StaffInfoPanel(myCalendar);
		add(myStaffInfo, BorderLayout.NORTH);
	}
	
	/**
	 * helper method to setup center panel. 
	 */
	private void setupCenterPanel() {
		myCenterPanel = new JPanel(new GridLayout(0, 7));
		drawCalendar();
		add(myCenterPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Creates and displays the calendar. 
	 */
	private void drawCalendar() {
		setupBorderCalendar();
		setupDaysOfWeek();
		setupCalendar();
	}
	
	/**
	 * Used to set up a border around the calendar.
	 */
	private void setupBorderCalendar() {
		TitledBorder title = BorderFactory.createTitledBorder(myCalendar.getCurrentDay().getMonth().toUpperCase());
		title.setTitleColor(Color.BLACK);
		title.setTitleJustification(TitledBorder.CENTER);
		title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0, true));
		title.setTitleFont(new Font(Font.SERIF, Font.BOLD, TITLE_FONT_SIZE));
		myCenterPanel.setBorder(title);
	}
	
	/**
	 * USed to set up the days of the week.
	 */
	private void setupDaysOfWeek() {
		JLabel label;
		
		for(String days: DAYNAMES) {
			label = new JLabel(days);
			label.setHorizontalAlignment(label.CENTER);
			label.setBorder(BorderFactory.createMatteBorder(BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, Color.BLACK));  
			myCenterPanel.add(label);
		}
	}                     
	
	/**
	 * Used to set up the calendar.
	 */
	private void setupCalendar() {
		myDays = myCalendar.createMonth();
		
		
		JLabel label;
		for(int days: myDays) {
			if(days == 0) {
				label = new JLabel(" ");
			} else {
				label = new JLabel(Integer.toString(days) + ": " + myCalendar.getCalendar().get(days -1).getNumAuctions());
			}
			label.setHorizontalAlignment(label.CENTER);
			label.setBorder(BorderFactory.createMatteBorder(BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, Color.BLACK));  
			myCenterPanel.add(label);
		}
	}
	
	/**
	 * Used to set up south panel which holds
	 * all of the buttons.
	 */
	private void setupSouthPanel() {
		myButtonPanel = new JPanel();
		makeButtonLogout();
		makeMaxAuctionButton();
		add(myButtonPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Used to make logout button.
	 */
	private void makeButtonLogout() {
		final JButton logout = new JButton("Logout");
		logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				firePropertyChange("LOGIN", "Bidder", "Login");
			}
		});
		myButtonPanel.add(logout);
	}
	
	/**
	 * Used to change max auctions allowed.
	 */
	private void makeMaxAuctionButton() {
		final JButton maxAuctions= new JButton("Update Auctions Allowed");
		
		maxAuctions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				String result = JOptionPane.showInputDialog(myFrame, "How many auctions per month?"
						, "Update Max Auctions", JOptionPane.QUESTION_MESSAGE);
				if(result != null) {
					myCalendar.setAuctionsAllowed(Integer.parseInt(result));
				}
			}
		});
		
		myButtonPanel.add(maxAuctions);
	}
	
	
	/**
	 * Used to keep center panel up to date at all
	 * times.
	 */
	@Override
	public void update(Observable arg0, Object arg1) {

		remove(myCenterPanel);
		myCenterPanel =  new JPanel(new GridLayout(0, 7));
		drawCalendar();
		add(myCenterPanel, BorderLayout.CENTER);
		
	
	}
	/**
	 * Setup the header for the staff page with staff information
	 */
	public void setUpStaffInfo() {
		myStaffInfo.setHeader(myStaff);
	}
    
	/**
	 * Used to set who is currently login.
	 * @param theUser the user
	 */
	public void setUser(Staff theUser) {
		myStaff = theUser;
	}
}
