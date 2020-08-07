import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CashRegister {

	private static List<Product> product = new ArrayList<>();
	private static Path filePath = Paths.get("Products.txt");
	private static Scanner scnr = new Scanner(System.in);
	private static List<Order> order = new ArrayList<>();
	private static int quantity = 0;
	private static List<Order> temp = new ArrayList<>();
	public static void main(String[] args) {
		int id = -1;

		boolean response = true;

		System.out.println("Welcome to Our Grand Circus Restuarant:");
		System.out.println("Menu:");

		do {
			listProduct();
			id = Validator.getInt(scnr, "Which item would you like to order?");
			if (id > product.size() + 1) {
				System.out.println("Please enter the valid menu item number\n");
				continue;
			}

			quantity = Validator.getInt(scnr, "How many would you like??");

			itemOrder(id, quantity);

		System.out.println("Adding "+ quantity + " " + product.get(id - 1).getName() + " to your cart!!");


		response = Validator.getYesNo(scnr, "Would you like to order anything else?(y/n)");
		} while (response);
		System.out.println("Your order is: ");
		System.out.printf("%-30s%-20s%-20s\n", "Item Name", "Quantity", "Price");
		System.out.printf("%-30s%-20s%-40s\n", "=============", "==========", "=======");

		delDup(order);
		for (Order o : temp)
			System.out.println(o);
		System.out.println("Your total is "+total(temp));

	}

	public static List<Product> readFile() {
		try {
			List<String> lines = Files.readAllLines(filePath);
			List<Product> products = new ArrayList<>();

			for (String line : lines) {
				String[] parts = line.split("~~~");
				String name = parts[0];
				String category = parts[1];
				String description = parts[2];
				double price = Double.parseDouble(parts[3]);
				products.add(new Product(name, category, description, price));
			}
			return products;
		} catch (IOException e) {
			System.out.println("Unable to read file");
			return new ArrayList<>();
		}
	}

	public static void listProduct() {
		product = readFile();
		int index = 1;
		System.out.printf("%-30s%-15s%-50s%-25s\n", "     Item Name", "Category", "Description", "Price");
		System.out.printf("%-30s%-15s%-50s%-25s\n", "     =============", "==========", "==========", "=======");
		for (Product list : product) {
			System.out.printf("%-5s%-25s%-15s%-50s%-2s%-25.2f\n", index, list.getName(), list.getCategory(),
					list.getDescription(), "$ ", list.getPrice());
			index++;
		}
		System.out.println();
	}

	public static List<Order> itemOrder(int orderNumber, int quantity) {

		// Collections.sort(lines, String.CASE_INSENSITIVE_ORDER);
		
		for (int i = 0; i < quantity; i++) {
			String orderName = product.get(orderNumber - 1).getName();
			double orderPrice = product.get(orderNumber - 1).getPrice();
			orderPrice = orderPrice * quantity;
			order.add(new Order(orderName, quantity, orderPrice));
	

		}
		return order;
	}

	public static List<Order> delDup(List<Order> order) {

		int j = 0;
		for (int i = 0; i < order.size() - 1; i++) {
			if (!order.get(i).getName().equals(order.get(i + 1).getName())) {
				temp.add(j++, order.get(i));
			}
		}
		temp.add(j++, order.get(order.size() - 1));
		return temp;
	}

	
	  private static double total(List<Order> order) { 
		  double total=0;
	  for(Order d:order) 
		  total=total+d.getPrice();	  
	  return total; 
	  }
	 

}