package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.ListCell;


public class StockManagementUI extends Application {

	
	private String style = "-fx-background-color: #2c3e50;" +  // Darker blue
            "-fx-border-color: transparent;" +
            "-fx-text-fill: white;" +           
            "-fx-background-radius: 5;" +   
            "-fx-font-size: 13px;" + 
            "-fx-font-weight: bold;" + 
            "-fx-border-radius: 5;" +
            "-fx-focus-color: transparent;" +
            "-fx-faint-focus-color: transparent;";   

	private String style2 = "-fx-background-color: #2980b9;" +  // Darker shade of #3498db
            "-fx-border-color: transparent;" + 
            "-fx-text-fill: white;" + 
            "-fx-background-radius: 5;" + 
            "-fx-font-size: 13px;" + 
            "-fx-font-weight: bold;" + 
            "-fx-border-radius: 5;" +
            "-fx-focus-color: transparent;" +
            "-fx-faint-focus-color: transparent;";
	
	private String style3 = 
		    "-fx-background-color: #3B6BA5;" +          // Royal Blue
		    "-fx-border-color: transparent;" +
		    "-fx-text-fill: white;" +
		    "-fx-background-radius: 5;" +
		    "-fx-border-radius: 5;" +
		    "-fx-font-size: 13px;" +
		    "-fx-font-weight: bold;" +
		    "-fx-pref-height: 23px;" +                  // Keep height fixed if desired
		    "-fx-cursor: hand;" +
		    "-fx-focus-color: transparent;" +
		    "-fx-faint-focus-color: transparent;";
	
