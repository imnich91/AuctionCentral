/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */

package view;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Used to build the bidder JPanel.
 * 
 * @author Colin Casey
 */
public class BidderPanel extends JPanel {
	
	/**
	 * Used to keep track of data.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Used to hold all Buttons.
	 */
	private JPanel myButtons;
	
	/**
	 * Used to tell users information.
	 */
	private JPanel myTextPanel;
	
	/**
	 * Used for formatting.
	 */
	private JPanel myMiddle;
	
	/**
	 * Used to give button access to whole class.
	 */
	private JButton myUpDateInfo;
	
	/**
	 * Used to give button access to whole class.
	 */
	private JButton myCancelBid;
	
	/**
	 * Used to give button access to whole class.
	 */
	private JButton myPlaceBid;
	
	/**
	 * Used to build the JPanel.
	 * 
	 * @param theFrame the frame everything is loaded into
	 */
	public BidderPanel(final JFrame theFrame) {
		setLayout(new BorderLayout());
		myMiddle = new JPanel();
		myButtons = new JPanel();
		upDateInfoButton();
		cancelBidButton();
		placeBidButton();
		makeTextPanel();
		add(myMiddle, BorderLayout.CENTER);
		add(myButtons, BorderLayout.PAGE_END);
	}
	
	/**
	 * Used to make a button.
	 */
	private void upDateInfoButton() {
		myUpDateInfo = new JButton("Update Information");
		myUpDateInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				//System.exit(1);
			}
		});
		myButtons.add(myUpDateInfo);
	}
	
	/**
	 * Used to make a button.
	 */
	private void cancelBidButton() {
		myCancelBid = new JButton("Cancel Bid");
		myCancelBid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				//System.exit(1);
			}
		});
		myButtons.add(myCancelBid);
	}
	
	/**
	 * Used to make a button.
	 */
	private void placeBidButton() {
		myPlaceBid = new JButton("Place Bid");
		myPlaceBid.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				//System.exit(1);
			}
		});
		myButtons.add(myPlaceBid);
	}
	
	/**
	 * Used to name current bidder.
	 */
	private void makeTextPanel() {
		myTextPanel = new JPanel();
		JLabel Jlabel = new JLabel("Login: ");
		myTextPanel.add(Jlabel);
		add(myTextPanel, BorderLayout.PAGE_START);
	}
}
