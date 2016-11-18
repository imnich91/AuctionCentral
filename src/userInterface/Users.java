package userInterface;

import java.util.HashMap;
import java.util.Scanner;

import model.Bidder;
import model.NonProfit;
import model.Staff;

public class Users {
	
	
	private static HashMap<String, String> myUsers;
	
	private static Bidder bidder1;
	private static Staff staff1;
	private static NonProfit nonProfit1;

	
	
	public static void main(String[] theargs) {
		
		myUsers =  new HashMap<String, String>();
		
		nonProfit1 = new NonProfit("Bill Gates", "billy", "1234",
				"Bill and Melinda Gates", "1234 Mercer Island", "111-111-1111");
		
		
		staff1 = new Staff("Bill Clinton", "billyC", "2345");
		
		bidder1 = new Bidder("Dave Mathews", "dave", "3456", 
				"d@gmail.com", "1111 11th st San Diego", "111-111-1111");

		
		
		System.out.println(bidder1.getUserName() + bidder1.getPassword());
		System.out.println(staff1.getUserName() + staff1.getPassword());
		System.out.println(nonProfit1.getUserName() + nonProfit1.getPassword());
		
		myUsers.put(bidder1.getUserName(), bidder1.getPassword());
		myUsers.put(staff1.getUserName(), staff1.getPassword());
		myUsers.put(nonProfit1.getUserName(), nonProfit1.getPassword());
		
		
		String username;
		String password;
		
		
		Scanner in = new Scanner(System.in) ;
		
		
		
		
		
		
		boolean loggingIn = true;
		
		while (loggingIn) {
			
			System.out.print("Please enter your username: ");
			username = in.nextLine();
			System.out.print("Please enter your password: ");
			password = in.nextLine();
			
			System.out.println(myUsers.get(username));
			
			if (password.equals(myUsers.get(username))) {
				
				
				System.out.println("You have successfully logged in!");		
				loggingIn = false;
				
			} 		
			
			
		}
		
		
		
		
		
		
		
	}

}
