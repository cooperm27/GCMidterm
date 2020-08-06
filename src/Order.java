
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
	



	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public double getPrice() {
		return price;
	}




	public void setPrice(double price) {
		this.price = price;
	}




	public int getQuantity() {
		return quantity;
	}




	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}




	@Override
	public String toString() {
		return String.format("%-30s%-20s%-2s%-40.2f",name,quantity,"$",price);
	}
	
	
}
