import java.util.Scanner;

public class CashPayment extends Payment {

	private static Scanner scnr = new Scanner(System.in);	
	
	public CashPayment(double amount) {
		super(amount);
	}
	

	@Override
	public String Payment(double amount) {
		System.out.println("How much cash are you giving us?");
		double cashAmount = scnr.nextDouble();
		double change = cashAmount - amount;
		String outPut = "Thank you.  Your change is $" + change + "($" + cashAmount + "- $" +  amount + ")";
		return outPut;
	}

	@Override
	public String toString() {
		return "CashPayment [toString()=" + super.toString() + "]";
	}
	
	

}
