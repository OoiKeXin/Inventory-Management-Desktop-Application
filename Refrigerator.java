package main;

public class Refrigerator extends Product{
    private String doorDesign, color;
    private double capacity;

    public Refrigerator(String name, double price, int quantity, String itemNumber,
    String doorDesign, String color, double capacity) 
    {
    super(name, price, quantity, itemNumber); // Call Product constructor
    this.doorDesign = doorDesign;
    this.color = color;
    this.capacity = capacity;
    }

    public String getDoorDesign() {
        return doorDesign;
    }

    public void setDoorDesign(String doorDesign) {
        this.doorDesign = doorDesign;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public double totalInventoryValue() {
        double price = super.getPrice();
        int quantity = super.getQuantity();
        return price*quantity;
    }

    @Override
    public String toString() {
        return String.format(
            "%-20s : %s\n" +                        // Item number is a String, use %s
            "%-20s : %s\n" +                        // Product name is a String
            "%-20s : %s\n" +                        // Door design is a String
            "%-20s : %s\n" +                        // Color is a String
            "%-20s : %.2f L\n" +                    // Capacity is a double, formatted to 2 decimal places
            "%-20s : %d\n" +                        // Quantity available is an int
            "%-20s : RM %.2f\n" +                   // Price (RM) is a double, formatted to 2 decimal places
            "%-20s : RM %.2f\n" +                   // Inventory value (RM) is a double
            "%-20s : %s",                           // Product status is a String
            "Item number", getItemNumber(),
            "Product name", getName(),
            "Door design", doorDesign,
            "Color", color,
            "Capacity (in Litres)", capacity,        // Properly formatted as a double
            "Quantity available", getQuantity(),
            "Price (RM)", getPrice(),
            "Inventory value (RM)", totalInventoryValue(),
            "Product status", (isStatus() ? "Active" : "Discontinued")
        );
    }

}
