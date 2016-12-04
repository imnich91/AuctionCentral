/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */

package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.List;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import model.Calendar;
import model.User;
import javax.swing.JPanel;

/**
 * This class is the driver of the
 * program.
 * 
 * @author Colin Casey
 */
public class AuctionCentralGUI implements Observer, PropertyChangeListener{
	
    /**
     * Used to set min size of window.
     */
    private static final Dimension MY_SIZE = new Dimension(600, 600);
	
    /**
     * Frame that all panels are put in.
     */
    private final JFrame myFrame = new JFrame("Auction Central");
    
    /**
     * Used to load different panels into main frame.
     */
    private JPanel myCards;
    
    /**
     * Used to keep track of staff panel.
     */
    private final static String STAFFPANEL = "STAFF PANEL";
    
    /**
     * Used to keep track of Non-Profit panel.
     */
    private final static String NONPROFITPANEL = "NONPROFIT PANEL";
    
    /**
     * Used to keep track of Bidder panel.
     */
    private final static String BIDDERPANEL = "BIDDER PANEL";
    
    /**
     * Used to keep track of Login panel.
     */
    private final static String LOGINPANEL = "LOGIN PANEL";
    
    /**
     * Used to keep track of Login panel.
     */
	private Login myLoginPanel;
	
	/**
	 * Auction Central staff panel.
	 */
    private StaffPanel myStaffPanel;
	
    /**
     * Auction Central non-Profit Panel.
     */
    private NonProfitPanel myNonProfitPanel;
    
    /**
     * Auction Central Bidder Panel.
     */
    private BidderPanel myBidderPanel;
    
    /**
     * A list of all users.
     */
	private List<User> myUsers;
	
	/**
	 * A collection of all user logins.
	 */
	private HashMap<String, String> myUsersLogins;
	
	/**
	 * Gives access to calendar object to the rest of the class.
	 */
	private Calendar myCalendar;
	
	/**
	 * Sets up class.
	 * 
	 * @param theUsers list of all users
	 * @param theLogins collection of all logins
	 * @param theCalendar Calendar
	 */
	public AuctionCentralGUI(List<User> theUsers, HashMap<String, String> theLogins, Calendar theCalendar) {
		
		myUsers = theUsers;
		myUsersLogins = theLogins;
		myCalendar = theCalendar;
	}
	
    /**
     * The method that runs this class.
     */
    public void start() { 
    	
    	
    	//make frame
    	myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	myFrame.setSize(MY_SIZE);
    	final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        myFrame.setLocation(dim.width / 2 -  myFrame.getSize().width / 2, 
                dim.height / 2 - myFrame.getSize().height / 2);
        
        
        //add frame to panel
        myLoginPanel = new Login(myFrame, myUsers, myUsersLogins);
        myFrame.add(myLoginPanel, BorderLayout.CENTER);
        myStaffPanel = new StaffPanel(myFrame);
        myNonProfitPanel = new NonProfitPanel(myFrame);
        myBidderPanel = new BidderPanel(myFrame, myCalendar);
        //myFrame.add(myLoginPanel, BorderLayout.CENTER);
        setupListeners();
        myCards = new JPanel(new CardLayout());
        myCards.add(LOGINPANEL, myLoginPanel);
        myCards.add(STAFFPANEL, myStaffPanel);
        myCards.add(NONPROFITPANEL, myNonProfitPanel);
        myCards.add(BIDDERPANEL, myBidderPanel);
        
        myFrame.add(myCards, BorderLayout.CENTER);

        myFrame.setVisible(true);
    }
    
    /**
     * Used to add a property change listener to the Login panel
     */
    private void setupListeners(){
    	myLoginPanel.addPropertyChangeListener(this);
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	

	/**
	 * This method is used to change the JPanel currently in the
	 * frame.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent theEvent) {
		if(theEvent.getPropertyName().equals("Staff")) {
			CardLayout c1 = (CardLayout)(myCards.getLayout());
			c1.show(myCards, STAFFPANEL);
		}
		if(theEvent.getPropertyName().equals("Bidder")) {
			myBidderPanel.setUser(myLoginPanel.getUser());
			CardLayout c1 = (CardLayout)(myCards.getLayout());
			c1.show(myCards, BIDDERPANEL);
		}
		if(theEvent.getPropertyName().equals("NonProfit")) {
			CardLayout c1 = (CardLayout)(myCards.getLayout());
			c1.show(myCards, NONPROFITPANEL);
		}
	}


}
