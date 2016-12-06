/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */

package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Auction;
import model.AuctionRequest;
import model.Calendar;
import model.Date;
import model.Item;
import model.NonProfit;
import model.Time;

/**
 * Used to build the non-profit JPanel.
 * 
 * @author Dmitriy Onishchenko,
 * Colin Casey (set up Skeleton)
 * 
 */
public class NonProfitPanel extends JPanel implements Observer, PropertyChangeListener{
	
	/**
	 * Used to save data.
	 */
	private static final long serialVersionUID = 1L;
	
	
	
    /**
     * Used to set min size of window.
     */
    private static final Dimension MY_SIZE = new Dimension(650, 600);

	/**
	 * Used to hold all Buttons.
	 */
	private JPanel myButtons;
	
	/**
	 * Used to give button access to all of the class.
	 */
	private JButton myCancelAuction;
	
	/**
	 * Used to give button access to all of the class.
	 */
	private JButton myAddAuction;
	
	/**
	 * Used to give button access to all of the class.
	 */
	private JButton myRemoveItem;
	
	/**
	 * Used to give button access to all of the class.
	 */
	private JButton myAddItem;
	
	/**
	 * Used to give button access to whole class.
	 */
	private JButton myLogout;
	
	/**
	 * The calendar for being currently used. 
	 */
	private Calendar myCalendar; 
	
	private JFrame myFrame;
	
	private LocalDate myLocalDate;	
	private Date myCurrDate;
	
	private String myCondition;
	private String mySize;
	
	
//	/**
//	 * Label used to display the the current non profit information.
//	 */
//	private JPanel myAuctionInfo;
	
	private NonProfitInfoPanel myAuctionInfo;

	/**
	 * The current non profit user.
	 */
	private NonProfit myCurrNonProfit;
	
	private ItemsPanel myInventory;
	
	private int myItemNumber;
	
	private Auction myAuction;


	
	/**
	 * Used to build the JPanel.
	 * 
	 * @param theFrame the frame everything is loaded into
	 */
	public NonProfitPanel(final JFrame theFrame, final Calendar theCalendar) {
		myFrame = theFrame;
		setLayout(new BorderLayout());
		myButtons = new JPanel();
		myCalendar = theCalendar;
		myItemNumber = -1;
		
		myLocalDate = LocalDate.now();		
		myCurrDate = new Date(myLocalDate.getDayOfMonth(), myLocalDate.getMonth().toString(), myLocalDate.getYear());		
		
		//Makes all buttons		
		makeAuctionInfoLabel();
		makeButtonCancelAuction();
		makeButtonAddAuction();
		makeButtonRemoveItem();
		makeButtonAddItem();
		makeButtonLogout();
		
		
		//Adds all buttons to button of JPanel
		add(myButtons, BorderLayout.PAGE_END);
		
	}

