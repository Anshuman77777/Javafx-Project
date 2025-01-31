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
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.util.HashMap;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class PurchaseController {
    @FXML
    private VBox purchaseContainer;
    private String wholesalerUsername;
    private HashMap<String, Item> inventory;

    public void setWholesalerUsername(String username) {
        this.wholesalerUsername = username;
        loadInventory();
    }

    private void loadInventory() {
        inventory = Auth.getWholesalerInventory(wholesalerUsername);
        if (inventory == null) {
            System.out.println("No inventory found.");
            return;
        }

        purchaseContainer.getChildren().clear();
        for (Map.Entry<String, Item> entry : inventory.entrySet()) {
            purchaseContainer.getChildren().add(createPurchaseRow(entry.getKey(), entry.getValue()));
        }
    }

   private HBox createPurchaseRow(String itemName, Item item) {
    HBox row = new HBox(15);
    row.setAlignment(Pos.CENTER_LEFT);
    row.setPadding(new Insets(5, 10, 5, 10));
    row.setStyle("-fx-background-color: #f4f4f4; -fx-background-radius: 10; -fx-border-color: #ddd; -fx-border-radius: 10;");

    Label nameLabel = new Label(itemName);
    nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

    Label stockLabel = new Label("Stock: " + item.getQuantity());
    stockLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");

    Label priceLabel = new Label("Price: $" + item.getPrice());
    priceLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #28a745;");

    TextField quantityField = new TextField();
    quantityField.setPromptText("Qty");
    quantityField.setPrefWidth(50);
    quantityField.setStyle("-fx-padding: 5; -fx-border-color: #ccc; -fx-border-radius: 5;");

    Button buyButton = new Button("Buy");
    buyButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 5;");
    buyButton.setOnMouseEntered(e -> buyButton.setStyle("-fx-background-color: #0056b3; -fx-text-fill: white;"));
    buyButton.setOnMouseExited(e -> buyButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;"));
    buyButton.setOnAction(e -> purchaseItem(itemName, item, quantityField));

    HBox.setHgrow(nameLabel, Priority.ALWAYS);

    row.getChildren().addAll(nameLabel, stockLabel, priceLabel, quantityField, buyButton);
    return row;
}


    private void purchaseItem(String itemName, Item item, TextField quantityField) {
        try {
            int buyQuantity = Integer.parseInt(quantityField.getText().trim());
            if (buyQuantity <= 0 || buyQuantity > item.getQuantity()) {
                System.out.println("Invalid quantity.");
                return;
            }

            item.setQuantity(item.getQuantity() - buyQuantity);
            inventory.put(itemName, item);

            Auth.updateWholesalerInventory(wholesalerUsername, inventory);
            loadInventory(); // Refresh display after purchase
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid number.");
        }
    }
}

