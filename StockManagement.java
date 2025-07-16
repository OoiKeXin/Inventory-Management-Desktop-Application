package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javafx.scene.control.Alert;


public class StockManagement {
	static public ArrayList<Product> productList = new ArrayList<>();
    static private final String DB_URL = "jdbc:mysql://localhost:3306/stock_management";
    static private final String DB_USER = "root"; // replace with your username
    static private final String DB_PASSWORD = "Okx0174405829!"; // replace with your password
    
    static public void addStock(ArrayList<Product> product, Scanner input){
        boolean check = false, check3= true;
        String str1;
    	do {
    		System.out.print("\nIs it existing product? (1 - Yes / 2 - No / 0 - Exit): ");
    		str1 = input.nextLine();
    		if(isNumeric(str1) && Integer.parseInt(str1) >= 0 && Integer.parseInt(str1) <= 2) {
    			check3 = false;
    		}
    	}while(check3);
    	
    	int option = Integer.parseInt(str1);
	    if(option == 1){
	    	int choice = displayProduct(product, input) - 1;
	    	if(choice >= 0) {
	    		if(product.get(choice).isStatus()) {
			        
			        	do{
			        		System.out.print("Amount of item to add: ");
			        		String add = input.nextLine();
			                if(isNumeric(add) && Integer.parseInt(add) >= 0 ){
			                	int addval = Integer.parseInt(add);
			                	int curVal = product.get(choice).getQuantity();
			                    product.get(choice).addStock(addval);
			                    modifyQuantity(product.get(choice).getItemNumber(), curVal + addval);
			                    check = false; 
			                } else {
			                	  System.out.print("Please enter a valid value!\n");
			                	  check = true;
			                }
			            }while(check);
	    			}else {
	    				System.out.print("The product is unavailable!\n");
	    			}
	    		}else {
	    			System.out.print("Product list is empty!\n");
	    		}
	        }else if(option == 2){
	        	addNewProduct(product, input);
	        }
    }

    static public int displayProduct(ArrayList<Product> product, Scanner input){
    	if(!product.isEmpty()) {
	    	Boolean checking = true;
	    	String index;
	        int i = 0;
	        for (Product p : product){
	            System.out.println(i + 1 + ". \n" + product.get(i) + "\n");
	            i++;
	        }
	        do {
		        System.out.print("Select the index number of product that you want to change: ");
		        index = input.nextLine();
		        if((Integer.parseInt(index)  < 0 || Integer.parseInt(index)  > product.size()) && isNumeric(index)) {
		        	System.out.print("Please enter a valid value!\n");
		        	checking = true;
		        	
		        }else {
		        	checking = false;
		        	
		        }
	        }while(checking);
	        return Integer.parseInt(index);
    	}else {
    		return -1;
    	}
    }
    
    static public int displayMenu(Scanner input){
    	boolean check = false;
        String choice;
        int val = -1;
        do{ 
        	System.out.print("\n1. View products\n2. Add stock\n3. Deduct stock\n4. Discontinue product\n0. Exit\nPlease enter a menu option: ");
            choice = input.nextLine();
        	if(isNumeric(choice) && Integer.parseInt(choice) >= 0 && Integer.parseInt(choice) < 5) {
        		val = Integer.parseInt(choice);
	            check = false;
        	}else {
        		check = true;
        	}
        }while(check);
        
        return val;
    }
    
    static public int addProduct( Scanner input) {
   	 String num;
        boolean check = false;
        do{
            System.out.print("Enter number of products to add: ");
            num = input.nextLine();
            if(Integer.parseInt(num) < 0 || isNumeric(num)){
                System.out.print("Please enter valid value: ");
                check = true;
            }else {
            	check = false;
            }
        }while(check);
        
        return Integer.parseInt(num);
    }
    
    static public void deductStock(ArrayList<Product> product, Scanner input){
        boolean check = false;
        int choice = displayProduct(product, input) -1;
        if(choice >= 0) {
        	if(product.get(choice).isStatus() != false) {
	        	if(product.get(choice).getQuantity() != 0) {
			        do{
			        System.out.print("Amount of item to deduct: ");
			        String deduct = input.nextLine();
			            if(Integer.parseInt(deduct) < 0 || Integer.parseInt(deduct) > product.get(choice).getQuantity()){
			                System.out.print("Please enter a valid value!\n");
			                check = true;
			            }else {
			            	int curVal = product.get(choice).getQuantity();
			                product.get(choice).deductStock(Integer.parseInt(deduct));
			                modifyQuantity(product.get(choice).getItemNumber(), curVal - Integer.parseInt(deduct));
			                check = false;
			                System.out.print("Successfully deduct the stock!\n");
			            }
			        }while(check); 
	        	}else {
	        		System.out.print("Product is out of stock! / The product is unavailable!\n");
	        	}
        	}else {
        		System.out.print("The product is unavailable!\n");
        	}
        }else {
        	System.out.print("Product list is empty!\n");
        }
    }
    
