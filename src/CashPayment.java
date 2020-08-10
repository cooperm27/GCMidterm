import java.util.Scanner;

public class CashPayment extends Payment {

	private static Scanner scnr = new Scanner(System.in);
	public double change = 0;

	public CashPayment(double amount) {
		super(amount);
	}

	public CashPayment() {

	}

	@Override
	public String Payment(double amount) {
		boolean enoughCash = false;
		double cashAmount = 0;
		do {
			cashAmount = Validator.getDouble(scnr, "How much cash are you giving us?");
			enoughCash = Validator.getEnoughCash(cashAmount, amount);
		} while (enoughCash == false);
		change = cashAmount - amount;
		System.out.println();
		String outPut = "Thank You. ";

		return outPut;
	}

	@Override
	public String toString() {
		return "CashPayment [toString()=" + super.toString() + "]";
	}

}
