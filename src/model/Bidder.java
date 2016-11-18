/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */


package model;

import java.io.Serializable;

/**
 * A bidder class which has additional required information that pertain to a Bidder.
 * @author Dmitriy Onishchenko
 *
 */
public class Bidder extends AbstractUser implements Serializable {
	
	/**
	 * The serializable ID.
	 */
	private static final long serialVersionUID = -8011641823985450611L;
	/**
	 * Bidders email address.
	 */
	private String myEmail;
	/**
	 * The bidders home address.
	 */
	private String myAddress;
	/**
	 * The bidders phone number.
	 */
	private String myPhoneNumber;
	
	
	/**
	 * Initialize the bidders fields.
	 * @param theName the first and last name of bidder.
	 * @param theUserName the user name of bidder.
	 * @param thePassword the password of bidder.
	 * @param theEmail the email address of bidder.
	 * @param theAddress the address of bidder.
	 * @param thePhoneNumber the phoneNumber of bidder.
	 */
	public Bidder(String theName, String theUserName, String thePassword, 
					String theEmail, String theAddress, String thePhoneNumber) {
		super(theName, theUserName, thePassword);
		
		myEmail = theEmail;		
		myAddress = theAddress;
		myPhoneNumber = thePhoneNumber;		
	}
	
	
	/**
	 * Sets the bidders address.
	 * @param address the bidders address.
	 */
	public void setAddress(String address) {
		myAddress = address;
	}

	/**
	 * Sets the bidders email address.
	 * @param email the bidders email address
	 */
	public void setEmail(String email) {
		myEmail = email;
	}


	/**
	 * Sets the bidders phone number.
	 * @param phoneNumber bidders phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		myPhoneNumber = phoneNumber;
	}
	
	
	
	/**
	 * Returns the bidders email address.
	 * @return the bidders email address
	 */
	public String getEmail() {
		return myEmail;
	}


	/**
	 * Returns the bidders home address.
	 * @return bidders home address.
	 */
	public String getAddress() {
		return myAddress;
	}
	
	/**
	 * Returns the users phone number.
	 * @return bidders phone number
	 */
	public String getPhoneNumber() {
		return myPhoneNumber;
	}
	
	
	/**
	 * {@inheritDoc}.
	 * Returns the bidders name and who their logged in as. 
	 */
	@Override
	public String toString() {
		
		return super.getName() + " logged in as " + getClass().getSimpleName();
	}

}
