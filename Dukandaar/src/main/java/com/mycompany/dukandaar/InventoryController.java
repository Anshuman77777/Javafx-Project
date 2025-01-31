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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.HashMap;

public class InventoryController {
    @FXML
    private VBox stocksContainer;

    private final HashMap<String, Integer> inventory = new HashMap<>();

    public void initialize() {
        // Sample data
        inventory.put("Apples", 10);
        inventory.put("Bananas", 5);
        inventory.put("Oranges", 8);

        updateDisplay();
    }

    private void updateDisplay() {
        stocksContainer.getChildren().clear();

        for (String key : inventory.keySet()) {
            int value = inventory.get(key);
            HBox itemRow = createItemRow(key, value);
            stocksContainer.getChildren().add(itemRow);
        }
    }

    private HBox createItemRow(String key, int value) {
        Label nameLabel = new Label(key);
        Label valueLabel = new Label(String.valueOf(value));

        Button plusButton = new Button("+");
        plusButton.setOnAction(e -> {
            inventory.put(key, inventory.get(key) + 1);
            updateDisplay();
        });

        Button minusButton = new Button("-");
        minusButton.setOnAction(e -> {
            if (inventory.get(key) > 1) {
                inventory.put(key, inventory.get(key) - 1);
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

        HBox row = new HBox(10, nameLabel, valueLabel, plusButton, minusButton, deleteButton);
        return row;
    }

    @FXML
    private void returnHashMap() {
        System.out.println("Current Inventory: " + inventory);
    }
}

