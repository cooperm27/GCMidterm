import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class CashRegister {
	 
	private static List<Product> product=new ArrayList<>();
	private static Path filePath = Paths.get("Products.txt");
	private static Scanner scnr=new Scanner(System.in);
	private static List<Order>order=new ArrayList<>();
	private static int quantity=0;
	public static void main(String[] args) {
		int id=0;
		boolean response=true;
		
			
		System.out.println("Welcome to Our Grand Circus Restuarant:");
		System.out.println("Menu:");
		listProduct();
		
		do {
		
		id = Validator.getInt(scnr, "Which item would you like to order?");

		if (id == product.size() + 1) {
			break;
		}
		  System.out.println("How many would you like??");
		  quantity=scnr.nextInt();

		 
		  itemOrder(id,quantity);
		 
		  scnr.nextLine();
		response=Validator.getYesNo(scnr, "Would you like to order anything else?(y/n)");
	}while(response);
		System.out.printf("%-30s%-20s%-20s\n", "Item Name","Quantity","Price");
		System.out.printf("%-30s%-20s%-40.3s\n", "=============", "==========","=======");
		for(Order o:order)
		 System.out.println(o);
	
	}
	
	public static List<Product> readFile(){
		try {
			List<String> lines = Files.readAllLines(filePath);
			//Collections.sort(lines, String.CASE_INSENSITIVE_ORDER);
			List<Product> products = new ArrayList<>();

					for(String line:lines) {
				String[] parts=line.split("~~~");
				String name=parts[0];
				String category=parts[1];
				String description=parts[2];
				double price=Double.parseDouble(parts[3]);
				products.add(new Product(name,category,description,price));
			}
			return products;
		}catch(IOException e) {
			System.out.println("Unable to read file");
			return new ArrayList<>();
		}
	}
	
	public static void listProduct() {
		product = readFile();
	    int index=1;
		System.out.printf("%-30s%-15s%-60s%-25s\n", "     Item Name", "Category", "Description","Price");
		System.out.printf("%-30s%-15s%-60s%-25s\n", "     =============", "==========", "==========","=======");
		for (Product list : product) {
			// System.out.println(i++ + ". " + list);
			System.out.printf("%-5s%-25s%-15s%-60s%-2s%-25.2f\n",index, list.getName(), list.getCategory(), list.getDescription(),"$ ",list.getPrice());
			index++;
		}
		System.out.println();
	}
	
		
		      
		public static List<Order> itemOrder(int orderNumber,int quantity){
			
				
				//Collections.sort(lines, String.CASE_INSENSITIVE_ORDER);
				//Set<Order> order1 = new LinkedHashSet<>();
					for(int i=0;i<quantity;i++) {
					     String orderName=product.get(orderNumber-1).getName();
					     double orderPrice=product.get(orderNumber-1).getPrice();
					     orderPrice=orderPrice*quantity;
					     order.add(new Order(orderName,quantity,orderPrice));
				
					}
				return order;
			}		
		
			


	
}