	/**
	 * This method is used to cancel an auction request.
	 */
	private void makeButtonCancelAuction() {
		myCancelAuction = new JButton("Cancel Auction Request");			
	
		myCancelAuction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				
				if (myAuction != null ){
					int selected = JOptionPane.showConfirmDialog(myFrame, "Are you sure you want "
							+ "to Cancel your Auction (All of your inventory will be removed)?");
					
					
					if (selected == JOptionPane.YES_OPTION) {						
						myCalendar.cancelAuction(myCurrNonProfit, myAuction.getDate(), myCurrDate);
						remove(myInventory);
						setAuctionInfo();
						myAuction = null;
					}					
				}
				
				
			}
		});		
		myButtons.add(myCancelAuction);
	}
	
	/**
	 * This method is used to add an auction request.
	 */
	private void makeButtonAddAuction() {
		myAddAuction = new JButton("Auction Request");			
		
		myAddAuction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				
				if (myCalendar.getAuctionForOrganization(myCurrNonProfit) == null)  {					
					makeAddAuctionDialog();					
				}			
			}
		});
		myButtons.add(myAddAuction);		
	}
	
	private void makeAddAuctionDialog() {
		
		//Make JPanels
		JPanel holder = new JPanel(new BorderLayout(10, 10));
		JPanel question = new JPanel(new GridLayout(0, 1, 2, 2));
		//Fill questions
		question.add(new JLabel("Date", SwingConstants.RIGHT));
		question.add(new JLabel("Time", SwingConstants.RIGHT));
		//Fill Holder
		holder.add(question, BorderLayout.WEST);
		//Make panel/textField
		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		JTextField date = new JTextField("DD/MM/YYYY");
		//Fill fields
		controls.add(date);
		JTextField time = new JTextField("HH AM/PM");
		controls.add(time);
		holder.add(controls, BorderLayout.CENTER);
		
		
		Object[] options = { "OK", "CANCEL" };
		int selected = JOptionPane.showOptionDialog(myFrame, holder, "Enter a date and time for Request",
		JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
		null, options, options[0]);

		// get information
		final String theDate = date.getText();
		final String theTime = new String(time.getText());		
	
		if (selected == JOptionPane.OK_OPTION) {
			parseRequestInfo(theDate, theTime);			
		}			
	}
	
	
	private void parseRequestInfo(String theDate, String theTime) {
		
		String orgName, month, period, requestTime;
		int day, year, hour;		
		Integer numToConvert; 
			

		String[] dayy = theDate.split("/");		
		month = Date.convertMonthToEquivalentInt(dayy[1]);
		
		// convert the day
		numToConvert = new Integer(dayy[0]);		
		day = numToConvert.intValue();
		
		// convert the year 
		numToConvert = new Integer(dayy[2]);		
		year = numToConvert.intValue();		


		dayy = theTime.split(" ");		
		// convert the Hour
		numToConvert = new Integer(dayy[0]);		
		hour = numToConvert.intValue();	
		
		period = dayy[1];
		
		Date date = new Date(day, month, year);
		Time time = new Time(hour, 0, period);	
		
		submitRequest(date, time);
	}
	
	private void submitRequest(Date theDate, Time theTime) {
		
		boolean granted = myCalendar.addAuction(new AuctionRequest(theDate, theTime, myCurrNonProfit.getOrgName()));
					
		if (granted) {			
			setAuctionInfo();			
		}
		
	}
	
	/**
	 * This method is used to remove an inventory item.
	 */
	private void makeButtonRemoveItem() {
		myRemoveItem = new JButton("Remove Item");
		myRemoveItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				
				if (myItemNumber != -1) {
						
					int selected = JOptionPane.showConfirmDialog(
							myFrame, 
							"Are you sure you want to delete the selected "
							+ "item (item# " + myItemNumber + ")?",
							"Remove Item",
							JOptionPane.YES_NO_OPTION);
					
					if (selected == JOptionPane.YES_OPTION) {						
						boolean r = myAuction.removeItem(myCurrNonProfit, myItemNumber, myAuction.getDate(), myCurrDate);
						myItemNumber = -1;								
												
						remove(myInventory);
						setAuctionInfo();					
					}
				}				
			}
		});
		
		myButtons.add(myRemoveItem);
	}
	
	/**
	 * This method is used to add an inventory item.
	 */
	private void makeButtonAddItem() {
		myAddItem = new JButton("Add Item");
		myAddItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				
				if (myCalendar.getAuctionForOrganization(myCurrNonProfit) != null)  {					
					makeAddItemDialog();					
				}							
				
			}
		});
		myButtons.add(myAddItem);
	}
	
	private void makeAddItemDialog() {
		
		//Make JPanels
		JPanel holder = new JPanel(new BorderLayout(10, 10));
		JPanel question = new JPanel(new GridLayout(0, 1, 2, 2));
		//Fill questions
		question.add(new JLabel("Item Name", SwingConstants.RIGHT));
		question.add(new JLabel("Condition", SwingConstants.RIGHT));
		question.add(new JLabel("Size", SwingConstants.RIGHT));
		question.add(new JLabel("Minimum Bid", SwingConstants.RIGHT));
		question.add(new JLabel("Donor", SwingConstants.RIGHT));
		question.add(new JLabel("Description", SwingConstants.RIGHT));
		question.add(new JLabel("Additional Comments", SwingConstants.RIGHT));
		//Fill Holder
		holder.add(question, BorderLayout.WEST);
		//Make panel/textField
		JPanel controls = new JPanel(new GridLayout(0, 1, 2, 2));
		JTextField name = new JTextField();
		
		
		String[] conditions = { "Acceptable", "Good", "Very Good", "Like New", "New" };		
		myCondition = conditions[2];		

		//Create the combo box, select item at index 4.
		//Indices start at 0, so 4 specifies the pig.
		JComboBox condList = new JComboBox(conditions);
		condList.setSelectedIndex(2);
		condList.addActionListener(new ActionListener() {
									
			@Override
			public void actionPerformed(ActionEvent e) {
				  JComboBox cb = (JComboBox)e.getSource();
			      myCondition = (String)cb.getSelectedItem();			       		
			}
		});
		
		String[] sizes = { "Small", "Medium", "Large"};
		mySize = sizes[0];

		//Create the combo box, select item at index 4.
		//Indices start at 0, so 4 specifies the pig.
		JComboBox<String> sizeList = new JComboBox<String>(sizes);
		sizeList.setSelectedIndex(0);
		sizeList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				  JComboBox<String> cb = (JComboBox)e.getSource();
			      mySize = (String)cb.getSelectedItem();			       		
			}
		});
		

		JTextField minBid = new JTextField();
		JTextField donor = new JTextField();
		JTextField desc = new JTextField(10);
		JTextField comments = new JTextField();		
		
		//Fill fields
		controls.add(name);		
		controls.add(condList);
		controls.add(sizeList);	
		controls.add(minBid);
		controls.add(donor);
		controls.add(desc);
		controls.add(comments);
		
		
		holder.add(controls, BorderLayout.CENTER);
		
		
		Object[] options = { "OK", "CANCEL" };
		int selected = JOptionPane.showOptionDialog(myFrame, holder, "Enter Item Information",
		JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
		null, options, options[0]);
		
		
		if (selected == JOptionPane.OK_OPTION) {
			
			confirmAddItem(name.getText(), myCondition, mySize, 
					minBid.getText(), donor.getText(), desc.getText(), comments.getText());
			
		}
	}
	
	
	public void confirmAddItem (String theName, String theCondition, String theSize,
								String minBid, String theDonor, String theDescription,
								String theComments){
		
		
		StringBuilder str = new StringBuilder("Are you sure you Want to add this Item?\n");
		
		str.append("Name: " + theName);
		str.append("\nThe Condition: ");
		str.append(theCondition);
		str.append("\nTheSize: ");
		str.append(theSize);
		str.append("\nMin Bid: ");
		str.append("$" + minBid);
		str.append("\nDonor: ");
		str.append(theDonor);
		str.append("\nDescription: ");
		str.append(theDescription);
		str.append("\nAdditional Comments: ");
		str.append(theComments);		
		
		JLabel confirm = new JLabel(str.toString());
		
		int selected = JOptionPane.showConfirmDialog(myFrame, str.toString(), "Add Item?", 
				JOptionPane.YES_NO_OPTION);	
		
		Integer bid = new Integer(minBid);
		
		if (selected == JOptionPane.YES_OPTION) {
			
			Item item = new Item(theName, theCondition, theSize, bid.intValue(), 
					theDonor, theDescription, theComments);					
			myAuction.addItem(myCurrNonProfit, item);
			remove(myInventory);
			setAuctionInfo();
			
		}
		
		
		
		
	}
								
								
	
	/**
	 * Used to make logout button.
	 */
	private void makeButtonLogout() {
		myLogout = new JButton("Logout");
		myLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				
				if (myInventory != null) {
					remove(myInventory);				
				}
				
				firePropertyChange("LOGIN", "Bidder", "Login");				
			}
		});
		myButtons.add(myLogout);
	}
	
	private void makeAuctionInfoLabel() {
		myAuctionInfo = new NonProfitInfoPanel(myCalendar);
		this.add(myAuctionInfo, BorderLayout.NORTH);
		
		
	}

	
	private void setAuctionInfo() {		
				
		if (myCalendar.getAuctionForOrganization(myCurrNonProfit) != null) {			

			myAuction = myCalendar.getAuctionForOrganization(myCurrNonProfit);
			myAuctionInfo.setNonProfit(myCurrNonProfit);
			myAuctionInfo.setTextHasAuction();
			myAddAuction.setEnabled(false);
			myAddItem.setEnabled(true);
			updateInventory();		

		} else {	
			myAuctionInfo.setTextNoAuction();	
			myAddAuction.setEnabled(true);
			myAddItem.setEnabled(false);
		}		
	}
	
	
	/**
	 * Updates current inventory
	 */
	private void updateInventory() {
		
		myInventory = new ItemsPanel(myCurrNonProfit, myCalendar);
		myInventory.addPropertyChangeListener(this);
		this.add(myInventory, BorderLayout.CENTER);
		myInventory.displayItems();			
	}
	
	
	
	public void setUpNonProfitInfo() {
		setAuctionInfo();
	}
	
	public void setUser(NonProfit theNonProfit) {
		
		myCurrNonProfit = theNonProfit;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent theEvent) {

		if(theEvent.getPropertyName().equals(ItemsPanel.NEW_ITEM_SELECTED)) {
			myItemNumber = (int)theEvent.getNewValue();
		}
	}
}
