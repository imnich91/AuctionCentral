/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */

package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

/**
 * This class is the driver of the
 * program.
 * 
 * @author Colin Casey
 */
public class AuctionCentralGUI implements Observer {
	
    /**
     * Used to set min size of window.
     */
    private static final Dimension MY_SIZE = new Dimension(500, 500);
	
    /**
     * Frame that all panels are put in.
     */
    private final JFrame myFrame = new JFrame("Auction Central");
    
    /**
     * Used to keep track of Login panel.
     */
	private Login myLoginPanel;
    
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
        myFrame.add(myLoginPanel, BorderLayout.CENTER);
        
        myFrame.setVisible(true);

    }

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
