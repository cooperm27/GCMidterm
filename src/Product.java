
public class Product {

	private String name;
	private String category;
	private String description;
	private double price;
	
	
	public Product() {
		super();
	}


	public Product(String category, String name, String description, double price) {
		super();
		this.category = category;
		this.name = name;
		this.description = description;
		this.price = price;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "Product [category=" + category + ", Name=" + name + ", description=" + description + ", price=" + price
				+ "]";
	}
	
	
	
}
