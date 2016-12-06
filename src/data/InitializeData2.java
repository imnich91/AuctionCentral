/*
 * TCSS 360 Software Development
 * Auction Central Project
 * Group 6 
 */

package data;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import model.AuctionRequest;
import model.Calendar;
import model.Date;
import model.NonProfit;
import model.Time;


/**
 * Serializable Class
 * @author Dmitriy Onishchenko
 *
 */
public class InitializeData2 {	
	
	public static void main(String [] args) {
		
		
		final Calendar myCalendar = new Calendar();		
		
		final NonProfit myNonProfitBill = new NonProfit("Bill Gates", "bill", "1234",
				"Bill", "1234 Mercer Island", "111-111-1111");
		
		final NonProfit myNonProfitAnn = new NonProfit("Ann Hopkins", "ann", "1111",
				"Hopkins", "2222 st", "222-222-2222");
		
		final NonProfit myNonProfit3 = new NonProfit("np3", "ann", "1111",
				"np3", "2222 st", "222-222-2222");
		
		final NonProfit myNonProfit4 = new NonProfit("np4", "ann", "1111",
				"np4", "2222 st", "222-222-2222");
		
		final NonProfit myNonProfit5 = new NonProfit("np5", "ann", "1111",
				"np5", "2222 st", "222-222-2222");
		
		final NonProfit myNonProfit6 = new NonProfit("np6", "ann", "1111",
				"np6", "2222 st", "222-222-2222");
		
		final NonProfit myNonProfit7 = new NonProfit("np7", "ann", "1111",
				"np7", "2222 st", "222-222-2222");
		
		final NonProfit myNonProfit8 = new NonProfit("np8", "ann", "1111",
				"np8", "2222 st", "222-222-2222");
		
		final NonProfit myNonProfit9 = new NonProfit("np9", "ann", "1111",
				"np9", "2222 st", "222-222-2222");
		
		final NonProfit myNonProfit10 = new NonProfit("np10", "ann", "1111",
				"np10", "2222 st", "222-222-2222");
		
		final NonProfit myNonProfit11 = new NonProfit("np11", "ann", "1111",
				"np11", "2222 st", "222-222-2222");
		
		final NonProfit myNonProfit12 = new NonProfit("np12", "ann", "1111",
				"np12", "2222 st", "222-222-2222");
		
		final NonProfit myNonProfit13 = new NonProfit("np13", "ann", "1111",
				"np13", "2222 st", "222-222-2222");
		
		final NonProfit myNonProfit14 = new NonProfit("np14", "ann", "1111",
				"np14", "2222 st", "222-222-2222");
		
		final NonProfit myNonProfit15 = new NonProfit("np15", "ann", "1111",
				"np15", "2222 st", "222-222-2222");
		
		final NonProfit myNonProfit16 = new NonProfit("np16", "ann", "1111",
				"np16", "2222 st", "222-222-2222");
		
		final NonProfit myNonProfit17 = new NonProfit("np17", "ann", "1111",
				"np17", "2222 st", "222-222-2222");
		
		final NonProfit myNonProfit18 = new NonProfit("np18", "ann", "1111",
				"np18", "2222 st", "222-222-2222");
		
		final NonProfit myNonProfit19 = new NonProfit("np19", "ann", "1111",
				"np19", "2222 st", "222-222-2222");
		
		final NonProfit myNonProfit20 = new NonProfit("np20", "ann", "1111",
				"np20", "2222 st", "222-222-2222");
		
		final NonProfit myNonProfit21 = new NonProfit("np21", "ann", "1111",
				"np21", "2222 st", "222-222-2222");
		
		final NonProfit myNonProfit22 = new NonProfit("np22", "ann", "1111",
				"np22", "2222 st", "222-222-2222");
		
		final NonProfit myNonProfit23 = new NonProfit("np23", "ann", "1111",
				"np23", "2222 st", "222-222-2222");
		
		final NonProfit myNonProfit24 = new NonProfit("np24", "ann", "1111",
				"np24", "2222 st", "222-222-2222");
		
		final NonProfit myNonProfit25 = new NonProfit("np25", "ann", "1111",
				"np25", "2222 st", "222-222-2222");
		
		final NonProfit myNonProfit26 = new NonProfit("np26", "ann", "1111",
				"np26", "2222 st", "222-222-2222");
		
		
		
		// populate calendar with Auctions and items.
				
		
		myCalendar.addAuction(new AuctionRequest(new Date(9, "December", 2016), 
				new Time(6, 00, "PM"), myNonProfit3.getOrgName()));	
		
		myCalendar.addAuction(new AuctionRequest(new Date(10, "December", 2016), 
				new Time(6, 00, "PM"), myNonProfit4.getOrgName()));	
		
		myCalendar.addAuction(new AuctionRequest(new Date(11, "December", 2016), 
				new Time(6, 00, "PM"), myNonProfit5.getOrgName()));	
		
		myCalendar.addAuction(new AuctionRequest(new Date(12, "December", 2016), 
				new Time(6, 00, "PM"), myNonProfit6.getOrgName()));	
		
		myCalendar.addAuction(new AuctionRequest(new Date(14, "December", 2016), 
				new Time(6, 00, "PM"), myNonProfit7.getOrgName()));	
		
		myCalendar.addAuction(new AuctionRequest(new Date(16, "December", 2016), 
				new Time(6, 00, "PM"), myNonProfit8.getOrgName()));	
		
		myCalendar.addAuction(new AuctionRequest(new Date(18, "December", 2016), 
				new Time(6, 00, "PM"), myNonProfit9.getOrgName()));	
		
		myCalendar.addAuction(new AuctionRequest(new Date(19, "December", 2016), 
				new Time(2, 00, "PM"), myNonProfit10.getOrgName()));
		
		myCalendar.addAuction(new AuctionRequest(new Date(19, "December", 2016), 
				new Time(6, 00, "PM"), myNonProfit11.getOrgName()));	
		
		myCalendar.addAuction(new AuctionRequest(new Date(9, "December", 2016), 
				new Time(2, 00, "PM"), myNonProfit12.getOrgName()));	
		
		myCalendar.addAuction(new AuctionRequest(new Date(10, "December", 2016), 
				new Time(2, 00, "PM"), myNonProfit13.getOrgName()));	
		
		myCalendar.addAuction(new AuctionRequest(new Date(11, "December", 2016), 
				new Time(2, 00, "PM"), myNonProfit14.getOrgName()));	
		
		myCalendar.addAuction(new AuctionRequest(new Date(12, "December", 2016), 
				new Time(2, 00, "PM"), myNonProfit15.getOrgName()));	
		
		myCalendar.addAuction(new AuctionRequest(new Date(12, "December", 2016), 
				new Time(2, 00, "PM"), myNonProfit16.getOrgName()));	
		
		myCalendar.addAuction(new AuctionRequest(new Date(13, "December", 2016), 
				new Time(2, 00, "PM"), myNonProfit17.getOrgName()));	
		
		myCalendar.addAuction(new AuctionRequest(new Date(13, "December", 2016), 
				new Time(6, 00, "PM"), myNonProfit18.getOrgName()));	
		
		myCalendar.addAuction(new AuctionRequest(new Date(14, "December", 2016), 
				new Time(2, 00, "PM"), myNonProfit19.getOrgName()));	
		
		myCalendar.addAuction(new AuctionRequest(new Date(15, "December", 2016), 
				new Time(2, 00, "PM"), myNonProfit20.getOrgName()));	
		
		myCalendar.addAuction(new AuctionRequest(new Date(18, "December", 2016), 
				new Time(2, 00, "PM"), myNonProfit21.getOrgName()));
		
		myCalendar.addAuction(new AuctionRequest(new Date(15, "December", 2016), 
				new Time(6, 00, "PM"), myNonProfit22.getOrgName()));	
		
		myCalendar.addAuction(new AuctionRequest(new Date(16, "December", 2016), 
				new Time(2, 00, "PM"), myNonProfit23.getOrgName()));	
		
		myCalendar.addAuction(new AuctionRequest(new Date(17, "December", 2016), 
				new Time(2, 00, "PM"), myNonProfit24.getOrgName()));	
		
		myCalendar.addAuction(new AuctionRequest(new Date(17, "December", 2016), 
				new Time(6, 00, "PM"), myNonProfit25.getOrgName()));	
		
		myCalendar.addAuction(new AuctionRequest(new Date(22, "December", 2016), 
				new Time(6, 00, "PM"), myNonProfit26.getOrgName()));	
		
		

	      
	      try {
	          
	         
	         FileOutputStream CalendarFile =
	         new FileOutputStream("./usersData/calendar24.ser");
	         ObjectOutputStream out3 = new ObjectOutputStream(CalendarFile);
	         out3.writeObject(myCalendar);
	         out3.close();
	         CalendarFile.close();	         
	
	         System.out.println("Serialized data is saved in /calendar24.ser");
	      }
	      
	      catch(IOException i) {
	          i.printStackTrace();
	      }
	   }

}
