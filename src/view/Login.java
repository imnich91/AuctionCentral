/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */

package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


/**
 * Used to build the login JPanel.
 * 
 * @author Colin Casey
 */
public class Login extends JPanel {

	/**
	 * Used to keep track of data
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Used to tell users that they need to login.
	 */
	private JPanel myTextPanel;

	/**
	 * Frame that all panels are put in.
	 */
	private final JFrame myFrame;

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
	 * @param theFrame
	 */
	public Login(JFrame theFrame) {
		myFrame = theFrame;
		makeButtonLogin();
		makeButtonExit();
		makeTextPanel();
		setBackground(Color.BLUE);
		setLayout(new BorderLayout());
		add(myTextPanel, BorderLayout.NORTH);
		add(myExit, BorderLayout.WEST);
		add(myLogin, BorderLayout.EAST);
	}

	/**
	 * Used to make a login message.
	 */
	private void makeTextPanel() {
		myTextPanel = new JPanel();
		JLabel Jlabel = new JLabel("Welcome to Auction Central");
		myTextPanel.add(Jlabel);
	}

	/**
	 * Used to make exit button.
	 */
	private void makeButtonExit() {
		myExit = new JButton("Exit");
		myExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				//firePropertyChange("LOGGEDIN", "STAFF PANEL", "LOGIN PANEL");
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
				//Make JPanels
				JPanel holder = new JPanel(new BorderLayout(10, 10));
				JPanel question = new JPanel(new GridLayout(0, 1, 2, 2));
				//Fill questions
				question.add(new JLabel("Username", SwingConstants.RIGHT));
				question.add(new JLabel("Password", SwingConstants.RIGHT));
				//Fill Holder
				holder.add(question, BorderLayout.WEST);
				//Make panel/textField
				JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
				JTextField username = new JTextField();
				//Fill fields
				controls.add(username);
				JPasswordField password = new JPasswordField();
				controls.add(password);
				holder.add(controls, BorderLayout.CENTER);
				//The pop up
				JOptionPane.showMessageDialog(myFrame, holder, "Login To Auction Central", 
						JOptionPane.QUESTION_MESSAGE);
				
				//Could not get cancel to work.
				//JOptionPane.showMessageDialog(myFrame, holder, "Login", JOptionPane.OK_CANCEL_OPTION);
				final String currentUser = username.getText();
				final String currentPassword = new String(password.getPassword());
				
				boolean flag = false;
				if(flag) {
					popupPass();
				} else {
					popupFail(true, true);
				}
				//System.out.println(currentUser);
				//System.out.println(currentPassword);
			}
		});
	}
	
	/**
	 * Used to tell user if the failed or passed
	 */
	private void popupPass() {
		JOptionPane.showMessageDialog(myFrame, "You are now login");
	}
	
	/**
	 * Used to tell user why they failed.
	 * @param thePassword if password is wrong
	 * @param theUsername if username is wrong
	 */
	private void popupFail(boolean thePassword, boolean theUsername) {
		String outPut = "Your";
		if (thePassword) {
			outPut += " password";
		} if (theUsername && thePassword) {
			outPut += " and";
		} if (theUsername) {
			outPut += " username";
		}
		outPut += " failed.";
		JOptionPane.showMessageDialog(myFrame, outPut, "Login Error",
			    JOptionPane.ERROR_MESSAGE);
	}
}
