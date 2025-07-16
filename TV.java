package main;

public class TV extends Product{
    private String screenType, resolution;
    private double displaySize;

    public TV(String name, double price, int quantity, String itemNumber,
    String screenType, String resolution, double displaySize) 
    {
    super(name, price, quantity, itemNumber); // Call Product constructor
    this.screenType = screenType;
    this.resolution = resolution;
    this.displaySize = displaySize;
    }

    public String getScreenType() {
        return screenType;
    }

    public void setScreenType(String screenType) {
        this.screenType = screenType;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public double getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(double displaySize) {
        this.displaySize = displaySize;
    }

    @Override
    public double totalInventoryValue() {
        double price = getPrice();
        int quantity = getQuantity();
        return price*quantity;
    }

    @Override
    public String toString() {
        return String.format(
            "%-20s : %s\n" +                // Item number is a String, use %s
            "%-20s : %s\n" +                // Product name is a String
            "%-20s : %s\n" +                // Screen type is a String
            "%-20s : %s\n" +                // Resolution is a String
            "%-20s : %.2f inches\n" +       // Display size is a double, format to 2 decimal places
            "%-20s : %d\n" +                // Quantity available is an int
            "%-20s : RM %.2f\n" +           // Price (RM) is a double
            "%-20s : RM %.2f\n" +           // Inventory value (RM) is a double
            "%-20s : %s",                   // Product status is a String
            "Item number", getItemNumber(),
            "Product name", getName(),
            "Screen type", screenType,
            "Resolution", resolution,
            "Display size", displaySize,    // Correctly formatted as a double with %.2f
            "Quantity available", getQuantity(),
            "Price (RM)", getPrice(),
            "Inventory value (RM)", totalInventoryValue(),
            "Product status", (isStatus() ? "Active" : "Discontinued")
        );
    }

}


