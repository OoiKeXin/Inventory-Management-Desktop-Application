package main;

public abstract class Product {
	   private String name, itemNumber;
	   private double price;
	   private int quantity;
	   private boolean status = true;

	   public Product() {
		   this.status = true;
	   }

	    public Product(String name, double price, int quantity, String itemNumber) {
	    this.name = name;
	    this.price = price;
	    this.quantity = quantity;
	    this.itemNumber = itemNumber;
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

	    public String getItemNumber() {
	        return itemNumber;
	    }

	    public void setItemNumber(String itemNumber) {
	        this.itemNumber = itemNumber;
	    }

	    public boolean isStatus() {
	        return status;
	    }

	    public void setStatus(boolean status) {
	        this.status = status;
	    }

	    public double totalInventoryValue() {
	        return price*quantity;
	    }

	    public void addStock(int add) {
	    	if (status) {
	            this.quantity += add;
	        } else {
	            System.out.println("Cannot add stock to a discontinued product.");
	        }
	    }
	    
	    public void deductStock(int deduct) {
	    	if (status) {
	            this.quantity -= deduct;
	        } else {
	            System.out.println("Cannot deduct stock to a discontinued product.");
	        }
	    }

	    @Override
	    public String toString() {
			return String.format("%-20s : %s\n%-20s : %s\n%-20s : RM %.2f\n%-20s : %d\n%-20s : RM %.2f\n%-20s : %s", 
	                "Item number", itemNumber, 
	                "Product name", name, 
	                "Price (RM)", price, 
	                "Quantity available", quantity, 
	                "Inventory value (RM)", totalInventoryValue(), 
	                "Product status" + (isStatus() ? "Active" : "Discontinued"));
		}

	}