	@Override
	public void start(Stage primaryStage) {
	    BorderPane root = new BorderPane();
	    root.setStyle("-fx-background-color: linear-gradient(to bottom right, " +
	                  "#ff9a9e, #fad0c4, #a1c4fd, #c2e9fb);");

	    // ======= Top Left Menu and Quit Buttons =======
	    GridPane menuAndQuit = new GridPane();
	    Button about = new Button("About");
	    Button quit = new Button("Quit");

	    // Make the buttons smaller
	    about.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; " +
	                   "-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 12px; -fx-padding: 2 5 2 5;");
	    quit.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; " +
	                  "-fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 12px; -fx-padding: 2 5 2 5;");

	    menuAndQuit.add(about, 0, 0);
	    menuAndQuit.add(quit, 1, 0);
	    menuAndQuit.setAlignment(Pos.TOP_LEFT);
	    menuAndQuit.setPadding(new Insets(5));

	    // Wrap the menu bar in a BorderPane (top-left)
	    BorderPane topBar = new BorderPane();
	    topBar.setLeft(menuAndQuit);
	    root.setTop(topBar);

	    // ======= Title =======
	    Label welcome = new Label("Welcome to the SMS!");
	    welcome.setFont(Font.font("Arial", FontWeight.BOLD, 25));
	    welcome.setTextFill(Color.NAVY);

	    // ======= Date and Time =======
	    VBox timeDate = new VBox(10);
	    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
	    LocalDateTime now = LocalDateTime.now();
	    Label date = new Label("Date: " + now.format(dateFormat));
	    Label time = new Label("Time: "+ now.format(timeFormat));
	    date.setFont(Font.font("null", FontWeight.BOLD, 16));
	    time.setFont(Font.font("null", FontWeight.BOLD, 16));
	    timeDate.getChildren().addAll(date, time);
	    timeDate.setAlignment(Pos.CENTER);

	    // ======= Login Section =======
	    HBox login = new HBox(10);
	    Label user = new Label("Username:");
	    user.setFont(Font.font("null", FontWeight.BOLD, 16));
	    TextField username = new TextField();
	    Button loginBtn = new Button("Login");
	    loginBtn.setStyle(style3);
	    login.getChildren().addAll(user, username, loginBtn);
	    login.setAlignment(Pos.CENTER);

	    // ======= Combined Center VBox =======
	    VBox centerBox = new VBox(20);
	    centerBox.getChildren().addAll(welcome, timeDate, login);
	    centerBox.setAlignment(Pos.CENTER);
	    centerBox.setSpacing(50);
	    root.setCenter(centerBox);

	    // ======= Button Actions =======
	    about.setOnAction(e -> {
	        VBox aboutBox = new VBox(20);
	        aboutBox.setAlignment(Pos.CENTER);
	        aboutBox.setPadding(new Insets(20));
	        HBox holdname = new HBox();
	        Label present = new Label("Presented by: ");
	        present.setFont(Font.font("Arial", FontWeight.BOLD, 16));
	        Label designedBy = new Label("Koay Wai Wen\nJaslyn Lau Qian Yu\nOoi Ke Xin\nTeoh Kah Yong");
	        holdname.getChildren().addAll(present, designedBy);
	        holdname.setSpacing(10);
	        holdname.setAlignment(Pos.TOP_CENTER);
	        designedBy.setFont(Font.font("null", FontWeight.BOLD, 16));
	        Button returnBtn = new Button("Close");
	        returnBtn.setStyle(style3);
	        returnBtn.setOnAction(f -> start(primaryStage));
	        aboutBox.getChildren().addAll(holdname, returnBtn);
	        root.setCenter(aboutBox);
	    });

	    quit.setOnAction(e -> primaryStage.close());

	    loginBtn.setOnAction(e -> {
	        String name = username.getText();
	        if (!isAlpha(name)) {
	            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter only alphabetic characters for the name!");
	        } else {
	        	UserInfo userLogin = StockManagement.loginUser(name);
	        	if (userLogin != null) {
	        		displayMenuGUI(root, userLogin);
                } else {
                	UserInfo userInfo = new UserInfo();
                	String id = userInfo.generateUserId(name);
                	userInfo.setId(id);
    	            userInfo.setName(name);
                    if (StockManagement.addUser(userInfo)) {
                    	displayMenuGUI(root, userInfo);
                    }
                }
	        }
	    });
	    
	    Scene scene = new Scene(root,950, 600);
	    primaryStage.setResizable(false);
	    primaryStage.setScene(scene);
	    primaryStage.setTitle("SMS");
	    primaryStage.show();
	}
	    
	public void displayMenuGUI(BorderPane root, UserInfo user) {
	    // ======= Top Welcome Header =======
	    HBox header = new HBox();
	    Label name = new Label("Hi " + user.getId() +", SMS Welcome You!");
	    name.setTextFill(Color.BROWN);
	    name.setFont(Font.font("Arial", FontWeight.BOLD, 25));
	    header.getChildren().add(name);
	    header.setAlignment(Pos.CENTER);
	    header.setPadding(new Insets(10));
	    root.setTop(header);

	    // ======= Left Menu Buttons =======
	    VBox menuBox = new VBox(15);
	    menuBox.setPadding(new Insets(20));

	    Label label = new Label("Select a menu option:");
	    label.setFont(Font.font(null, FontWeight.BOLD, 15));

	    Button viewbtn = new Button("View Products");
	    viewbtn.setStyle(style);
	    Button addbtn = new Button("Add Stock");
	    addbtn.setStyle(style);
	    Button deductbtn = new Button("Deduct Stock");
	    deductbtn.setStyle(style);
	    Button discontinuebtn = new Button("Discontinue Product");
	    discontinuebtn.setStyle(style);
	    Button exit = new Button("Exit");
	    exit.setStyle(style);
	    

	    double buttonWidth = 150;
	    for (Button btn : new Button[]{viewbtn, addbtn, deductbtn, discontinuebtn, exit}) {
	        btn.setPrefWidth(buttonWidth);
	        btn.setPrefHeight(40);
	    }

	    menuBox.getChildren().addAll(label, viewbtn, addbtn, deductbtn, discontinuebtn, exit);
	    menuBox.setAlignment(Pos.TOP_CENTER);
	    menuBox.setSpacing(20);
	    root.setLeft(menuBox);

	    // ======= Button Event Handlers =======
	    viewbtn.setOnAction(e -> {
	    	onlyDisplayGUI(root);
	    	viewbtn.setStyle(style2);
	    	addbtn.setStyle(style);
	    	deductbtn.setStyle(style);
	    	discontinuebtn.setStyle(style);
	    });
	    addbtn.setOnAction(e -> {
	    	addStockGUI(root);
	    	addbtn.setStyle(style2);
	    	viewbtn.setStyle(style);
	    	deductbtn.setStyle(style);
	    	discontinuebtn.setStyle(style);
	    });
	    deductbtn.setOnAction(e -> {
	    	deductStockGUI(root);
	    	deductbtn.setStyle(style2);
	    	addbtn.setStyle(style);
	    	viewbtn.setStyle(style);
	    	discontinuebtn.setStyle(style);
	    });
	    discontinuebtn.setOnAction(e -> {
	    	discontinueMenuGUI(root);
	    	discontinuebtn.setStyle(style2);
	    	viewbtn.setStyle(style);
	    	addbtn.setStyle(style);
	    	deductbtn.setStyle(style);
	    });
	    exit.setOnAction(e -> {
	    	HBox holdName = new HBox();
	    	holdName.setAlignment(Pos.CENTER);
	    	Label displayName = new Label();

	    	displayName.setText("You have logged out from " + user.getId() + "(" + user.getName() +  ").");

	    	displayName.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
	    	holdName.getChildren().add(displayName);
	    	root.setCenter(holdName);
	    	root.setLeft(null);
	    	root.setTop(null);
	    	root.setBottom(null);
	    });
	    
	    StockManagement.loadProduct();
	    
	    // Clear any previously displayed content in the center
	    root.setCenter(null);
	}
	
	public void onlyDisplayGUI(BorderPane root) {
	    VBox grouping = new VBox(10);
	    grouping.setPadding(new Insets(58, 25, 13, 10));
	    root.setBottom(null);

	    if (!StockManagement.productList.isEmpty()) {
	        ListView<String> productListView = new ListView<>();
	        productListView.setPrefWidth(500);
	        productListView.setPrefHeight(600);
	        productListView.setStyle("-fx-font-family: monospace; -fx-font-size: 14px;");

	        // Add simulated header row
	        String header = String.format("%-3s %-20s %-20s %-20s %-10s %-10s", "No.", "Item Number", "Name", "Product Type", "Quantity", "Status");
	        productListView.getItems().add(header);

	        // Add product rows
	        int index = 1;
	        for (Product p : StockManagement.productList) {
	            String type = (p instanceof TV) ? "TV" : "Refri";
	            String row = String.format("%-4d %-20s %-20s %-20s %-10d %-10s",
	                index, p.getItemNumber(), p.getName(), type, p.getQuantity(), p.isStatus());
	            productListView.getItems().add(row);
	            index++;
	        }

	        productListView.setCellFactory(lv -> new ListCell<String>() {
	            @Override
	            protected void updateItem(String item, boolean empty) {
	                super.updateItem(item, empty);
	                if (empty) {
	                    setText(null);
	                    setStyle(""); // Reset the style if the cell is empty
	                } else {
	                    setText(item);
	                    if (getIndex() == 0) {
	                        // Set the background color for the first row (index 0) to black,
	                        // text color to white, and bold the text
	                        setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-weight: bold;");
	                    } else {
	                        setStyle(""); // Reset style for other rows
	                    }
	                    
	                    // Check if the item is selected
	                    if (isSelected()) {
	                        // Apply the gray background for selected items
	                        setStyle("-fx-background-color: gray; -fx-text-fill: white; -fx-font-weight: bold;");
	                    }
	                }
	            }
	        });

	        	// Optional: Set a fixed size for the ListView
	        productListView.setPrefSize(600, 400);
	        grouping.getChildren().add(productListView);
	        grouping.setAlignment(Pos.TOP_CENTER);
	        root.setCenter(grouping);
	        
	        HBox h1 = new HBox();
	        root.setBottom(h1);
	        h1.setPadding(new Insets(20, 20, 20, 20));
	       
	    } else {
	        Label emptyList = new Label("The product list is empty.");
	        emptyList.setStyle("-fx-font-size: 25px; -fx-text-fill: #FF6347; -fx-font-weight: bold;");
	        VBox emptyMessageBox = new VBox(emptyList);
	        emptyMessageBox.setAlignment(Pos.CENTER);
	        root.setCenter(emptyMessageBox);
	    }
	}

	public ListView<String> displayProductGUI(BorderPane root) {
	    VBox grouping = new VBox(10);
	    grouping.setPadding(new Insets(28, 25, 20, 10));
	    grouping.setSpacing(10);
	    grouping.setAlignment(Pos.CENTER);

	    ListView<String> productListView = new ListView<>();
	    if (StockManagement.productList.size() != 0) {
	        
		        productListView.setPrefWidth(500);
		        productListView.setPrefHeight(600);
		        productListView.setStyle("-fx-font-family: monospace; -fx-font-size: 14px;");

		        // Add simulated header row
		        String header = String.format("%-3s %-20s %-20s %-20s %-10s %-10s", "No.", "Item Number", "Name", "Product Type", "Quantity", "Status");
		        productListView.getItems().add(header);

		        // Add product rows
		        int index = 1;
		        for (Product p : StockManagement.productList) {
		            String type = (p instanceof TV) ? "TV" : "Refri";
		            String row = String.format("%-4d %-20s %-20s %-20s %-10d %-10s",
		                index, p.getItemNumber(), p.getName(), type, p.getQuantity(), p.isStatus());
		            productListView.getItems().add(row);
		            index++;
		        }
		        
		        productListView.setCellFactory(lv -> new ListCell<String>() {
		            @Override
		            protected void updateItem(String item, boolean empty) {
		                super.updateItem(item, empty);
		                if (empty || item == null) {
		                    setText(null);
		                    setStyle("");
		                } else {
		                    setText(item);
		                    if (getIndex() == 0) {
		                        setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-weight: bold;");
		                    } else if (isSelected()) {
		                        setStyle("-fx-background-color: gray; -fx-text-fill: white; -fx-font-weight: bold;");
		                    } else {
		                        setStyle("");
		                    }
		                }
		            }
		        });

	        Label label = new Label("Select a product: ");
	        Label message2 = new Label();
	        label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
	        HBox text = new HBox();
	        text.getChildren().addAll(label, message2);
	        
	        Button selectBtn = new Button("Confirm");
	        selectBtn.setStyle(style);
	        HBox boxBtn = new HBox(50);
	        boxBtn.getChildren().addAll(selectBtn);
	        boxBtn.setAlignment(Pos.CENTER_RIGHT);
	        
	        productListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
	            if (newVal != null && productListView.getSelectionModel().getSelectedIndex() > 0) {  // avoid header
	                int selectedindex = productListView.getSelectionModel().getSelectedIndex() - 1; // Adjust for header
	                Product selectedProduct = StockManagement.productList.get(selectedindex);
	                
	                confirmationMes("Confirmation", "Select " + selectedProduct.getName() + " (Stock: " + selectedProduct.getQuantity() + ")");
	                
	            }else {
	            	showAlert(AlertType.ERROR, "Invalid Input", "No product selected or header clicked!");
	            }
	        });
	        grouping.getChildren().addAll(text, productListView);
	        root.setCenter(grouping);
	    } else {
	        // If no products exist in the list, center the message in the root
	        Label emptyList = new Label("The product list is empty.");
	        emptyList.setStyle("-fx-font-size: 25px; -fx-text-fill: #FF6347;-fx-font-weight: bold;"); // Customize the style if needed
	        emptyList.setAlignment(Pos.CENTER);
	        VBox emptyMessageBox = new VBox(emptyList);
	        emptyMessageBox.setAlignment(Pos.CENTER); // Ensures the message is centered
	        
	        // Set the center of root to the empty message VBox
	        root.setCenter(emptyMessageBox);
	    }

	    return productListView;
	}

	public void addStockGUI(BorderPane root) {
	    VBox vbox = new VBox(10);
	    vbox.setPadding(new Insets(20));
	    
	    VBox grouping2 = new VBox();
	    grouping2.setPadding(new Insets(10, 23, 0, 0));
	    
	    root.setBottom(null);
	    
	    RadioButton existingProductRadio = new RadioButton("Existing Product");
	    existingProductRadio.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;"); 
	    RadioButton newProductRadio = new RadioButton("New Product");
	    newProductRadio.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;"); 
	    ToggleGroup group = new ToggleGroup();
	    existingProductRadio.setToggleGroup(group);
	    newProductRadio.setToggleGroup(group);
	    
	    HBox btnHbox = new HBox();
	    Button continuebtn = new Button("Continue");
	    continuebtn.setStyle(style3);
	    btnHbox.getChildren().add(continuebtn);
	    
	    vbox.getChildren().addAll(existingProductRadio, newProductRadio);
	    vbox.setPadding(new Insets(52, 20, 20, 20));
	    vbox.setSpacing(20);
	    
	    grouping2.getChildren().addAll(vbox, continuebtn);
	    grouping2.setAlignment(Pos.TOP_RIGHT);
	    root.setCenter(grouping2);
	    
	    continuebtn.setOnAction(e -> {
	        if (existingProductRadio.isSelected() && StockManagement.productList.size() != 0) {
	            // Show product selection view
	        	Button contbtn = new Button("Confirm");
	        	HBox hbox = new HBox();
	        	hbox.setAlignment(Pos.CENTER);
	        	//hbox.setPadding(new Insets(50, 20, 20, 20));
	        	
	            ListView<String> productListView = displayProductGUI(root);
	            contbtn.setStyle(style3);
	            
	            root.setBottom(hbox);
	            hbox.getChildren().add(contbtn);
	            hbox.setPadding(new Insets(20, 20, 20, 20));
	            hbox.setAlignment(Pos.TOP_CENTER);
 
	            contbtn.setOnAction(f -> {	            	
	                int selectedIndex = productListView.getSelectionModel().getSelectedIndex() - 1;
	                if (selectedIndex >= 0) {
	                    // Product selected, now show quantity input
	                    ComboBox<Integer> comboBox = new ComboBox<>();
	                    for (int i = 1; i <= 100; i++) {
	                        comboBox.getItems().add(i);
	                        comboBox.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
	                    }
	                    
	                    comboBox.setPrefWidth(350); 
	                    comboBox.setMaxWidth(200);  // Optional: restrict max width

	                    comboBox.setStyle(
	                    	    "-fx-font-size: 15px;" +
	                    	    "-fx-font-weight: bold;" +
	                    	    "-fx-background-color: white;" +
	                    	    "-fx-border-color: #3B6BA5;" +
	                    	    "-fx-border-radius: 5;" +
	                    	    "-fx-background-radius: 5;" +
	                    	    "-fx-padding: 0 0 0 4;" +
	                    	    "-fx-text-fill: #2c3e50;" +
	                    	    "-fx-cursor: hand;"
	                    	);
	                    
	                    Button confirmbtn = new Button("Add");
	                    HBox addProduct = new HBox();
	                    Label quantity = new Label("Enter the quantity of stocks: ");
	                    quantity.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;"); 
	                    addProduct.getChildren().addAll(quantity, comboBox, confirmbtn);
	                    addProduct.setSpacing(23);
	                    addProduct.setPadding(new Insets(60, 15, 0, 0));
	                    addProduct.setAlignment(Pos.TOP_CENTER);

	                    confirmbtn.setStyle(style3);
	                    root.setBottom(null);
	                    root.setCenter(addProduct);
	                    confirmbtn.setOnAction(g -> {
	                        Integer selectedValue = comboBox.getValue();
	                        if (selectedValue != null) {
	                            Product p = StockManagement.productList.get(selectedIndex);
	                            if(p.isStatus()) {
		                            if (confirmationMes("Updated Stock", "Add " + selectedValue + " new stocks to " + p.getName() + " ?")) {
		                            	int newQuantity = p.getQuantity() + selectedValue;
		                                p.setQuantity(newQuantity);
		                                StockManagement.modifyQuantity(p.getItemNumber(), newQuantity);
		                                showAlert(AlertType.INFORMATION, "Success", "Stock updated successfully!");
		                                addStockGUI(root);
		                                root.setBottom(null);
		                            } else {
		                                showAlert(AlertType.ERROR, "Alert", "Failed to add stock.");
		                            }
	                            }else {
	                            	showAlert(AlertType.ERROR, "Alert", "This product is currently unavailable!");
	                            }
	                        } else {
	                            showAlert(AlertType.ERROR, "Alert", "Please select a valid quantity.");
	                        }
	                    });
	                } else {
	                    showAlert(AlertType.ERROR, "Alert", "No product selected.");
	                }
	            });

	        } else if (existingProductRadio.isSelected() && StockManagement.productList.size() == 0) {
	            // Handle case where there are no existing products
	        	Label emptyList = new Label("The product list is empty.");
	            emptyList.setStyle("-fx-font-size: 25px; -fx-text-fill: #FF6347; -fx-font-weight: bold;"); // Customize the style if needed
	            emptyList.setAlignment(Pos.CENTER);
	            VBox emptyMessageBox = new VBox(emptyList);
	            emptyMessageBox.setAlignment(Pos.CENTER); // Ensures the message is centered
	            
	            // Set the center of root to the empty message VBox
	            root.setCenter(emptyMessageBox);
	        } else if (newProductRadio.isSelected()) {
	            addNewProductGUI(root); // Handle adding a new product
	        } else {
	            showAlert(AlertType.ERROR, "Invalid Option", "Please choose type of product to add!");
	        }
	    });
	}
	
	public void deductStockGUI(BorderPane root) {
	    VBox vbox = new VBox(10);
	    vbox.setPadding(new Insets(20));

	    // Step 1: Create the 'Continue' button for product selection
	    Button continuebtn = new Button("Continue");
	    continuebtn.setStyle(style3);
	    HBox hbox = new HBox();
    	hbox.setAlignment(Pos.CENTER);
        
        
        hbox.getChildren().add(continuebtn);
        hbox.setPadding(new Insets(20, 20, 20, 20));
        hbox.setAlignment(Pos.TOP_CENTER);
        if(!StockManagement.productList.isEmpty()) {
        	root.setBottom(hbox);
        } 

	    // Step 2: Display the product list for selection
        Integer[] selectedQuantity = {null};
	    ListView<String> productListView = displayProductGUI(root);
	    
	    continuebtn.setOnAction(e -> {
	        int selectedIndex = productListView.getSelectionModel().getSelectedIndex() -1;
	        if (selectedIndex >= 0) {
	            // Step 3: Once the product is selected, show quantity deduction input
	            Product selectedProduct = StockManagement.productList.get(selectedIndex);

	            // Create UI components for quantity input and confirmation
	            if(selectedProduct.getQuantity() > 0) {
		            VBox grouping = new VBox(10);
		            HBox deductProduct = new HBox();
		            deductProduct.setSpacing(23);
		            Label quantityLabel = new Label("Select amount to deduct: ");
		            quantityLabel.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;"); 
		            ComboBox<Integer> quantityComboBox = new ComboBox<>();
	
		            quantityComboBox.setPrefWidth(350); 
		            quantityComboBox.setMaxWidth(200);  // Optional: restrict max width
		            
		            // Populate the ComboBox with quantity options (1 to the product's current quantity)
		            for (int i = 1; i <= selectedProduct.getQuantity(); i++) {
		                quantityComboBox.getItems().add(i);
		                quantityComboBox.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
		            }
		            
		            quantityComboBox.setStyle(
                    	    "-fx-font-size: 15px;" +
                    	    "-fx-font-weight: bold;" +
                    	    "-fx-background-color: white;" +
                    	    "-fx-border-color: #3B6BA5;" +
                    	    "-fx-border-radius: 5;" +
                    	    "-fx-background-radius: 5;" +
                    	    "-fx-padding: 0 0 0 4;" +
                    	    "-fx-text-fill: #2c3e50;" +
                    	    "-fx-cursor: hand;"
                    	);
	
		            Button confirmbtn = new Button("Confirm");
		            root.setBottom(null);
		            confirmbtn.setStyle(style3);
	
		            deductProduct.getChildren().addAll(quantityLabel, quantityComboBox, confirmbtn);
		            deductProduct.setAlignment(Pos.CENTER);
		            grouping.getChildren().addAll(deductProduct);
		            grouping.setAlignment(Pos.TOP_CENTER);
		            grouping.setPadding(new Insets(58, 0, 0, 0));
	
		            // Step 4: Set the center to show deduction input UI
		            root.setCenter(grouping);
	
		            // Handle the confirm button action
		            confirmbtn.setOnAction(g -> {
		                selectedQuantity[0] = quantityComboBox.getValue();
		                if (selectedQuantity[0] != null && selectedQuantity[0] <= selectedProduct.getQuantity()) {
		                    	if(selectedProduct.isStatus()) {
		                        // Confirm the deduction
			                        if (confirmationMes("Deduct Product", "Do you want to deduct " + selectedQuantity[0] + " products from " + selectedProduct.getName() + "?")) {
			                        	int newQuantity = selectedProduct.getQuantity() - selectedQuantity[0];
			                            selectedProduct.setQuantity(newQuantity);
			                            StockManagement.modifyQuantity(selectedProduct.getItemNumber(), newQuantity);
			                            root.setBottom(null);
			                	        deductStockGUI(root);
			                        }
			                    }else {
			                    	showAlert(AlertType.ERROR, "Alert", "This product is currently unavailable!");
			                    } 
		                 }else {
		                	 showAlert(AlertType.ERROR, "Alert", "This product is currently out of stock!");
		                 }
		            });
	            }else {
	            	showAlert(AlertType.ERROR, "Alert", "This product is currently out of stock!");
	            }
	        } else {
	            showAlert(AlertType.ERROR, "Alert", "No product selected.");
	        }
	    });
	}
	
	public void discontinueMenuGUI(BorderPane root){
	    Button contbtn = new Button("Confirm");
	    contbtn.setStyle(style3);
		HBox hbox = new HBox();
	    hbox.setAlignment(Pos.CENTER);
	        
	    if(!StockManagement.productList.isEmpty()) {
	    	root.setBottom(hbox);
	    }
	    
	    hbox.getChildren().add(contbtn);
	    hbox.setPadding(new Insets(20, 20, 20, 20));
	    hbox.setAlignment(Pos.TOP_CENTER);

        ListView<String> productListView = displayProductGUI(root);
        
        contbtn.setOnAction(e -> {
        	int selectedProduct = productListView.getSelectionModel().getSelectedIndex()-1;
        	if(selectedProduct > -1) {
            	Product p = StockManagement.productList.get(selectedProduct);
            	if(p.isStatus()) {
		        	if(confirmationMes("Discontinue Product", "Do you want to discontinue " + p.getName() + " ?")) {
		        		StockManagement.productList.get(selectedProduct).setStatus(false);
		        		StockManagement.discontinue(p.getItemNumber());
		        		discontinueMenuGUI(root);
		        	}
            	} else {
            		showAlert(AlertType.ERROR, "Alert", "Cannot discontinue the product. This product is unavailable!");
            	}
	        }else {
	        	showAlert(AlertType.ERROR, "Alert", "No product selected.");
	        }
        });
        
    }
	
	public void addNewProductGUI(BorderPane root){
		
		Label typeOfProduct = new Label("Type pf product: ");
		typeOfProduct.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;"); 
		ToggleGroup group = new ToggleGroup();
	    RadioButton tvButton = new RadioButton("TV");
	    tvButton.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
	    tvButton.setToggleGroup(group);
	    RadioButton fridgeButton = new RadioButton("Refrigerator");
	    fridgeButton.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
	    fridgeButton.setToggleGroup(group);
	    
	    VBox grouping2 = new VBox();
	    grouping2.setPadding(new Insets(10, 23, 0, 0));
	    
	    VBox vbox = new VBox();
	    vbox.getChildren().addAll(tvButton, fridgeButton);
	    vbox.setSpacing(20);
	    vbox.setPadding(new Insets(52, 20, 20, 20));
	    
	    HBox btnHbox = new HBox();
	    Button continuebtn = new Button("Continue");
	    continuebtn.setStyle(style3);
	    btnHbox.getChildren().add(continuebtn);
	    
	    grouping2.getChildren().addAll(vbox, continuebtn);
	    grouping2.setAlignment(Pos.TOP_RIGHT);
	    root.setCenter(grouping2);

	    continuebtn.setOnAction(e -> {
		    if(tvButton.isSelected()) {
		    	addTVGUI(root);
		    }else if(fridgeButton.isSelected()) {
		    	addRefrigeratorGUI(root);
		    }else {
		    	showAlert(AlertType.ERROR, "Invalid Option", "Please choose type of product to add!");
		    }
	    });
    }
	
	public void addTVGUI(BorderPane root) {
	    GridPane gp = new GridPane();
	    gp.setAlignment(Pos.TOP_CENTER);
	    VBox vbox = new VBox();
	    gp.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
	    
	    // Labels and TextFields for product details
	    gp.add(new Label("Name: "), 0, 0); 
	    TextField nameField = new TextField();
	    gp.add(nameField, 1, 0);

	    gp.add(new Label("Screen Type: "), 0, 1);
	    TextField screenTypeField = new TextField();
	    gp.add(screenTypeField, 1, 1);

	    gp.add(new Label("Resolution (Pixels): "), 0, 2);
	    TextField resolutionField = new TextField();
	    gp.add(resolutionField, 1, 2);

	    gp.add(new Label("Display Size (inches): "), 0, 3);
	    TextField displaySizeField = new TextField();
	    gp.add(displaySizeField, 1, 3);

	    gp.add(new Label("Quantity available in stock: "), 0, 4);
	    TextField quantityField = new TextField();
	    gp.add(quantityField, 1, 4);

	    gp.add(new Label("Price (RM): "), 0, 5);
	    TextField priceField = new TextField();
	    gp.add(priceField, 1, 5);

	    gp.add(new Label("Item number (TV-): "), 0, 6);
	    TextField itemNumField = new TextField();
	    gp.add(itemNumField, 1, 6);

	    // Button to submit the form
	    Button addProductButton = new Button("Add TV");
	    
	    gp.add(addProductButton, 2, 7);
	    addProductButton.setStyle(style3);
	    
	    HBox btn =new HBox();
	    btn.getChildren().add(addProductButton);
	    root.setBottom(btn); 
	    
	    btn.setAlignment(Pos.CENTER);
	    btn.setPadding(new Insets(20, 20, 20, 20));

	    gp.setVgap(30);
	    gp.setHgap(30);
	    // Action for adding a new TV
	    addProductButton.setOnAction(e -> {
	        String name = nameField.getText();
	        String screenType = screenTypeField.getText();
	        String resolution = resolutionField.getText();
	        double displaySize = 0;
	        int quantity = 0;
	        double price = 0;
	        String itemNum = itemNumField.getText();

	        try {
	            // Parse displaySize, quantity, and price
	            displaySize = Double.parseDouble(displaySizeField.getText());
	            quantity = Integer.parseInt(quantityField.getText());
	            price = Double.parseDouble(priceField.getText());

	            // Check if the parsed values are valid
	            if (displaySize > 0 && quantity >= 0 && price > 0) {
	                // Create the TV object
	                TV tv = new TV(name, price, quantity, itemNum, screenType, resolution, displaySize);
	                StockManagement.productList.add(tv);
	                StockManagement.addTV(tv);
	                confirmationMes("Update Successfully", "TV added successfully!");


	                // Clear fields for the next product
	                nameField.clear();
	                screenTypeField.clear();
	                resolutionField.clear();
	                displaySizeField.clear();
	                quantityField.clear();
	                priceField.clear();
	                itemNumField.clear();
	                addTVGUI(root);
	            } else {
	            	showAlert(AlertType.ERROR, "Invalid Input", "Please enter valid positive values for display size, quantity, and price.");
	            }
	        } catch (NumberFormatException ex) {
	        	showAlert(AlertType.ERROR, "Invalid Input", "Please enter valid positive values for display size, quantity, and price.");
	        }
	    });

	    // Layout setup
	    vbox.getChildren().addAll(gp);
	    vbox.setPadding(new Insets(53, 0, 0, 0));
	    vbox.setSpacing(20);
	    vbox.setAlignment(Pos.TOP_CENTER);
	    root.setCenter(vbox);
	}

	public void addRefrigeratorGUI(BorderPane root) {
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.TOP_CENTER);
		VBox vbox = new VBox();
		gp.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
		
	    // Labels and TextFields for product details
	    gp.add(new Label("Enter name: "), 0, 0); 
	    TextField nameField = new TextField();
	    gp.add(nameField, 1, 0);

	    gp.add(new Label("Door design: "), 0, 1);
	    TextField doorDesignField = new TextField();
	    gp.add(doorDesignField, 1, 1);

	    gp.add(new Label("Color: "), 0, 2);
	    TextField colorField = new TextField();
	    gp.add(colorField, 1, 2);

	    gp.add(new Label("Capacity (L): "), 0, 3);
	    TextField capacityField = new TextField();
	    gp.add(capacityField, 1, 3);

	    gp.add(new Label("Quantity available in stock: "), 0, 4);
	    TextField quantityField = new TextField();
	    gp.add(quantityField, 1, 4);

	    gp.add(new Label("Price (RM): "), 0, 5);
	    TextField priceField = new TextField();
	    gp.add(priceField, 1, 5);

	    gp.add(new Label("Item number (RFG-): "), 0, 6);
	    TextField itemNumField = new TextField();
	    gp.add(itemNumField, 1, 6);

	    // Button to submit the form
	    Button addProductButton = new Button("Add Refrigerator");
	    addProductButton.setStyle(style3);
	    gp.add(addProductButton, 2, 7);

	    HBox btn =new HBox();
	    btn.getChildren().add(addProductButton);
	    root.setBottom(btn); 
	    
	    btn.setAlignment(Pos.CENTER);
	    btn.setPadding(new Insets(20, 20, 20, 20));
	    
	    gp.setVgap(30);
	    gp.setHgap(30);

	    // Action for adding a new Refrigerator
	    addProductButton.setOnAction(e -> {
	        String name = nameField.getText();
	        String doorDesign = doorDesignField.getText();
	        String color = colorField.getText();
	        double capacity = 0;
	        int quantity = 0;
	        double price = 0;
	        String itemNum = itemNumField.getText();

	        try {
	            // Parse capacity, quantity, and price
	            capacity = Double.parseDouble(capacityField.getText());
	            quantity = Integer.parseInt(quantityField.getText());
	            price = Double.parseDouble(priceField.getText());

	            // Check if the parsed values are valid
	            if (capacity > 0 && quantity >= 0 && price > 0) {
	                // Create the Refrigerator object
	                Refrigerator fridge = new Refrigerator(name, price, quantity, itemNum, doorDesign, color, capacity);
	                StockManagement.productList.add(fridge);
	                StockManagement.addRefri(fridge);
	                confirmationMes("Update Successfully", "Refrigerator added successfully!");


	                // Add success message and reset form fields
	                vbox.getChildren().clear(); // clear form fields and feedback label

	                // Clear fields after successful addition
	                nameField.clear();
	                doorDesignField.clear();
	                colorField.clear();
	                capacityField.clear();
	                quantityField.clear();
	                priceField.clear();
	                itemNumField.clear();
	                addRefrigeratorGUI(root);

	            } else {
	            	showAlert(AlertType.ERROR, "Invalid Input", "Please enter valid positive values for display size, quantity, and price.");
	            }

	        } catch (NumberFormatException ex) {
	        	showAlert(AlertType.ERROR, "Invalid Input", "Please enter valid positive values for display size, quantity, and price.");
	        }
	    });

	    // Layout setup
	    
	    vbox.getChildren().addAll(gp);
	    vbox.setPadding(new Insets(53, 0, 0, 0));
	    vbox.setSpacing(20);
	    vbox.setAlignment(Pos.TOP_CENTER);
	    root.setCenter(vbox);
	}

	private boolean isAlpha(String str) {
	    return str != null && str.matches("[a-zA-Z ]+");
	}
    
    public void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(message);
        
        
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle(
            "-fx-background-color: white;" +
            "-fx-font-family: 'Segoe UI';" +
            "-fx-font-size: 14px;" +
            "-fx-text-fill: #2c3e50;" +
            "-fx-border-color: #3498db;" +
            "-fx-border-width: 2;" +
            "-fx-border-radius: 8;" +
            "-fx-background-radius: 8;"
        );

        // Style the OK button (can apply to other buttons similarly)
        Button okButton = (Button) dialogPane.lookupButton(ButtonType.OK);
        okButton.setStyle(
            "-fx-background-color: #3498db;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 5;" +
            "-fx-border-radius: 5;"
        );
        
        alert.showAndWait();
    }
    
    public static boolean confirmationMes(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        // Custom buttons
        ButtonType yes = new ButtonType("Yes");
        ButtonType no = new ButtonType("No");
        alert.getButtonTypes().setAll(yes, no);

        // Style the dialog pane
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle(
            "-fx-background-color: white;" +
            "-fx-font-family: 'Segoe UI';" +
            "-fx-font-size: 14px;" +
            "-fx-text-fill: #2c3e50;" +
            "-fx-border-color: #3498db;" +
            "-fx-border-width: 2;" +
            "-fx-border-radius: 8;" +
            "-fx-background-radius: 8;"
        );

        // Style the Yes button
        Button yesBtn = (Button) dialogPane.lookupButton(yes);
        yesBtn.setStyle(
            "-fx-background-color: #27ae60;" + // Green
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 5;" +
            "-fx-border-radius: 5;"
        );

        // Style the No button
        Button noBtn = (Button) dialogPane.lookupButton(no);
        noBtn.setStyle(
            "-fx-background-color: #e74c3c;" + // Red
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 5;" +
            "-fx-border-radius: 5;"
        );

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == yes;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
