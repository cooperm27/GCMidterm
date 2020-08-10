
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class CreditCardPayment extends Payment{
	
	Scanner scnr=new Scanner(System.in);
	
	private long cardNumber;
	private  Date expiration;
	private int cVV;
	
	public CreditCardPayment(double amount) {
			   super(amount);

	}
	

	

	public CreditCardPayment(double amount, long cardNumber, Date expiration, int cVV) {
		super(amount);
		this.cardNumber = cardNumber;
		this.expiration = expiration;
		this.cVV = cVV;
	}





	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
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
		boolean validNum=true;
		boolean validCVV=true;
		boolean validDate=true;
		do {
	    cardNumber=Validator.getLong(scnr, "Please enter the Credit Card number: ");
	    validNum=Validator.getCardSize(cardNumber);
		}while(!validNum);
		do {
		System.out.println("Please enter the Expiration date(mm/yy): ");

	    String dateString = scnr.next();
	    DateFormat formatter = new SimpleDateFormat("MM/yy");
	      
	    try {
			expiration = formatter.parse(dateString);
			validDate=true;
		} catch (ParseException e) {

		   System.out.println("Please enter a valid expiration date!");  
		   validDate=false;
		   continue;
		}
	      validDate=Validator.dateCheck(dateString);
	      if(!validDate) {
	    	  System.out.println("Please enter a valid expiration date!");
	    	 continue;
	      }
		}while(!validDate);
		scnr.nextLine(); 
	    do {
	    cVV=Validator.getInt(scnr,"Please enter CVV number:");
	 	 	validCVV=Validator.getcVVSize(cVV);

	    }while(!validCVV);
	    
	    String outPut=String.format("Approved\nThank you.Your card has been charged $ %.2f\n",amount) ;
	    return outPut;
	}
	
}
