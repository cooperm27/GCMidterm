
import java.util.Scanner;
public class CreditCardPayment extends Payment{
	
	Scanner scnr=new Scanner(System.in);
	
	private long cardNumber;
	private  String expiration;
	private int cVV;
	
	public CreditCardPayment(double amount) {
		super(amount);

	}
	

	

	public CreditCardPayment(double amount, long cardNumber, String expiration, int cVV) {
		super(amount);
		this.cardNumber = cardNumber;
		this.expiration = expiration;
		cVV = cVV;
	}




	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	public int getCVV() {
		return cVV;
	}

	public void setCVV(int cVV) {
		this.cVV = cVV;
	}
	
	

	@Override
	public String toString() {
		return "CreditCardPayment [cardNumber=" + cardNumber + ", expiration=" + expiration + ", CVV=" + cVV + "]";
	}




	@Override
	public String Payment(double amount) {
	    System.out.println("Please enter the Credit Card number: ");
	    cardNumber=scnr.nextLong();
	    System.out.println("Please enter the Expiration date: ");
	    expiration=scnr.nextLine();
	    System.out.println("Please enter CVV number:");
	    cVV=scnr.nextInt();
	    return "Payment done";
	}
}
