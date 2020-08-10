
import java.util.Scanner;

public class Validator {
	public static String getString(Scanner scnr, String prompt) {
		System.out.print(prompt);
		return scnr.nextLine();
	}
	public static String getPaymentType (Scanner scnr, String prompt){
		String response;
		boolean isValid;
		System.out.println(prompt);
		do {
			response = scnr.nextLine();
			isValid = response.equalsIgnoreCase("Cash") || response.equalsIgnoreCase("Check") || response.equalsIgnoreCase("Card");
			if (!isValid) {
				System.out.println("Please try again and only choose Cash, Card or Check.");
			}
			}while (!isValid);
			return response;
	}

	public static int getInt(Scanner scnr, String prompt) {
		System.out.println(prompt);
		// loop while the input would be bad.
		while (!scnr.hasNextInt()) {
			scnr.nextLine(); // <-- clear out the bad input that they already typed
							
			System.out.println("That is not a valid number. Please try again.");
			System.out.print(prompt);
			
		}
		int input = scnr.nextInt();
		scnr.nextLine(); // <-- clear entire line to ready for next input
		return input;
	}
	

	public static int getPositiveInt(Scanner scnr, String prompt) {
		int input;
		do {
			input = getInt(scnr, prompt);
			if (input <= 0) {
				System.out.println("You must enter a positive number.");
			}
		} while (input <= 0);
		return input;
	}

	public static double getDouble(Scanner scnr, String prompt) {
		System.out.print(prompt);
		// loop while the input would be bad.
		while (!scnr.hasNextDouble()) {
			scnr.nextLine(); // <-- clear out the bad input that they already
								// typed
			System.out.println("That is not a valid number. Please try again.");
			System.out.print(prompt);
		}
		double input = scnr.nextDouble();
		scnr.nextLine(); // <-- clear entire line to ready for next input
		return input;
	}

	public static long getLong(Scanner scnr, String prompt) {
		System.out.print(prompt);
		// loop while the input would be bad.
		while (!scnr.hasNextLong()) {
			scnr.nextLine(); // <-- clear out the bad input that they already
								// typed
			System.out.println("That is not a valid credit card number. Please try again.");
			System.out.print(prompt);
		}
		long input = scnr.nextLong();
		scnr.nextLine(); // <-- clear entire line to ready for next input
		return input;
	}
	
	public static boolean getCardSize(long num) {
		  String s=num+"";
		  int size=s.length();
		if(size!=16) {
			System.out.println("That is not a valid credit card number. Please enter a 16 digit number");
			return false;
		}
		return true;
	}
	
	public static boolean getcVVSize(int cVV) {
		  String s=cVV+"";
		  int size=s.length();
		if(size!=3) {
			System.out.println("That is not a valid CVV number. Please enter a 3 digit number");
			return false;
		}
		return true;
	}
	
	public static boolean getCheckSize(int checkNumber) {
		  String s=checkNumber+"";
		  int size=s.length();
		if(size!=3) {
			System.out.println("That is not a valid Check number. Please enter a 3 digit number");
			return false;
		}
		return true;
	}
	
	// valid: YES, yes, y, Y / No, no, N, n
	public static boolean getYesNo(Scanner scnr, String prompt) {
		String input;
		boolean isValid;
		do {
			System.out.println(prompt);
			input = scnr.nextLine();
			isValid = "yes".equalsIgnoreCase(input) ||
					"no".equalsIgnoreCase(input) || "y".equalsIgnoreCase(input)
					|| "n".equalsIgnoreCase(input);
			if (!isValid) {
				System.out.println("Invalid response. Enter yes or no.");
			}
		} while (!isValid);
		
		return input.toLowerCase().startsWith("y");
	}
	
	public static boolean dateCheck(String date) {
		int month=0,year=0;
	   if(date.contains("/")) {
		   String[] change= date.split("/");
		   month=Integer.parseInt(change[0]);
		    year=Integer.parseInt(change[1]);
		      
	   }
		   if(month>=1 && month<=12 && year>=20)
			   return true;
		   else
			   return false;
	   
	}
	public static String getNewCategory (Scanner scnr, String prompt){
		String response;
		boolean isValid;
		System.out.println(prompt);
		do {
			response = scnr.nextLine();
			isValid = response.equalsIgnoreCase("Appetizer") || response.equalsIgnoreCase("Entree") || response.equalsIgnoreCase("Dessert")|| response.equalsIgnoreCase("Drink");;
			if (!isValid) {
				System.out.println("Please try again and only choose Appetizer, Entree, Drink or Dessert.");
			}
			}while (!isValid);
			return response;
}
}