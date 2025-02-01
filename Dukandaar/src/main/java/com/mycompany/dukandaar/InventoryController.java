/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dukandaar;

/**
 *
 * @author Anshu
 */

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.HashMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class InventoryController {
    @FXML
    private VBox stocksContainer;
    @FXML
    
    private TextField itemNameField, quantityField, priceField;
    @FXML
     Button insertb,submit;
    String user;
    private  HashMap<String, Item> inventory;
    void setUser(String user)
    {this.user=user;
    inventory=Auth.getWholesalerInventory(user);
      updateDisplay();
        
    }
    public void initialize() {
        // Sample items
        
       
      
    }

    private void updateDisplay() {
        stocksContainer.getChildren().clear();
        for (String key : inventory.keySet()) {
            stocksContainer.getChildren().add(createItemRow(key, inventory.get(key)));
        }
    }

    private HBox createItemRow(String key, Item item) {
        
        Label nameLabel = new Label(key);
        Label valueLabel = new Label("Qty:");
        Label valueLabel2=new Label(" "+item.getQuantity());
        Label priceLabel = new Label("Price: ₹" + item.getPrice());

        Button plusButton = new Button("+");
        plusButton.setOnAction(e -> {
            item.setQuantity(item.getQuantity() + 1);
            updateDisplay();
        });

        Button minusButton = new Button("-");
        minusButton.setOnAction(e -> {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
            } else {
                inventory.remove(key);
            }
            updateDisplay();
        });

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            inventory.remove(key);
            updateDisplay();
        });
        deleteButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5;");
        plusButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5;");
        minusButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5;");
        insertb.setStyle("-fx-background-color: ##00ff84; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5;");
        submit.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5;");
        deleteButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5;");
        HBox row = new HBox(10, nameLabel,valueLabel,plusButton,valueLabel2,  minusButton, priceLabel, deleteButton);
       row.setAlignment(Pos.CENTER);
        row.setPadding(new Insets(5, 10, 5, 10));
        row.setStyle("-fx-background-color: #f4f4f4; -fx-background-radius: 10; -fx-border-color: #ddd; -fx-border-radius: 10;");
        return row;
    }

    @FXML
    private void insertItem() {
        String name = itemNameField.getText().trim();
        String quantityText = quantityField.getText().trim();
        String priceText = priceField.getText().trim();

        // Validate input
        if (name.isEmpty() || quantityText.isEmpty() || priceText.isEmpty()) {
            System.out.println("Please fill all fields.");
            return;
        }

        try {
            int quantity = Integer.parseInt(quantityText);
            double price = Double.parseDouble(priceText);

            if (quantity <= 0 || price <= 0) {
                System.out.println("Quantity and Price must be positive.");
                return;
            }

            // Insert or update item
            inventory.put(name, inventory.getOrDefault(name, new Item(0, price)));
            inventory.get(name).setQuantity(inventory.get(name).getQuantity() + quantity);

            updateDisplay();
            clearInputFields();
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
        }
    }

    private void clearInputFields() {
        itemNameField.clear();
        quantityField.clear();
        priceField.clear();
    }

    @FXML
    private void returnHashMap() {
        System.out.println("Current Inventory:");
        for (String key : inventory.keySet()) {
            Item item = inventory.get(key);
        
        }
        Auth.updateWholesalerInventory(user,inventory );
    }

   
}

