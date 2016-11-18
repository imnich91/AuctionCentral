/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */


package model;

/**
 * The user Interface which all users share.
 * @author Dmitriy Onishchenko
 *
 */
public interface User {	
	
	/**
	 * Returns the userName of the user.
	 * @return userName of user.
	 */
	String getUserName();	
	
	/**
	 * Returns the password of the user.
	 * @return password of user.
	 */
	String getPassword();
	
	/**
	 * Returns the name of the user.
	 * @return name of user
	 */
	String getName();
	
	
	/**
	 * Sets the name of the user.
	 * @param the users first and last name.
	 */
	void setName(String theName);
	
	/**
	 * Sets the userName of the user.
	 * @param the users userName.
	 */
	void setUserName(String theUserName);
	
	/**
	 * Sets the password of the user.
	 * @param the users password.
	 */
	void setPassword(String thePassword);
	

}