    static public void discontinueMenu(ArrayList<Product> product, Scanner input){
        boolean check = true;
        int choice = displayProduct(product, input) - 1;
        if(choice >= 0) {
	        do{
	        	Product p = product.get(choice);
		        if (choice >=0 && choice < product.size()) {
		        	if(p.isStatus()) {
			           product.get(choice).setStatus(false);
			           discontinue(product.get(choice).getItemNumber());
			           System.out.print("Successfully discontinued the product\n");
			           check = false;
		        	}else {
		        		System.out.print("The product has been discontinued recently!\n");
		        		check = false;
		        	}
		        }else {
		        	System.out.print("Please enter a valid value!");
		        	check = true;
		        }
	        }while(check);
        }else {
        	System.out.print("Product list is empty!\n");
        }
    }
    
    static public void decideMenu(int choice, ArrayList<Product> product, Scanner input){
        switch(choice){
            case 1:
                onlyDisplay(product);
                 break;
             case 2:
                 addStock(product, input);
                 break;
             case 3:
                 deductStock(product, input);
                 break;
             case 4:
                 discontinueMenu(product, input);
                 break;
             case 0:
                 break;
         }
    }
    
    static public void addNewProduct(ArrayList<Product> product, Scanner input){
        boolean check = false;
        String choice;
        do{
            System.out.print("1. TV\n2. Refrigerator\nEnter: ");
            choice = input.nextLine();
            if(Integer.parseInt(choice) >= 1 && Integer.parseInt(choice) <= 2 && isNumeric(choice)){
            	if(Integer.parseInt(choice) == 1){
                	addTV(product, input);    
                }else{
                	addRefrigerator(product, input);
                }
            	check = false;
            }else {
            	 System.out.print("Only number 1 or 2 allowed!\n");
                 check = true;
            }
        }while(check);
    }
    
    static public void addRefrigerator(ArrayList<Product> product, Scanner input){
    	Boolean check = true, check2 = true, check3 = true;
    	
        System.out.print("Enter name: ");
        String name = input.nextLine();
        System.out.print("\nDoor design: ");
        String doorDesign = input.nextLine();
        System.out.print("\nColor: ");
        String color = input.nextLine();
        
        double val1 = 0.0;
        do {
        	System.out.print("\nCapacity (L): ");
	        String capacity = input.nextLine();
	        if(isDouble(capacity) || isNumeric(capacity)) {
	        	val1 = Double.parseDouble(capacity);
	        	check = false;
	        }
        }while(check);
        
        
        int val2 = 0;
        do {
        	System.out.print("\nQuantity available in stock: ");
	        String quantity = input.nextLine();
	        if(isNumeric(quantity)) {
	        	val2 = Integer.parseInt(quantity);
	        	check2 = false;
	        }
        }while(check2);
        
        double val3 = 0.0;
        do {
        	System.out.print("\nPrice (RM): ");
	        String price = input.nextLine();
	        if(isDouble(price) || isNumeric(price)) {
	        	val3 = Double.parseDouble(price);
	        	check3 = false;
	        }
        }while(check3);
        
        System.out.print("\nItem number (RFG-): ");
        String itemNum = input.nextLine();
        Refrigerator newRefrigerator = new Refrigerator(name, val3, val2, itemNum, doorDesign, color, val1);
        product.add(newRefrigerator);
        addRefri(newRefrigerator);
    }
    
    static public void addTV(ArrayList<Product> product, Scanner input){
    	Boolean check = true, check2 = true, check3 = true;
    	
        System.out.print("Enter name: ");
        String name = input.nextLine();

        System.out.print("\nScreen Type: ");
        String screenType = input.nextLine();

        System.out.print("\nResolution (Pixels): ");
        String resolution = input.nextLine();  // Fixed typo here

        double val1 = 0.0;
        do {
        	System.out.print("\nDisplay Size (inches): ");
	        String size = input.nextLine();
	        if(isDouble(size) || isNumeric(size)) {
	        	val1 = Double.parseDouble(size);
	        	check = false;
	        }
        }while(check);

        
        int val2 = 0;
        do {
        	System.out.print("\nQuantity available in stock: ");
	        String quantity = input.nextLine();
	        if(isNumeric(quantity)) {
	        	val2 = Integer.parseInt(quantity);
	        	check2 = false;
	        }
        }while(check2);

        double val3 = 0;
        do {
        	System.out.print("\nPrice (RM): ");
	        String price = input.nextLine();
	        if(isDouble(price) || isNumeric(price)) {
	        	val3 = Double.parseDouble(price);
	        	check3 = false;
	        }
        }while(check3);

        System.out.print("\nItem number (TV-): ");
        String itemNum = input.nextLine();
        
        TV newTV = new TV(name, val3, val2, itemNum, screenType, resolution, val1);
        product.add(newTV);
        addTV(newTV);
    }

