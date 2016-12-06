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
import javax.swing.JPanel;

import model.User;

/**
 * 
 * @author imnic
 *
 */
public class StaffPanel extends JPanel implements Observer {
	
	private JPanel myCenterPanel;
	
	private JPanel myButtonPanel;
	
	/**
	 * Used to give button access to whole class.
	 */
	private JButton myLogout;
	
	/**
	 * Used to tell users information.
	 */
	private JPanel myTextPanel;
	
	/**
	 * Used to give access of login user to other classes.
	 */
	private User myUser;
		
	public StaffPanel(final JFrame theFrame) {
		setLayout(new BorderLayout());
		setupCenterPanel();
		setupSouthPanel();
		
	}
	
	private void setupCenterPanel() {
		myCenterPanel = new JPanel();
		add(myCenterPanel, BorderLayout.CENTER);
	}
	
	private void setupSouthPanel() {
		myButtonPanel = new JPanel();
		makeButtonLogout();
		add(myButtonPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Used to make logout button.
	 */
	private void makeButtonLogout() {
		myLogout = new JButton("Logout");
		myLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				firePropertyChange("LOGIN", "Bidder", "Login");
			}
		});
		myButtonPanel.add(myLogout);
	}
	
	/**
	 * Used to name current bidder.
	 */
	private void makeTextPanel() {
		myTextPanel = new JPanel();
		String name = myUser.getUserName();
		JLabel Jlabel = new JLabel("Login as: "+ name);
		myTextPanel.add(Jlabel);
		add(myTextPanel, BorderLayout.PAGE_START);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
    
	/**
	 * Used to set who is currently login.
	 * @param theUser the user
	 */
	public void setUser(User theUser) {
		myUser = theUser;
		makeTextPanel();
	}
}
