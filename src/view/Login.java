/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */

package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * Used to build the login JPanel.
 * 
 * @author Colin Casey
 *
 */
public class Login extends JPanel {
	
	/**
	 * Used to give button access to whole class.
	 */
	private JButton myLogin;
	
	/**
	 * Used to give button access to whole class.
	 */
	private JButton myExit;
	
	/**
	 * Used to make panel.
	 */
	public void Login() {
		makeButtonLogin();
		makeButtonExit();
		
		add(myExit);
		add(myLogin);
	}
	
	/**
	 * Used to make exit button.
	 */
	private void makeButtonExit() {
		myExit = new JButton("Exit");
	    myExit.addActionListener(new ActionListener() {
	    	@Override
	        public void actionPerformed(final ActionEvent theEvent) {
	    		System.exit(1);
	        }
	    });
		
	}

	/**
	 * Used to make login button.
	 */
	private void makeButtonLogin() {
		   myLogin = new JButton("Login");
	       myLogin.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(final ActionEvent theEvent) {
	            }
	        });
	}
}