    static public void onlyDisplay(ArrayList<Product> product){
    	if(!product.isEmpty()) {
	        int i = 0;
	        for (Product p : product){
	            System.out.println((i + 1) + ". \n" + p + "\n");
	            i++;
	        }
	    }else {
	    	System.out.print("Product list is empty!\n");
	    }
    } 
    
    static public void main(String[] args) {
    	Scanner input = new Scanner(System.in);
    	System.out.print("Welcome to the SMS\n\n");
    	LocalDateTime now = LocalDateTime.now();

        // Format it nicely
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formattedDateTime = now.format(formatter);

        // Print to console
        System.out.println("Current date and time: " + formattedDateTime);
        System.out.println("\nPresented by: \nKoay Wai Wen \nJaslyn Lau Qian Yu \nOoi Ke Xin \nTeoh Kah Yong");

        Boolean check = false;
        do {
	    	System.out.print("\nEnter your name: ");
	    	String name = input.nextLine();
	
	    	if (!name.matches("[a-zA-Z ]+")) {
	    	    System.out.print("Please enter only alphabetic characters for the name!\n\n");
	    	    check = true;
	    	} else {
	    	    UserInfo userInfo = StockManagement.loginUser(name);
	    	    check = false;
	    	    if (userInfo == null) {
	    	        userInfo = new UserInfo();
	    	        userInfo.setName(name);
	    	        userInfo.generateUserId(name);
	    	        StockManagement.addUser(userInfo);
	    	    }
	    	    loadProduct();
	    	    int choices;
	    	    do {
	    	        choices = displayMenu(input);
	    	        decideMenu(choices, productList, input);
	    	    } while (choices != 0);
	
	    	    System.out.print("You have log out from: " + userInfo.getId() + " (" + userInfo.getName() + ")\n");
	    	}
    	}while(check);
    	input.close();
 
    }
       
