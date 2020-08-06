
public class Order {

	private String name;
	private double price;
	private int quantity;
	
	
	
	public Order(String name, int quantity,double price) {
		super();
		this.name = name;
		this.quantity=quantity;
		this.price = price;

	}



	@Override
	public String toString() {
		return String.format("%-30s%-10s%-2s%-20s",name,quantity,"$",price);
	}
	
	
}
