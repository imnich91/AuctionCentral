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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
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
    private static final Dimension MY_SIZE = new Dimension(500, 500);
	
    /**
     * Frame that all panels are put in.
     */
    private final JFrame myFrame = new JFrame("Auction Central");
    
    /**
     * 
     */
    private JPanel myCards;
    
    /**
     * 
     */
    private final static String STAFFPANEL = "STAFF PANEL";
    
    /**
     * 
     */
    private final static String NONPROFITPANEL = "NONPROFIT PANEL";
    
    /**
     * 
     */
    private final static String BIDDERPANEL = "BIDDER PANEL";
    
    /**
     * 
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
     * 
     */
    
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
        myLoginPanel = new Login(myFrame);
        myStaffPanel = new StaffPanel(myFrame);
        //myFrame.add(myLoginPanel, BorderLayout.CENTER);
        setupListeners();
        myCards = new JPanel(new CardLayout());
        
        myCards.add(LOGINPANEL, myLoginPanel);
        myCards.add(STAFFPANEL, myStaffPanel);
        
        
        myFrame.add(myCards, BorderLayout.CENTER);
        myFrame.setVisible(true);
        
       

    }
    
    private void setupListeners(){
    	myLoginPanel.addPropertyChangeListener(this);
    }

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent theEvent) {
		if(theEvent.getPropertyName().equals("LOGGEDIN")) {
			CardLayout c1 = (CardLayout)(myCards.getLayout());
			c1.show(myCards, STAFFPANEL);
		}
		
	}


}
