import java.util.Scanner;

class CheckPayment extends Payment {
	private int checkNumber;

	private static Scanner scnr = new Scanner(System.in);

	public CheckPayment(double amount) {
		super(amount);

	}

	public CheckPayment(double amount, int checkNumber) {
		super(amount);
		this.checkNumber = checkNumber;
	}

	public int getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(int checkNumber) {
		this.checkNumber = checkNumber;
	}

	@Override
	public String toString() {
		return "CheckPayment [checkNumber=" + checkNumber + "]";
	}

	@Override
	public String Payment(double amount) {
		boolean check = true;
		do {
			checkNumber = Validator.getPositiveInt(scnr, "Enter the check number:");
			check = Validator.getCheckSize(checkNumber);
		} while (!check);
		String outPut = String.format("Approved\nThank you");
		return outPut;
	}

}
