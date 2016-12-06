/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
		myCenterPanel = new JPanel();
		add(myCenterPanel, BorderLayout.CENTER);
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
				System.out.println("auctions:" + myCalendar.getAuctionsAllowed());
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
