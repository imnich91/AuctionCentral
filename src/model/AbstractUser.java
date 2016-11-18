/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */


package model;

import java.io.Serializable;

/**
 * Abstract User Class contains all fields that all users share.
 * @author Dmitriy Onishchenko
 *
 */
public class AbstractUser implements User, Serializable {

	/**
	 * The serializable id.
	 */
	private static final long serialVersionUID = -1523357576780039985L;
	/**
	 * Users first and last name.
	 */
	private String myName;
	/**
	 * Users userName for login.
	 */
	private String myUserName;
	/**
	 * Users password for login.
	 */
	private String myPassword;
	
	/**
	 * Initialize class
	 * @param name the name of the user
	 * @param userName the userName of the user
	 * @param password the users password
	 */
	protected AbstractUser (String name, String userName, String password) {
		myName = name;
		myUserName = userName;
		myPassword = password;
		
	}	

	@Override
	public void setName(String name) {
		myName = name;
	}	

	@Override
	public void setUserName(String userName) {
		myUserName = userName;
	}	
	

	@Override
	public void setPassword(String password) {
		myPassword = password;
	}

	@Override
	public String getUserName() {		
		return myUserName;
	}

	@Override
	public String getPassword() {
		return myPassword;
	}
	
	@Override
	public String getName() {
		return myName;
	}

}
