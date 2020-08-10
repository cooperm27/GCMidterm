import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CashRegister {

	private static List<Product> product = new ArrayList<>();
	private static Path filePath = Paths.get("Products.txt");
	private static Scanner scnr = new Scanner(System.in);
	private static List<Order> order = new ArrayList<>();
	private static int quantity = 0;
	private static List<Order> temp = new ArrayList<>();
	private static CashPayment cash = new CashPayment();
	private static boolean cashPay = false;
	private static double finalTotal = 0;
	private static String question = null;

	public static void main(String[] args) {

		boolean orderAgain = true;
		do {
			int id = -1;
			boolean response = true;
			int addProduct = 0;

			System.out.println("Welcome to Our Grand Circus Restuarant!!!");
			System.out.println("\nMenu");

			do {
				listProduct();
				addProduct = product.size() + 1;
				System.out.println(addProduct + " Suggest a product");
				id = Validator.getPositiveInt(scnr, "\nWhich item would you like to order?");
				if (id > product.size() + 1) {
					System.out.println("Please enter the valid menu item number\n");
					continue;
				} else if (id == product.size() + 1) {
					Product p = getInputFromUser(scnr);
					appendLineToFile(p);
					System.out.println("Adding suggestion to menu");
					continue;
				}

				quantity = Validator.getPositiveInt(scnr, "How many would you like??");
				itemOrder(id, quantity);
				System.out.println("Adding " + quantity + " " + product.get(id - 1).getName() + " to your cart!!");

				response = Validator.getYesNo(scnr, "\nWould you like to order anything else?(y/n)");
			} while (response);

			// Printing order
			listOrder();

			// Printing receipt
			printReceipt();

			product.clear();
			order.clear();
			temp.clear();
			quantity = 0;
			cash = null;
			cashPay = false;
			finalTotal = 0;
			question = null;
			orderAgain = Validator.getYesNo(scnr, "\nWant to place another order (y/n)?");
		} while (orderAgain);
		System.out.println("Thank You for coming to Grand Circus Restuarant!!");
		scnr.close();
	}

	// Reading from the Products text file and sorting it alphabetically
	public static List<Product> readFile() {
		try {
			List<String> lines = Files.readAllLines(filePath);
			Collections.sort(lines, String.CASE_INSENSITIVE_ORDER);
			List<Product> products = new ArrayList<>();

			for (String line : lines) {
				String[] parts = line.split("~~~");
				String category = parts[0];
				String name = parts[1];
				String description = parts[2];
				double price = Double.parseDouble(parts[3]);
				products.add(new Product(category, name, description, price));
			}
			return products;
		} catch (IOException e) {
			System.out.println("Unable to read file");
			return new ArrayList<>();
		}
	}

	// Function to display the menu to the console
	public static void listProduct() {
		product = readFile();
		int index = 1;
		for (int i = 0; i < 110; i++)
			System.out.print("*");
		System.out.println();
		System.out.printf("%-25s%-25s%-50s%-25s\n", "     Category", "Name", "Description", "Price");
		System.out.printf("%-25s%-25s%-50s%-25s\n", "     ==========", "==========", "==============", "=======");
		for (Product list : product) {
			System.out.printf("%-5s%-20s%-25s%-50s%-2s%-25.2f\n", index, list.getCategory(), list.getName(),
					list.getDescription(), "$ ", list.getPrice());
			index++;
		}
		System.out.println();
		for (int i = 0; i < 110; i++)
			System.out.print("*");
		System.out.println();
	}

	// Method to print order
	public static void listOrder() {

		System.out.println("Your order is: ");
		for (int i = 0; i < 70; i++)
			System.out.print("*");
		System.out.println();
		System.out.printf("%-30s%-20s%-20s\n", "Item Name", "Quantity", "Price");
		System.out.printf("%-30s%-20s%-40s\n", "=============", "==========", "=======");

		delDup(order);
		for (Order o : temp)
			System.out.println(o);
		System.out.println();

		System.out.printf("%-5s%-2.2f\n", "Subtotal is : $ ", subtotal(temp));
		System.out.printf("%-5s%-2.2f\n", "Sales tax is : $ ", .06 * subtotal(temp));
		finalTotal = 1.06 * subtotal(temp);
		System.out.printf("%-5s%-2.2f\n", "Total is : $ ", finalTotal);
		System.out.println();
		for (int i = 0; i < 70; i++)
			System.out.print("*");
		System.out.println();

		question = Validator.getPaymentType(scnr, "How would you like to pay? (Cash/Card/Check)");

		if (question.equalsIgnoreCase("Cash")) {
			cash = new CashPayment(finalTotal);
			cashPay = true;
			System.out.print(cash.Payment(finalTotal));
		} else if (question.equalsIgnoreCase("Card")) {
			CreditCardPayment ccp = new CreditCardPayment(finalTotal);
			System.out.println(ccp.Payment(finalTotal));
		} else if (question.equalsIgnoreCase("Check")) {
			CheckPayment checkPayment = new CheckPayment(finalTotal);
			System.out.println(checkPayment.Payment(finalTotal));
		}
	}

	// Method to print the receipt
	public static void printReceipt() {
		System.out.println();
		for (int i = 0; i < 70; i++)
			System.out.print("*");
		System.out.println();
		System.out.println("Receipt: \n");
		System.out.printf("%-30s%-20s%-20s\n", "Item Name", "Quantity", "Price");
		System.out.printf("%-30s%-20s%-40s\n", "=============", "==========", "=======");
		for (Order o : temp)
			System.out.println(o);
		System.out.println();
		System.out.printf("%-5s%-2.2f\n", "Subtotal = $ ", subtotal(temp));
		System.out.printf("%-5s%-2.2f\n", "Sales tax = $ ", .06 * subtotal(temp));
		System.out.printf("%-5s%-2.2f\n", "Total  = $ ", finalTotal);
		System.out.printf("%-2s%-2.2f%-2s%-10s\n", "You are charged $ ", finalTotal, " by ", question);
		if (cashPay)
			System.out.printf("%-5s%-2.2f\n", "Change = $", cash.change);
		System.out.println();
		for (int i = 0; i < 70; i++)
			System.out.print("*");
		System.out.println();
	}

	// Adding customer order to the cart(in the list order)
	public static List<Order> itemOrder(int orderNumber, int quantity) {
		for (int i = 0; i < quantity; i++) {
			String orderName = product.get(orderNumber - 1).getName();
			double orderPrice = product.get(orderNumber - 1).getPrice();
			orderPrice = orderPrice * quantity;
			order.add(new Order(orderName, quantity, orderPrice));

		}
		return order;
	}

	// Deleting duplicates rows of order list
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

	// Function to calculate the sub-total
	private static double subtotal(List<Order> order) {
		double subtotal = 0;
		for (Order d : order)
			subtotal = subtotal + d.getPrice();
		return subtotal;
	}

	// Function to get input from user to add items to menu
	public static Product getInputFromUser(Scanner scnr) {

		String category = Validator.getNewCategory(scnr, "Enter a Category");
		String itemName = Validator.getString(scnr, "Enter item name: ");
		String description = Validator.getString(scnr, "Enter a brief description ");
		double price = Validator.getDouble(scnr, "Enter price: ");
		return new Product(category, itemName, description, price);

	}

	// Function to write the new item to text file(Products)
	public static void appendLineToFile(Product c) {
		String line = c.getCategory() + "~~~" + c.getName() + "~~~" + c.getDescription() + "~~~" + c.getPrice();
		List<String> lines = Collections.singletonList(line);
		try {
			Files.write(filePath, lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.out.println("Unable to write to file.");
		}
	}

}