    static public UserInfo loginUser(String name) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);  // Set the name parameter
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // User found, returning user info
                UserInfo user = new UserInfo();
                user.setId(rs.getString("user_id"));
                user.setName(rs.getString("username"));
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    static public boolean addUser(UserInfo newuser) {
        String query = "INSERT INTO users (user_id, username) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

        	stmt.setString(1, newuser.getId());
            stmt.setString(2, newuser.getName());
            
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    static public void getAllProducts() throws SQLException {
        String query = """
            SELECT p.product_id, p.name, p.price, p.quantity, p.itemNumber, p.status,
                   t.screenType, t.resolution, t.displaySize, NULL AS doorDesign, NULL AS color, NULL AS capacity
            FROM products p
            LEFT JOIN tv_details t ON p.product_id = t.product_id

            UNION ALL

            SELECT p.product_id, p.name, p.price, p.quantity, p.itemNumber, p.status,
                   NULL, NULL, NULL, r.doorDesign, r.color, r.capacity
            FROM products p
            LEFT JOIN refri_details r ON p.product_id = r.product_id

            ORDER BY product_id
        """;

        // Map to track added products by product_id
        Map<Integer, Product> productMap = new HashMap<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int productId = rs.getInt("product_id");

                // If product already exists in the map, skip it to avoid duplication
                if (!productMap.containsKey(productId)) {
                    String name = rs.getString("name");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    String itemNumber = rs.getString("itemNumber");
                    boolean status = rs.getBoolean("status");

                    if (rs.getString("screenType") != null) {
                        // It's a TV
                        TV tv = new TV(name, price, quantity, itemNumber,
                                       rs.getString("screenType"),
                                       rs.getString("resolution"),
                                       rs.getDouble("displaySize"));
                        tv.setStatus(status);
                        productList.add(tv);
                        productMap.put(productId, tv); // Store in map
                    } else {
                        // It's a Refrigerator
                        Refrigerator rf = new Refrigerator(name, price, quantity, itemNumber,
                                                           rs.getString("doorDesign"),
                                                           rs.getString("color"),
                                                           rs.getDouble("capacity"));
                        rf.setStatus(status);
                        productList.add(rf);
                        productMap.put(productId, rf); // Store in map
                    }
                }
            }
        }
    }

    static public void loadProduct() {
    	if(StockManagement.productList.isEmpty()) {
            try {
                StockManagement.getAllProducts();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    static public void modifyQuantity(String itemNumber, int newQuantity) {
    	String updateSQL = "UPDATE products SET quantity = ? WHERE itemNumber = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(updateSQL)) {

            // Set the new quantity and item number
            stmt.setInt(1, newQuantity);        // New quantity value
            stmt.setString(2, itemNumber);      // The item number to identify the product

            // Execute the update statement
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Product quantity updated successfully.");
            } else {
                System.out.println("No product found with the provided item number.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    static public void discontinue(String itemNumber){
    	String updateSQL = "UPDATE products SET status = ? WHERE itemNumber = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(updateSQL)) {

            // Set the new quantity and item number
            stmt.setBoolean(1, false);        // New quantity value
            stmt.setString(2, itemNumber);      // The item number to identify the product

            // Execute the update statement
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Product discontinued successfully.\n");
            } else {
                System.out.println("No product found with the provided item number.\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    static public void addRefri(Refrigerator newRefri) {
      	 // Step 1: Insert common product details into `products` table
          String productInsertSQL = "INSERT INTO products (name, price, quantity, itemNumber, status) VALUES (?, ?, ?, ?, ?)";
          try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
          	PreparedStatement stmt = conn.prepareStatement(productInsertSQL, Statement.RETURN_GENERATED_KEYS)){
              stmt.setString(1, newRefri.getName());
              stmt.setDouble(2, newRefri.getPrice());
              stmt.setInt(3, newRefri.getQuantity());
              stmt.setString(4, newRefri.getItemNumber());
              stmt.setBoolean(5, true); // Assuming the product is available by default
              stmt.executeUpdate();

              // Get the generated product_id (auto-incremented)
              ResultSet generatedKeys = stmt.getGeneratedKeys();
              int productId = -1;
              if (generatedKeys.next()) {
                  productId = generatedKeys.getInt(1);
              }

              if (productId != -1) {
                  // Step 2: Insert TV-specific details into `tv_details` table
                  String refriInsertSQL = "INSERT INTO refri_details (product_id, doorDesign, color, capacity) VALUES (?, ?, ?, ?)";
                  try (PreparedStatement refriStmt = conn.prepareStatement(refriInsertSQL)) {
                	  refriStmt.setInt(1, productId); // Foreign key to products table
                	  refriStmt.setString(2, newRefri.getDoorDesign());
                	  refriStmt.setString(3, newRefri.getColor());
                	  refriStmt.setDouble(4, newRefri.getCapacity());
                	  refriStmt.executeUpdate();
                      System.out.println("Refrigerator product added successfully.");
                  }
              } else {
                  System.out.println("Failed to insert product.");
              }
   	    } catch (SQLException e) {
   	        e.printStackTrace();
   	    }
    }

    static public void addTV(TV newTV) {
   	 // Step 1: Insert common product details into `products` table
       String productInsertSQL = "INSERT INTO products (name, price, quantity, itemNumber, status) VALUES (?, ?, ?, ?, ?)";
       try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
       	PreparedStatement stmt = conn.prepareStatement(productInsertSQL, Statement.RETURN_GENERATED_KEYS)){
           stmt.setString(1, newTV.getName());
           stmt.setDouble(2, newTV.getPrice());
           stmt.setInt(3, newTV.getQuantity());
           stmt.setString(4, newTV.getItemNumber());
           stmt.setBoolean(5, true); // Assuming the product is available by default
           stmt.executeUpdate();

           // Get the generated product_id (auto-incremented)
           ResultSet generatedKeys = stmt.getGeneratedKeys();
           int productId = -1;
           if (generatedKeys.next()) {
               productId = generatedKeys.getInt(1);
           }

           if (productId != -1) {
               // Step 2: Insert TV-specific details into `tv_details` table
               String tvInsertSQL = "INSERT INTO tv_details (product_id, screenType, resolution, displaySize) VALUES (?, ?, ?, ?)";
               try (PreparedStatement tvStmt = conn.prepareStatement(tvInsertSQL)) {
                   tvStmt.setInt(1, productId); // Foreign key to products table
                   tvStmt.setString(2, newTV.getScreenType());
                   tvStmt.setString(3, newTV.getResolution());
                   tvStmt.setDouble(4, newTV.getDisplaySize());
                   tvStmt.executeUpdate();
                   System.out.println("TV product added successfully.");
               }
           } else {
               System.out.println("Failed to insert product.");
           }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
    }
    
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}


