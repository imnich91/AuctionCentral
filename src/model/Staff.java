/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */


package model;

import java.io.Serializable;

/**
 * The Auction Central Staff class. 
 * @author Dmitriy Onishchenko
 *
 */
public class Staff extends AbstractUser implements Serializable {

	/**
	 * The serialazable id.
	 */
	private static final long serialVersionUID = 8381395309392302462L;

	/**
	 * Initialize the fields.
	 * @param theName the first and last name of the staff
	 * @param theUserName the staffs userName.
	 * @param password the staffs password.
	 */
	public Staff(String theName, String theUserName, String thePassword) {
		super(theName, theUserName, thePassword);
		
	}

	/**
	 * {@inheritDoc}.
	 * Returns the staff name
	 */
	@Override
	public String toString() {		
		return super.getName() + " logged in as Auction Central Staff Person";
	}
}
