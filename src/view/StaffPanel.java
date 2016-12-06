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
 *
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
	
	public StaffPanel(final JFrame theFrame) {
		myFrame = theFrame;
		setLayout(new BorderLayout());
		myCalendar = new Calendar();
		setupNorthPanel();
		setupCenterPanel();
		setupSouthPanel();
		myCalendar.addObserver(this);
		
	}
	
	private void setupNorthPanel() {
		myStaffInfo = new StaffInfoPanel(myCalendar);
		add(myStaffInfo, BorderLayout.NORTH);
	}
	
	private void setupCenterPanel() {
		myCenterPanel = new JPanel(new GridLayout(0, 7));
		drawCalendar();
		add(myCenterPanel, BorderLayout.CENTER);
	}
	
	private void drawCalendar() {
		setupBorderCalendar();
		setupDaysOfWeek();
		setupCalendar();
	}
	
	private void setupBorderCalendar() {
		TitledBorder title = BorderFactory.createTitledBorder(myCalendar.getCurrentDay().getMonth().toUpperCase());
		title.setTitleColor(Color.BLACK);
		title.setTitleJustification(TitledBorder.CENTER);
		title.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0, true));
		title.setTitleFont(new Font(Font.SERIF, Font.BOLD, TITLE_FONT_SIZE));
		myCenterPanel.setBorder(title);
	}
	
	private void setupDaysOfWeek() {
		JLabel label;
		for(String days: DAYNAMES) {
			label = new JLabel(days);
			label.setBorder(BorderFactory.createMatteBorder(BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, Color.BLACK));  
			myCenterPanel.add(label);
		}
	}                     
	
	private void setupCalendar() {
		int start;
		switch(LocalDate.now().getDayOfWeek().getValue()){
	        
	    	case(1):
	    		start = 1;
	    	    break;
	    	case(2):
	    		start = 2;
	    	    break;
	    	case(3):
	    		start = 3;
	    	    break;
	    	case(4):
	    		start = 4;
	    	    break;
	    	case(5):
	    		start = 5;
	    	    break;
	    	case(6):
	    		start = 6;
	    	    break;
	    	default:
	    		start = 0;
	    		break;
	    }
		myDays = new ArrayList<Integer>();
		int count = 0;
		for(int i = 1; i <= 30; i++) {
			if(i <= start) {
				myDays.add(0);
			} else if(LocalDate.now().getDayOfMonth() + count > LocalDate.now().getMonth().maxLength()) {
				myDays.add(LocalDate.now().getDayOfMonth() + count - 31);
				count++;
			}
			else {
				myDays.add(LocalDate.now().getDayOfMonth() + count++);
			}
		}
		JLabel label;
		for(int days: myDays) {
			if(days == 0) {
				label = new JLabel(" ");
			} else {
				label = new JLabel(Integer.toString(days) + ": " + myCalendar.getCalendar().get(days -1).getNumAuctions());
			}
			label.setBorder(BorderFactory.createMatteBorder(BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, BORDER_WIDTH, Color.BLACK));  
			myCenterPanel.add(label);
		}
	}
	
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
				myCalendar.setAuctionsAllowed(Integer.parseInt(JOptionPane.showInputDialog(myFrame, "How many auctions per month?"
						, "Update Max Auctions", JOptionPane.QUESTION_MESSAGE)));
			}
		});
		
		myButtonPanel.add(maxAuctions);
	}
	
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
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
