import java.util.Scanner;

public class CashPayment extends Payment {

	private static Scanner scnr = new Scanner(System.in);	
	  public double change=0;
	public CashPayment(double amount) {
		super(amount);
	}
	

	public CashPayment() {
		
	}


	@Override
	public String Payment(double amount) {
		System.out.println("How much cash are you giving us?");
		double cashAmount = scnr.nextDouble();
		 change = cashAmount - amount;
		String outPut=String.format("Thank you.  Your change is $ %.2f\n",change) ;
		return outPut;
	}

	@Override
	public String toString() {
		return "CashPayment [toString()=" + super.toString() + "]";
	}
	
	

}
