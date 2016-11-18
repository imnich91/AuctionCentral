/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */


package model;

import java.io.Serializable;

/**
 * The non profit contact person class representing the non-profit oraganization.
 * @author Dmitriy Onishchenko
 *
 */
public class NonProfit extends AbstractUser implements Serializable {
	
	/**
	 * The Serializable id.
	 */
	private static final long serialVersionUID = 8077614071748115685L;
	/**
	 * The non profit organization name.
	 */
	private String myOrgName;
	/**
	 * The non-profit contact person phone number.
	 */
	private String myOrgPhoneNumber;
	/**
	 * The organizations address.
	 */
	private String myOrgAddress;

	/**
	 * Initialize the fields.
	 * @param name the first and last name.
	 * @param userName the userName.
	 * @param password the password.
	 * @param orgName the non-profit organization name.
	 * @param orgAddress the non-profit address
	 * @param orgPhoneNumber the non-profit phone number
	 */
	public NonProfit(String name, String userName, String password, 
						String orgName, String orgAddress, String orgPhoneNumber) {
		super(name, userName, password);
		
		myOrgName = orgName;
		myOrgAddress = orgAddress;
		myOrgPhoneNumber = orgPhoneNumber;
		
	}
	
	/**
	 * Sets the organization name	
	 * @param orgName the organization name.
	 */
	public void setOrgName(String orgName) {		
		myOrgName = orgName;		
	}
	
	/**
	 * Sets the contact persons phone number
	 * @param orgPhoneNumber the contact persons phone number.
	 */
	public void setPhoneNumber(String orgPhoneNumber) {
		myOrgPhoneNumber = orgPhoneNumber;
	}
	
	/**
	 * Sets the organizations address
	 * @param address the organizations address.
	 */
	public void setOrgAddress(String address) {
		myOrgAddress = address;
	}
	
	/**
	 * Returns the organizations name.
	 * @return the organizations name.
	 */
	public String getOrgName() {
		return myOrgName;
	}
	
	/**
	 * Returns the contact persons phone number.
	 * @return the contact persons phone number.
	 */
	public String getOrgPhoneNumner() {
		return myOrgPhoneNumber;		
	}
	
	/**
	 * Returns the organizations address.
	 * @return the organizations address.
	 */
	public String getOrgAddress() {
		return myOrgAddress;
	}
	
	

	/**
	 * {@inheritDoc}.
	 * Returns the contact persons name and who they are logged in as 
	 * and which organization they are representing.
	 */
	@Override
	public String toString() {		
		return super.getName() + " logged in as Non-Profit representative for " 
									+ myOrgName;
	}
}
